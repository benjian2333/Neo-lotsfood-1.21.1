package lotsoffood.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

/**
 * 甜枫木方块 - 挖掘后掉落 maple_wood，不受精准采集影响
 */
public class WoodSpileBlock extends Block {

    public WoodSpileBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        // 始终掉落 maple_wood，无视精准采集
        return List.of(new ItemStack(lotsoffood.common.registry.ModBlocks.MAPLE_WOOD.get()));
    }
}
