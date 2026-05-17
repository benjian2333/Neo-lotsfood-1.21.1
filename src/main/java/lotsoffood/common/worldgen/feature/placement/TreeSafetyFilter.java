package lotsoffood.common.worldgen.feature.placement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.util.RandomSource;

/**
 * 树木安全放置过滤器
 * 用于防止模组树木在已有树木的位置生成，避免"叠叠乐"现象
 */
public class TreeSafetyFilter extends PlacementFilter {

    public static final MapCodec<TreeSafetyFilter> CODEC = RecordCodecBuilder.mapCodec(p_225608_ -> 
        p_225608_.group(
            Codec.BOOL.fieldOf("check_below").forGetter(p -> p.checkBelow),
            Codec.INT.fieldOf("check_range").forGetter(p -> p.checkRange)
        ).apply(p_225608_, TreeSafetyFilter::new)
    );

    private final boolean checkBelow;
    private final int checkRange;

    public TreeSafetyFilter(boolean checkBelow, int checkRange) {
        this.checkBelow = checkBelow;
        this.checkRange = checkRange;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        // 检查 pos / 下方是否是树木方块
        if (checkBelow) {
            BlockState stateAtPos = context.getBlockState(pos);
            if (isTreeBlock(stateAtPos)) {
                return false; // pos 已被树占用，拒绝生成
            }

            BlockPos belowPos = pos.below();
            BlockState belowState = context.getBlockState(belowPos);
            
            if (isTreeBlock(belowState)) {
                return false; // 下方有树木，拒绝生成
            }
        }
        
        // 检查周围是否有树木
        if (checkRange > 0) {
            // 只要 pos 上方落点被树冠占用（leaves），TreeFeature 仍可能走到放置/根系逻辑，
            // 因此这里对 y 方向进行“向上偏大”的检测，尽量覆盖 trunk/foliage 的上半段。
            int yMin = -checkRange;
            // 山地自然生成可能遇到“几十格高”的原版树，这里把向上扫描范围放大到更接近树冠高度。
            int yMax = checkRange * 6;
            for (int x = -checkRange; x <= checkRange; x++) {
                for (int z = -checkRange; z <= checkRange; z++) {
                    for (int y = yMin; y <= yMax; y++) {
                        BlockPos checkPos = pos.offset(x, y, z);
                        BlockState checkState = context.getBlockState(checkPos);
                        
                        if (isTreeBlock(checkState)) {
                            return false; // 周围有树木，拒绝生成
                        }
                    }
                }
            }
        }
        
        return true; // 可以生成
    }

    /**
     * 检查是否是树木相关的方块（原木、树叶等）
     */
    private boolean isTreeBlock(BlockState state) {
        // 使用 Tag 检查，更加通用
        if (state.is(BlockTags.LOGS)) {
            return true;
        }
        if (state.is(BlockTags.LEAVES)) {
            return true;
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> type() {
        return lotsoffood.common.registry.ModPlacementModifiers.TREE_SAFETY_FILTER.get();
    }
}
