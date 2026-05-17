package lotsoffood.common.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

/**
 * 枫木原木方块（支持斧头剥皮 + 可燃 + 可旋转）
 * 使用 StrippableRotatedPillarBlock 统一管理剥皮映射
 */
public class ModFailableRotatedPillarBlock extends StrippableRotatedPillarBlock {
    
    public ModFailableRotatedPillarBlock(Properties properties) {
        super(properties);
    }
    
    public ModFailableRotatedPillarBlock(Properties properties, int flammability, int fireSpreadSpeed) {
        super(properties, flammability, fireSpreadSpeed);
    }
}
