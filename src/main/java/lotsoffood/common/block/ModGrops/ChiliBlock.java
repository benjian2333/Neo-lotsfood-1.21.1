package lotsoffood.common.block.ModGrops;

import lotsoffood.common.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ChiliBlock extends CropBlock{
    public static final int MAX_AGE = 4;
    public static final IntegerProperty AGE = IntegerProperty.create("age",0,4);
    private static final VoxelShape[] SHAPE_BY_AGE =
            new VoxelShape[]{
                    Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 13.0, 16.0),
                    Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
            };
    public ChiliBlock(Properties properties) {
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }
    @Override
    protected ItemLike getBaseSeedId(){
        return ModItems.CHILI_SEEDS.get();
    }
    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        int age = state.getValue(AGE);
        var random = params.getLevel().getRandom();
        if (age >= MAX_AGE) {
            // 辣椒：掉落种子 + 2~3 个果实
            int fruitCount = 2 + random.nextInt(2);
            return List.of(
                    new ItemStack(ModItems.CHILI_SEEDS.get(), 1),
                    new ItemStack(ModItems.CHILI.get(), fruitCount)
            );
        }
        // 未成熟：掉落种子
        return List.of(new ItemStack(ModItems.CHILI_SEEDS.get(), 1));
    }

}