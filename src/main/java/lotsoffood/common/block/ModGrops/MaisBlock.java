package lotsoffood.common.block.ModGrops;

import lotsoffood.Config;
import com.mojang.serialization.MapCodec;
import lotsoffood.common.block.AbstractTallBushCropBlock;
import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.List;

/**
 * 两格结构作物：
 * - Bottom：负责生长、骨粉、掉落
 * - Top：独立 AGE、禁止掉落；但当 Top 成熟时会把 Bottom 也标记为成熟
 */
public class MaisBlock {
    // 玉米阶段数：age 0..MAX_AGE 共 MAX_AGE+1 个阶段
    public static final int MAX_AGE = 4;

    /**
     * 防止两格作物在“玩家破坏一格”时触发对方 onRemove 再次反向销毁，
     * 造成重复销毁/递归，进而引发卡死或闪烁现象。
     *
     * 只用于“同一线程内的配对销毁”防重入。
     */
    private static final ThreadLocal<Boolean> SKIP_TOP_REMOVAL = ThreadLocal.withInitial(() -> false);

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    // 生长概率（仿照 AbstractTallBushCropBlock 的 roll 逻辑）
    private static final int GROWING_SPEED = 5;
    private static final int GROWTH_CHANCE = 2;

    private static BlockPos topPos(BlockPos bottomPos) {
        return bottomPos.above();
    }

    private static BlockPos bottomPos(BlockPos topPos) {
        return topPos.below();
    }

    private static int getAge(BlockState state) {
        return state.getValue(AGE);
    }

    private static boolean isMaxAge(BlockState state) {
        return getAge(state) >= MAX_AGE;
    }

    private static double stemHalfWidthByAge(int age) {
        double t = (double) Math.max(0, Math.min(MAX_AGE, age)) / (double) MAX_AGE; // 0..1
        return 2.0 + 3.0 * t; // 直径约 4..10，保持细长感
    }

    private static double stemMinByAge(int age) {
        return 8.0 - stemHalfWidthByAge(age);
    }

    private static double stemMaxByAge(int age) {
        return 8.0 + stemHalfWidthByAge(age);
    }

    private static void ensureTop(ServerLevel level, BlockPos bottomPos, BlockState bottomState) {
        BlockPos topPos = topPos(bottomPos);
        BlockState existingTop = level.getBlockState(topPos);

        int age = getAge(bottomState);

        // 顶部如果已存在，就同步更新 AGE，让顶端贴图/碰撞随生长阶段变化。
        if (existingTop.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisTopBlock) {
            if (getAge(existingTop) != age) {
                BlockState nextTop = existingTop.setValue(AGE, age);
                level.setBlock(topPos, nextTop, 3);
            }
            return;
        }

        // 顶部为空气时创建
        if (existingTop.isAir()) {
            BlockState nextTop = ModBlocks.MAIS_TOP_BLOCK.get().defaultBlockState().setValue(AGE, age);
            level.setBlock(topPos, nextTop, 3);
        }
    }

    public static class CropMaisBottomBlock extends AbstractTallBushCropBlock {
        public CropMaisBottomBlock(BlockBehaviour.Properties properties) {
            super(
                    properties,
                    MAX_AGE,
                    1,
                    state -> state.is(Blocks.FARMLAND),
                    GROWING_SPEED,
                    GROWTH_CHANCE
            );
        }

        @Override
        public MapCodec<? extends BushBlock> codec() {
            @SuppressWarnings("unchecked")
            MapCodec<? extends BushBlock> codec = (MapCodec<? extends BushBlock>) (MapCodec<?>) BushBlock.CODEC;
            return codec;
        }

        @Override
        protected IntegerProperty getAgeProperty() {
            return AGE;
        }

        @Override
        protected ItemLike getHarvestItem() {
            return ModItems.CORN.get();
        }

        @Override
        protected ItemLike getSeedItem() {
            return ModItems.CORN.get();
        }

        @Override
        protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            BlockPos topPos = pos.above();
            BlockState topState = level.getBlockState(topPos);
            // 初次种植时顶部为空气（setPlacedBy 会随后放置顶部方块）；
            // 已建立后顶部必须是 CropMaisTopBlock；其余方块则不合法。
            if (!topState.isAir() && !(topState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisTopBlock)) {
                return false;
            }
            if (level.getRawBrightness(topPos, 0) < 9) {
                return false;
            }

            BlockState soil = level.getBlockState(pos.below());
            return this.mayPlaceOn(soil, level, pos.below());
        }

        @Override
        protected boolean isRandomlyTicking(BlockState state) {
            return !isMaxAge(state);
        }

        @Override
        protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
            if (!level.isAreaLoaded(pos, 1)) {
                return;
            }

            BlockPos topPos = pos.above();
            BlockState topState = level.getBlockState(topPos);
            if (!(topState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisTopBlock)) {
                return;
            }

            // 光照检测：只看顶部方块位置的亮度
            if (level.getRawBrightness(topPos, 0) < 9) {
                return;
            }

            int age = getAge(state);
            if (age >= MAX_AGE) {
                return;
            }

            boolean roll = random.nextInt(GROWING_SPEED + GROWTH_CHANCE) < GROWTH_CHANCE;
            if (!roll) {
                return;
            }

            // 使用顶部位置做 growth hook/事件判定
            if (CommonHooks.canCropGrow(level, topPos, topState, roll)) {
                BlockState before = state;
                BlockState next = getStateForAge(age + 1);
                level.setBlock(pos, next, 2);
                CommonHooks.fireCropGrowPost(level, pos, before);
                onGrowthStageChanged(level, pos, next, age, age + 1);
            }
        }

        @Override
        protected void onGrowthStageChanged(ServerLevel level, BlockPos pos, BlockState newState, int previousAge, int newAge) {
            // 生长发生在底部：同步顶部 AGE
            ensureTop(level, pos, newState);
        }

        @Override
        public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
            if (isMaxAge(state)) {
                return;
            }
            int current = getAge(state);
            int targetAge = Math.min(MAX_AGE, current + 1);
            BlockState next = getStateForAge(targetAge);
            // 使用 3 强制同步到客户端，避免出现服务端已更新但客户端仍显示 stage0。
            level.setBlock(pos, next, 3);
            onGrowthStageChanged(level, pos, next, current, targetAge);
        }

        @Override
        protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            int age = getAge(state);
            double min = stemMinByAge(age);
            double max = stemMaxByAge(age);

            // 需求：age=0 时 bottom 约半格高；age>=1 时 bottom 直接满方块高度。
            double yMax = age <= 0 ? 8.0 : 16.0;
            return Block.box(min, 0.0, min, max, yMax, max);
        }

        @Override
        protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
            RandomSource random = params.getLevel().getRandom();
            if (isMaxAge(state)) {
                // 其它作物：3~4 个果实
                int n = 2 + random.nextInt(2);
                return List.of(new ItemStack(getHarvestItem(), n));
            }
            // 未成熟时：至少掉落 1 个种子/果实，避免“作物完全不掉落”的问题
            return List.of(new ItemStack(getSeedItem(), 1));
        }

        @Override
        public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
            super.setPlacedBy(level, pos, state, placer, stack);
            if (level instanceof ServerLevel serverLevel) {
                ensureTop(serverLevel, pos, state);
            }
        }

        @Override
        protected ItemInteractionResult useItemOn(
                ItemStack stack,
                BlockState state,
                Level level,
                BlockPos pos,
                Player player,
                InteractionHand hand,
                BlockHitResult hitResult
        ) {
            // 针对玉米 bottom 的兜底逻辑：直接处理骨粉，避免被上层交互分支吞掉。
            if (stack.is(Items.BONE_MEAL) && !isMaxAge(state)) {
                if (level instanceof ServerLevel serverLevel) {
                    this.performBonemeal(serverLevel, serverLevel.random, pos, state);
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                }
                // 客户端与服务端都触发一次骨粉粒子事件，确保创造模式下也稳定可见。
                level.levelEvent(1505, pos, 15);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        @Override
        public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
            if (!level.isClientSide && newState.isAir()) {
                BlockPos topPos = pos.above();
                BlockState topState = level.getBlockState(topPos);
                if (topState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisTopBlock) {
                    // 当顶部因为“玩家破坏顶部”而触发底部销毁时，这里不再反向销毁顶部，
                    // 避免顶部被重复移除导致闪烁/递归。
                    if (!SKIP_TOP_REMOVAL.get() && level instanceof ServerLevel serverLevel) {
                        // 顶部不掉落，所以这里直接置空即可（避免 destroyBlock 触发额外链式逻辑）
                        serverLevel.setBlock(topPos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    public static class CropMaisTopBlock extends AbstractTallBushCropBlock {
        // 碰撞/选中盒在 AGE=0 时完全为空（顶部贴图阶段 0 是空白）。


        public CropMaisTopBlock(BlockBehaviour.Properties properties) {
            super(
                    properties,
                    MAX_AGE,
                    1,
                    state -> state.is(Blocks.FARMLAND),
                    GROWING_SPEED,
                    GROWTH_CHANCE
            );
        }

        @Override
        public MapCodec<? extends BushBlock> codec() {
            @SuppressWarnings("unchecked")
            MapCodec<? extends BushBlock> codec = (MapCodec<? extends BushBlock>) (MapCodec<?>) BushBlock.CODEC;
            return codec;
        }

        @Override
        protected IntegerProperty getAgeProperty() {
            return AGE;
        }

        @Override
        protected ItemLike getHarvestItem() {
            // 顶部不掉落，但返回一个有效物品用于骨粉/克隆逻辑
            return ModItems.CORN.get();
        }

        @Override
        protected ItemLike getSeedItem() {
            return ModItems.CORN.get();
        }

        @Override
        protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            // 结构保护：必须有底部方块；并检查顶部光照
            BlockState bottomState = level.getBlockState(pos.below());
            if (!(bottomState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisBottomBlock)) {
                return false;
            }
            return level.getRawBrightness(pos, 0) >= 9;
        }

        @Override
        protected boolean isRandomlyTicking(BlockState state) {
            // 底部控制生长：顶部不随机生长
            return false;
        }

        @Override
        protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            int age = getAge(state);
            if (age <= 0) {
                return Shapes.empty();
            }

            // 需求：上下同阶段 XZ 一致，顶部从 age=1 开始出现并沿 Y 递增到满格。
            double min = stemMinByAge(age);
            double max = stemMaxByAge(age);

            double progress = MAX_AGE <= 1 ? 1.0 : (double) (age - 1) / (double) (MAX_AGE - 1); // age1->0, max->1
            double height = 4.0 + 12.0 * progress; // age=1 约 1/4 格, 最终 16
            return Block.box(min, 0.0, min, max, height, max);
        }

        @Override
        protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
            // 顶部不掉落
            return List.of();
        }

        @Override
        public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
            // 骨粉支持：不管作用在顶部还是底部，统一由底部推进
            BlockPos bottomBlockPos = bottomPos(pos);
            BlockState bottomState = level.getBlockState(bottomBlockPos);
            return bottomState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisBottomBlock bottomBlock && !bottomBlock.isMaxAge(bottomState);
        }

        @Override
        public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
            BlockPos bottomBlockPos = bottomPos(pos);
            BlockState bottomState = level.getBlockState(bottomBlockPos);
            if (bottomState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisBottomBlock bottomBlock) {
                bottomBlock.performBonemeal(level, random, bottomBlockPos, bottomState);
            }
        }

        @Override
        public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
            super.onRemove(state, level, pos, newState, isMoving);
        }

        @Override
        public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
            // 仅在“玩家手动破坏 top”时同步删除 bottom 并让 bottom 负责掉落。
            // 不在 onRemove 里做双向 destroy，避免两格互相 destroy 引发的递归/卡死。
            if (!level.isClientSide) {
                BlockPos bottomPos = bottomPos(pos);
                BlockState bottomState = level.getBlockState(bottomPos);
                if (bottomState.getBlock() instanceof lotsoffood.common.block.ModGrops.MaisBlock.CropMaisBottomBlock) {
                    // 这次销毁会触发 bottom.onRemove，从而尝试再次移除 top；
                    // 加锁跳过 bottom.onRemove 的反向移除，避免重复销毁。
                    SKIP_TOP_REMOVAL.set(true);
                    try {
                        ((ServerLevel) level).destroyBlock(bottomPos, true);
                    } finally {
                        SKIP_TOP_REMOVAL.set(false);
                    }
                }
            }
            return super.playerWillDestroy(level, pos, state, player);
        }
    }
}