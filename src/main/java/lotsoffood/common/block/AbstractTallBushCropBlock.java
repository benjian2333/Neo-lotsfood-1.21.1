package lotsoffood.common.block;

import lotsoffood.Config;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 面向两格及以上高秆作物的通用父类：继承 {@link BushBlock}，统一管理生长阶段、土壤校验、随机刻生长、骨粉与掉落。
 * <p>
 * 子类必须提供静态的 {@link IntegerProperty} AGE（通过 {@link #getAgeProperty()}），并实现 {@link #codec()}、
 * {@link #getHarvestItem()}、{@link #getSeedItem()}。
 */
public abstract class AbstractTallBushCropBlock extends BushBlock implements BonemealableBlock {

    @Override
    public abstract MapCodec<? extends BushBlock> codec();

    private final int maxAge;
    private final int minOccupyingHeightBlocks;
    private final Predicate<BlockState> validSoil;
    private final int growingSpeed;
    private final int growthChance;

    protected AbstractTallBushCropBlock(
            BlockBehaviour.Properties properties,
            int maxAge,
            int minOccupyingHeightBlocks,
            Predicate<BlockState> validSoil,
            int growingSpeed,
            int growthChance
    ) {
        super(
                properties
                        .sound(SoundType.GRASS)
                        .randomTicks()
                        .noCollission()
                        .instabreak()
        );
        if (maxAge < 1) {
            throw new IllegalArgumentException("maxAge must be >= 1 (at least two stages: 0..maxAge)");
        }
        if (minOccupyingHeightBlocks < 1) {
            throw new IllegalArgumentException("minOccupyingHeightBlocks must be >= 1");
        }
        if (growingSpeed < 1 || growthChance < 1) {
            throw new IllegalArgumentException("growingSpeed and growthChance must be >= 1");
        }
        this.maxAge = maxAge;
        this.minOccupyingHeightBlocks = minOccupyingHeightBlocks;
        this.validSoil = validSoil;
        this.growingSpeed = growingSpeed;
        this.growthChance = growthChance;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    /** 最大阶段索引（成熟值为该值）；阶段总数为 {@code getMaxAge() + 1}。 */
    public int getMaxAge() {
        return maxAge;
    }

    public int getAge(BlockState state) {
        return state.getValue(getAgeProperty());
    }

    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(getAgeProperty(), Mth.clamp(age, 0, maxAge));
    }

    public boolean isMaxAge(BlockState state) {
        return getAge(state) >= maxAge;
    }

    /** 生长阶段总数（nbStades）。 */
    public int getGrowthStageCount() {
        return maxAge + 1;
    }

    /**
     * 子类必须声明与 {@link #getMaxAge()} 范围一致的 {@link IntegerProperty}（通常命名为 {@code AGE}）。
     */
    protected abstract IntegerProperty getAgeProperty();

    protected abstract ItemLike getHarvestItem();

    protected abstract ItemLike getSeedItem();

    /**
     * 是否为「用种子种植」的作物；未成熟时仅掉落 1 个种子；否则未成熟掉落 1 个 {@link #getHarvestItem()}。
     */
    protected boolean isSeedPlantedCrop() {
        return true;
    }

    /**
     * 子类在向上延伸多格（例如第二格茎、果穗）时，可在此同步放置或破坏上层方块。
     */
    @SuppressWarnings("unused")
    protected void onGrowthStageChanged(ServerLevel level, BlockPos pos, BlockState newState, int previousAge, int newAge) {
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return validSoil.test(state);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (!CropBlock.hasSufficientLight(level, pos)) {
            return false;
        }
        for (int i = 1; i < minOccupyingHeightBlocks; i++) {
            if (!level.getBlockState(pos.above(i)).isAir()) {
                return false;
            }
        }
        return super.canSurvive(state, level, pos);
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
        if (level.getRawBrightness(pos, 0) < 9) {
            return;
        }
        int age = getAge(state);
        if (age >= maxAge) {
            return;
        }
        boolean roll = random.nextInt(growingSpeed + growthChance) < growthChance;
        if (CommonHooks.canCropGrow(level, pos, state, roll) && roll) {
            BlockState before = state;
            BlockState next = getStateForAge(age + 1);
            level.setBlock(pos, next, 2);
            CommonHooks.fireCropGrowPost(level, pos, before);
            onGrowthStageChanged(level, pos, next, age, age + 1);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !isMaxAge(state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return !isMaxAge(state);
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (isMaxAge(state)) {
            return;
        }
        int current = getAge(state);
        int targetAge;
        if (Config.TALL_CROP_BONEMEAL_INSTANT.get()) {
            targetAge = maxAge;
        } else {
            int nbStades = getGrowthStageCount();
            int delta = nbStades / 3 + random.nextInt(4);
            targetAge = Math.min(maxAge, current + delta);
        }
        BlockState before = state;
        BlockState next = getStateForAge(targetAge);
        level.setBlock(pos, next, 2);
        CommonHooks.fireCropGrowPost(level, pos, before);
        onGrowthStageChanged(level, pos, next, current, targetAge);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(getSeedItem());
    }

    /**
     * 未成熟时允许继续走“物品对方块”的默认流程，让骨粉物品逻辑触发 BonemealableBlock。
     */
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
        if (!isMaxAge(state) && stack.is(Items.BONE_MEAL)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> out = new ArrayList<>(1);
        if (isMaxAge(state)) {
            int n = 2 + params.getLevel().getRandom().nextInt(3);
            out.add(new ItemStack(getHarvestItem(), n));
        } else if (isSeedPlantedCrop()) {
            out.add(new ItemStack(getSeedItem(), 1));
        } else {
            out.add(new ItemStack(getHarvestItem(), 1));
        }
        return out;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int age = getAge(state);
        double height = 2.0 + 14.0 * (double) age / (double) maxAge;
        return Block.box(0.0, 0.0, 0.0, 16.0, height, 16.0);
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            Direction facing,
            BlockState facingState,
            net.minecraft.world.level.LevelAccessor level,
            BlockPos currentPos,
            BlockPos facingPos
    ) {
        return !state.canSurvive(level, currentPos)
                ? net.minecraft.world.level.block.Blocks.AIR.defaultBlockState()
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }
}