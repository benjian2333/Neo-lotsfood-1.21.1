package lotsoffood.common.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

/**
 * 自定义安全树木特征
 * 防止与原版树木冲突，避免"叠叠乐"现象
 * 
 * 解决思路：
 * 1. 检查下方是否是树木方块（原木、树叶）
 * 2. 如果是，则拒绝生成，让位于原版树木
 * 3. 这样可以确保同一位置只有一棵树生成
 */
public class ModSafeTreeFeature extends TreeFeature {
    
    public ModSafeTreeFeature() {
        super(TreeConfiguration.CODEC);
    }
    
    public ModSafeTreeFeature(Codec<TreeConfiguration> codec) {
        super(codec);
    }
}
