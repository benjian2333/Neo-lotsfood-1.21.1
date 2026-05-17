package lotsoffood.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 具有可燃性的旋转柱状方块基类
 * 适用于原木、去皮原木等需要燃烧且可以旋转的方块
 */
public class FlammableRotatedPillarBlock extends RotatedPillarBlock {
    
    private final int flammability;
    private final int fireSpreadSpeed;
    
    public FlammableRotatedPillarBlock(Properties properties, int flammability, int fireSpreadSpeed) {
        super(properties);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }
    
    /**
     * 默认构造函数，使用标准可燃值
     */
    public FlammableRotatedPillarBlock(Properties properties) {
        this(properties, 5, 5);
    }
    
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return flammability;
    }
    
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return fireSpreadSpeed;
    }
}
