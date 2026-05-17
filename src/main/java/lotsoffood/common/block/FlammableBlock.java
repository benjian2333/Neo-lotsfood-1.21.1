package lotsoffood.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 具有可燃性的方块基类
 * 所有需要燃烧的方块都应该继承此类而不是直接继承 Block
 * 适用于不可旋转的方块（如树叶、木板等）
 */
public class FlammableBlock extends Block {
    
    private final int flammability;
    private final int fireSpreadSpeed;
    
    public FlammableBlock(Properties properties, int flammability, int fireSpreadSpeed) {
        super(properties);
        this.flammability = flammability;
        this.fireSpreadSpeed = fireSpreadSpeed;
    }
    
    /**
     * 默认构造函数，使用标准可燃值
     */
    public FlammableBlock(Properties properties) {
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
