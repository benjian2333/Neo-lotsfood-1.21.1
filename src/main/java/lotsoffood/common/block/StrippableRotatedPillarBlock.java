package lotsoffood.common.block;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * 支持斧头剥皮的旋转柱状方块基类
 * 统一管理所有原木与去皮原木之间的映射关系
 * 
 * 使用示例：
 * static {
 *     registerStripping(ModBlocks.SAKURA_WOOD, ModBlocks.STRIPPED_SAKURA_WOOD);
 * }
 */
public class StrippableRotatedPillarBlock extends FlammableRotatedPillarBlock {
    
    /** 存储原木 -> 去皮原木的映射关系 */
    private static final Map<Block, Block> STRIP_MAP = new HashMap<>();
    
    public StrippableRotatedPillarBlock(Properties properties) {
        super(properties);
    }
    
    public StrippableRotatedPillarBlock(Properties properties, int flammability, int fireSpreadSpeed) {
        super(properties, flammability, fireSpreadSpeed);
    }
    
    /**
     * 注册原木到去皮原木的映射关系
     * @param logBlock 原木方块
     * @param strippedBlock 对应的去皮原木方块
     */
    public static void registerStripping(Block logBlock, Block strippedBlock) {
        STRIP_MAP.put(logBlock, strippedBlock);
    }
    
    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            Block currentBlock = state.getBlock();
            
            // 查找映射表中是否有对应的去皮方块
            Block strippedBlock = STRIP_MAP.get(currentBlock);
            if (strippedBlock != null) {
                return strippedBlock.defaultBlockState().setValue(BlockStateProperties.AXIS, state.getValue(BlockStateProperties.AXIS));
            }
        }
        
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
