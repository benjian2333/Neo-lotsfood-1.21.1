package lotsoffood.common.worldgen.feature;

import lotsoffood.common.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CoconutTreeFeature extends Feature<NoneFeatureConfiguration> {

    public CoconutTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Pick a random lean direction
        Direction leanDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int leanX = leanDir.getStepX();
        int leanZ = leanDir.getStepZ();

        // Trunk height: 9-10 blocks
        int trunkHeight = 8 + random.nextInt(2);

        // Phase 1: straight section (first 3-4 blocks go straight up)
        int straightSection = 3 + random.nextInt(1); // 3 blocks straight

        // Build trunk
        BlockState trunkState = Blocks.JUNGLE_LOG.defaultBlockState();
        int leanOffset = 0;
        BlockPos topPos = origin;

        for (int y = 0; y < trunkHeight; y++) {
            if (y >= straightSection && y % 3 == 0 && leanOffset < 2) {
                leanOffset++;
            }
            BlockPos logPos = origin.offset(leanX * leanOffset, y, leanZ * leanOffset);
            if (level.getBlockState(logPos).canBeReplaced()) {
                level.setBlock(logPos, trunkState, 3);
            }
            if (y == trunkHeight - 1) {
                topPos = logPos;
            }
        }

        // Place star-shaped leaves at the top — 4 directions, fully symmetric
        BlockState leavesState = Blocks.JUNGLE_LEAVES.defaultBlockState()
                .setValue(LeavesBlock.PERSISTENT, Boolean.TRUE)
                .setValue(LeavesBlock.DISTANCE, 1);

        BlockState coconutState = ModBlocks.COCONUT_BLOCK.get().defaultBlockState();

        // === Layer 1 (topPos + 2): small cross, 5 leaves ===
        placeIfAir(level, topPos.offset(0, 2, 0), leavesState);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            placeIfAir(level, topPos.offset(dir.getStepX(), 2, dir.getStepZ()), leavesState);
        }

        // === Layer 2 (topPos + 1): center + 3 leaves out on each of 4 axes ===
        placeIfAir(level, topPos.offset(0, 1, 0), leavesState);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            for (int i = 1; i <= 3; i++) {
                placeIfAir(level, topPos.offset(dir.getStepX() * i, 1, dir.getStepZ() * i), leavesState);
            }
        }

        // === Layer 3 (topPos): trunk center + 4 coconuts + leaf tips ===
        // Place 4 coconut blocks around the trunk
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos coconutPos = topPos.relative(dir);
            if (level.getBlockState(coconutPos).canBeReplaced()) {
                level.setBlock(coconutPos, coconutState, 3);
            }
        }

        // Leaves at distance 3 and 4 from trunk on each axis (distance 2 is air gap)
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            int sx = dir.getStepX();
            int sz = dir.getStepZ();
            placeIfAir(level, topPos.offset(sx * 3, 0, sz * 3), leavesState);
            placeIfAir(level, topPos.offset(sx * 4, 0, sz * 4), leavesState);
        }

        return true;
    }

    private void placeIfAir(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (level.getBlockState(pos).canBeReplaced()) {
            level.setBlock(pos, state, 3);
        }
    }
}
