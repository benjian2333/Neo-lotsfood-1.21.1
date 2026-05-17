package lotsoffood.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.DyeColor;

/**
 * 樱花树叶方块 - 支持 16 种颜色变体
 */
public class SakuraLeavesBlock extends FlammableLeavesBlock {
    /**
     * 颜色属性：原版 16 种染料色
     */
    public static final net.minecraft.world.level.block.state.properties.EnumProperty<DyeColor> VARIANT =
        net.minecraft.world.level.block.state.properties.EnumProperty.create("variant", DyeColor.class);

    public SakuraLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        // Default: raw white texture (no tint).
        this.registerDefaultState(this.defaultBlockState().setValue(VARIANT, DyeColor.WHITE));
    }

    @Override
    protected void createBlockStateDefinition(net.minecraft.world.level.block.state.StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VARIANT);
    }
}
