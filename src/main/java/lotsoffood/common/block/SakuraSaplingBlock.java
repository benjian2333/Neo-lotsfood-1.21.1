package lotsoffood.common.block;

import lotsoffood.common.block.type.SakuraVariantColors;
import lotsoffood.common.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class SakuraSaplingBlock extends SaplingBlock {
    public static final EnumProperty<DyeColor> VARIANT = EnumProperty.create("variant", DyeColor.class);

    public SakuraSaplingBlock(BlockBehaviour.Properties properties) {
        super(ModTreeGrowers.SAKURA_TREE_GROWER, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STAGE, 0).setValue(VARIANT, DyeColor.WHITE));
    }

    @Override
    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, net.minecraft.util.RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            TreeGrower grower = ModTreeGrowers.getSakuraTreeGrower(state.getValue(VARIANT));
            grower.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack, BlockState state, Level level, BlockPos pos, net.minecraft.world.entity.player.Player player, InteractionHand hand, BlockHitResult hitResult
    ) {
        if (!(stack.getItem() instanceof DyeItem dyeItem)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        DyeColor color = dyeItem.getDyeColor();
        if (!SakuraVariantColors.isEnabledVariant(color)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        BlockState updatedState = state.getValue(VARIANT) == color ? state : state.setValue(VARIANT, color);
        if (updatedState != state) {
            level.setBlock(pos, updatedState, 3);
        }

        if (!level.isClientSide) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }

            if (level instanceof ServerLevel serverLevel
                    && this.isValidBonemealTarget(serverLevel, pos, updatedState)
                    && this.isBonemealSuccess(serverLevel, serverLevel.random, pos, updatedState)) {
                this.performBonemeal(serverLevel, serverLevel.random, pos, updatedState);
                serverLevel.levelEvent(1505, pos, 0);
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VARIANT);
    }
}

