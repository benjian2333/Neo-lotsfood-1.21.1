package lotsoffood.data;



import java.util.List;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import lotsoffood.common.block.ModGrops.ChiliBlock;
import lotsoffood.common.block.ModGrops.GrapeBlock;
import lotsoffood.common.block.ModGrops.MaisBlock;
import lotsoffood.common.block.ModGrops.TomateBlock;
import lotsoffood.common.block.ModGrops.VanilleBlock;
import lotsoffood.common.block.ModGrops.CafeBeansBlock;
import lotsoffood.common.block.ModGrops.StrawBerryBlock;
import lotsoffood.common.block.AbstractModCakeBlock;



import lotsoffood.common.registry.ModBlocks;

public class BlockStates extends BlockStateProvider {
    private static final int DEFAULT_ANGLE_OFFSET = 180;
    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(ModBlocks.CARAM.get());
        this.simpleBlock(ModBlocks.BRICKSUCRE.get());
        this.simpleBlock(ModBlocks.CHOCOBLOC.get());

        // BRICKSUCRE variants
        this.slabBlock((SlabBlock) ModBlocks.BRICKSUCRE_SLAB.get(), this.resourceBlock("bricksucre"), this.resourceBlock("bricksucre"));
        this.stairsBlock((StairBlock) ModBlocks.BRICKSUCRE_STAIRS.get(), this.resourceBlock("bricksucre"));
        this.fenceBlock((FenceBlock) ModBlocks.BRICKSUCRE_FENCE.get(), this.resourceBlock("bricksucre"));
        this.fenceGateBlock((FenceGateBlock) ModBlocks.BRICKSUCRE_FENCE_GATE.get(), this.resourceBlock("bricksucre"));
        this.trapdoorBlock((TrapDoorBlock) ModBlocks.BRICKSUCRE_TRAPDOOR.get(), this.resourceBlock("bricksucre"), true);

        // CARAM variants
        this.slabBlock((SlabBlock) ModBlocks.CARAM_SLAB.get(), this.resourceBlock("caram"), this.resourceBlock("caram"));
        this.stairsBlock((StairBlock) ModBlocks.CARAM_STAIRS.get(), this.resourceBlock("caram"));
        this.fenceBlock((FenceBlock) ModBlocks.CARAM_FENCE.get(), this.resourceBlock("caram"));
        this.fenceGateBlock((FenceGateBlock) ModBlocks.CARAM_FENCE_GATE.get(), this.resourceBlock("caram"));
        this.trapdoorBlock((TrapDoorBlock) ModBlocks.CARAM_TRAPDOOR.get(), this.resourceBlock("caram"), true);

        // CHOCOBLOC variants
        this.slabBlock((SlabBlock) ModBlocks.CHOCOBLOC_SLAB.get(), this.resourceBlock("chocobloc"), this.resourceBlock("chocobloc"));
        this.stairsBlock((StairBlock) ModBlocks.CHOCOBLOC_STAIRS.get(), this.resourceBlock("chocobloc"));
        this.fenceBlock((FenceBlock) ModBlocks.CHOCOBLOC_FENCE.get(), this.resourceBlock("chocobloc"));
        this.fenceGateBlock((FenceGateBlock) ModBlocks.CHOCOBLOC_FENCE_GATE.get(), this.resourceBlock("chocobloc"));
        this.trapdoorBlock((TrapDoorBlock) ModBlocks.CHOCOBLOC_TRAPDOOR.get(), this.resourceBlock("chocobloc"), true);
        
        // 树叶方块：使用 cutout 渲染类型实现透明背景
        this.leavesBlock(ModBlocks.MAPLE_LEAVES_ORANGE.get());
        this.leavesBlock(ModBlocks.MAPLE_LEAVES_YELLOW.get());
        this.leavesBlock(ModBlocks.MAPLE_LEAVES_RED.get());
        this.leavesBlock(ModBlocks.MAPLE_LEAVES_LIME.get());
        // 樱花树叶: 自定义模型，在所有面上设置 tintindex=0 以支持颜色处理器染色
        this.sakuraTintedLeavesBlock(ModBlocks.SAKURA_LEAVES.get(), this.resourceBlock("sakuraleaves"));
        this.horizontalBlock(ModBlocks.COCONUT_BLOCK.get(), this.models().getExistingFile(this.resourceBlock("cocobloc")));
        this.simpleBlock(ModBlocks.MAPLE_DIAMOND_ORE.get());
        this.simpleBlock(ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get());
        this.simpleBlock(ModBlocks.MAPLE_DIAMOND_BLOCK.get());
        this.saplingBlock(ModBlocks.MAPLES_ORANGE.get());
        this.saplingBlock(ModBlocks.MAPLES_YELLOW.get());
        this.saplingBlock(ModBlocks.MAPLES_LIME.get());
        this.saplingBlock(ModBlocks.MAPLES_RED.get());
        this.saplingBlock(ModBlocks.SAKURA.get());
        this.saplingBlock(ModBlocks.COCONUT_SAPLING.get());
        // maple_wood: side 使用 maple_wood_side，顶部/底部使用 maple_wood_top
        this.axisBlock((RotatedPillarBlock) ModBlocks.MAPLE_WOOD.get(), this.resourceBlock("maple_wood_side"), this.resourceBlock("maple_wood_top"));
        this.axisBlock((RotatedPillarBlock) ModBlocks.SAKURA_WOOD.get(), this.resourceBlock("sakura_wood_side"), this.resourceBlock("sakura_wood_top"));
        // 樱木板：可旋转的竖纹方块（side=横纹 sakuraplank_x, end=竖纹 sakuraplank_y）
        this.axisBlock((RotatedPillarBlock) ModBlocks.SAKURA_PLANK.get(), this.resourceBlock("sakuraplank_x"), this.resourceBlock("sakuraplank_y"));
        // 为台阶/楼梯生成辅助模型（ExistingFileHelper 需要模型文件存在）
        this.models().cubeAll("sakuraplank_x", this.resourceBlock("sakuraplank_x"));
        this.models().cubeAll("sakuraplank_y", this.resourceBlock("sakuraplank_y"));
        // 樱木台阶（底面/顶面用竖纹，侧面用横纹，与木板视觉一致）
        this.slabBlock((SlabBlock) ModBlocks.SAKURA_SLAB.get(), this.resourceBlock("sakuraplank_y"), this.resourceBlock("sakuraplank_x"));
        this.stairsBlock((StairBlock) ModBlocks.SAKURA_STAIRS.get(), this.resourceBlock("sakuraplank_x"));
        // woodspile: side 使用 woodspile，顶部/底部使用 maple_wood_top
        this.simpleBlock(
                ModBlocks.WOOD_SPILE.get(),
                this.models().cubeColumn(
                        this.blockName(ModBlocks.WOOD_SPILE.get()),
                        this.resourceBlock("woodspile"),
                        this.resourceBlock("maple_wood_top")
                )
        );
        this.cakeBlock(ModBlocks.TARTATAIN_BLOCK.get(), "tartatatin_block");
        this.cakeBlock(ModBlocks.FRAISECAKE_BLOCK.get(), "fraisecake_block");
        this.cakeBlock(ModBlocks.CARROTCAKE_BLOCK.get(), "carrotcake_block");
        this.cakeBlock(ModBlocks.CHOCOCAKE_BLOCK.get(), "chococake_block");
        this.cakeBlock(ModBlocks.CHEESECAKE_BLOCK.get(), "cheesecake_block");
        this.cakeBlock(ModBlocks.BLACKFORESTCAKE_BLOCK.get(), "blackforestcake_block");

        this.stageBlock(ModBlocks.TOMATE_BLOCK.get(), TomateBlock.AGE);
        this.stageBlock(ModBlocks.STRAWBERRY_BLOCK.get(),StrawBerryBlock.AGE);
        this.stageBlock(ModBlocks.GRAPE_BLOCK.get(), GrapeBlock.AGE);
        this.stageBlock(ModBlocks.CHILI_BLOCK.get(), ChiliBlock.AGE);
        this.stageBlock(ModBlocks.VANILLE_BLOCK.get(), VanilleBlock.AGE);
        this.stageBlock(ModBlocks.MAIS_TOP_BLOCK.get(), MaisBlock.AGE);
        this.stageBlock(ModBlocks.MAIS_BOTTOM_BLOCK.get(), MaisBlock.AGE);
        this.stageBlock(ModBlocks.CAFEBEANS_BLOCK.get(), CafeBeansBlock.AGE);

        // 落叶堆
        this.leafPileBlock(ModBlocks.LEAVES_RED_FAST.get(), this.resourceBlock("leaves_red_fast"));
        this.leafPileBlock(ModBlocks.LEAVES_YELLOW_FAST.get(), this.resourceBlock("leaves_yellow_fast"));
        this.leafPileBlock(ModBlocks.LEAVES_ORANGE_FAST.get(), this.resourceBlock("leaves_orange_fast"));
        this.leafPileBlock(ModBlocks.LEAVES_LIME_FAST.get(), this.resourceBlock("leaves_lime_fast"));
        this.leafPileBlock(ModBlocks.LEAVES_SAKURA_FAST.get(), this.resourceBlock("sakuracarpet"));
        this.leafPileBlock(ModBlocks.LEAVES_SAKURA_RED_FAST.get(), this.resourceBlock("sakuracarpet_red"));
        this.leafPileBlock(ModBlocks.LEAVES_SAKURA_YELLOW_FAST.get(), this.resourceBlock("sakuracarpet_yellow"));


    }

    public BlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "lotsoffood", existingFileHelper);
    }

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath("lotsoffood", "block/" + path);
    }

    public ModelFile existingModel(Block block) {
        return new ModelFile.ExistingModelFile(this.resourceBlock(this.blockName(block)), this.models().existingFileHelper);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(this.resourceBlock(path), this.models().existingFileHelper);
    }

    public ConfiguredModel[] cubeRandomRotation(Block block, String suffix) {
        String var10000 = this.blockName(block);
        String formattedName = var10000 + (suffix.isEmpty() ? "" : "_" + suffix);
        return ConfiguredModel.allYRotations(this.models().cubeAll(formattedName, this.resourceBlock(formattedName)), 0, false);
    }

    public void customDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, Property<?>... ignored) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction dir = (Direction)state.getValue(BlockStateProperties.FACING);
            return ConfiguredModel.builder().modelFile((ModelFile)modelFunc.apply(state)).rotationX(dir == Direction.DOWN ? 180 : (dir.getAxis().isHorizontal() ? 90 : 0)).rotationY(dir.getAxis().isVertical() ? 0 : ((int)dir.toYRot() + 180) % 360).build();
        }, ignored);
    }

    public void customHorizontalBlock(Block block, Function<BlockState, ModelFile> modelFunc, Property<?>... ignored) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> ConfiguredModel.builder().modelFile((ModelFile)modelFunc.apply(state)).rotationY(((int)((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING)).toYRot() + 180) % 360).build(), ignored);
    }

    public void stageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
        String var10000 = this.blockName(block);

        // Manually register each age variant model, otherwise the blockstate loader may complain
        // about missing model files (e.g. "...#age=3").
        for (Integer ageSuffix : ageProperty.getPossibleValues()) {
            String stageName = var10000 + "_stage" + ageSuffix;
            String textureName;

            // Some crops use different texture naming than their block id.
            // Keep model file naming stable (stageName), only remap the texture target.
            if (var10000.equals("cropmaisbottom")) {
                textureName = "mais_block_bottom" + ageSuffix;
            } else if (var10000.equals("cropmaistop")) {
                textureName = "mais_block_top" + ageSuffix;
            } else if (var10000.equals("cafebeans_block")) {
                textureName = "cafe_beans_block_stage" + ageSuffix;
            } else {
                textureName = var10000 + "_stage" + ageSuffix;
            }

            ConfiguredModel.Builder stateBuilder =
                    this.getVariantBuilder(block).partialState().with(ageProperty, ageSuffix).modelForState();

            stateBuilder
                    .modelFile(((BlockModelBuilder)this.models().cross(stageName, this.resourceBlock(textureName))).renderType("cutout"))
                    .addModel();
        }
    }

    public void cakeBlock(Block block, String modelPrefix) {
        for (int bites = 0; bites <= AbstractModCakeBlock.MAX_BITES; bites++) {
            this.getVariantBuilder(block)
                    .partialState()
                    .with(AbstractModCakeBlock.BITES, bites)
                    .modelForState()
                    .modelFile(this.models().getExistingFile(this.resourceBlock(modelPrefix + "_bite" + bites)))
                    .addModel();
        }
    }

    public void customStageBlock(Block block, @Nullable ResourceLocation parent, String textureKey, IntegerProperty ageProperty, List<Integer> suffixes, Property<?>... ignored) {
        String var10000 = this.blockName(block);

        for (Integer ageSuffix : ageProperty.getPossibleValues()) {
            int suffixIndex = suffixes.isEmpty() ? ageSuffix : Math.min(suffixes.size() - 1, ageSuffix);
            String stageName = var10000 + "_stage" + (suffixes.isEmpty() ? ageSuffix : suffixes.get(suffixIndex));

            ConfiguredModel.Builder stateBuilder =
                    this.getVariantBuilder(block).partialState().with(ageProperty, ageSuffix).modelForState();

            BlockModelBuilder modelBuilder = parent == null
                    ? (BlockModelBuilder)this.models().cross(stageName, this.resourceBlock(stageName))
                    : (BlockModelBuilder)this.models().singleTexture(stageName, parent, textureKey, this.resourceBlock(stageName));

            stateBuilder.modelFile(modelBuilder.renderType("cutout")).addModel();
        }
    }

    public void wildCropBlock(Block block) {
        this.wildCropBlock(block, false);
    }

    public void wildCropBlock(Block block, boolean isBushCrop) {
        if (isBushCrop) {
            this.simpleBlock(block, ((BlockModelBuilder)this.models().singleTexture(this.blockName(block), this.resourceBlock("bush_crop"), "crop", this.resourceBlock(this.blockName(block)))).renderType("cutout"));
        } else {
            this.simpleBlock(block, ((BlockModelBuilder)this.models().cross(this.blockName(block), this.resourceBlock(this.blockName(block)))).renderType("cutout"));
        }

    }

    /**
     * 为树叶方块设置透明渲染类型
     */
    public void leavesBlock(Block block) {
        this.leavesBlock(block, this.resourceBlock(this.blockName(block)));
    }

    /**
     * 为树叶方块设置透明渲染类型（可指定贴图）
     */
    public void leavesBlock(Block block, ResourceLocation texture) {
        this.simpleBlock(block, ((BlockModelBuilder)this.models().cubeAll(this.blockName(block), texture)).renderType("cutout"));
    }

    /**
     * 为支持颜色染色的樱花树叶方块生成模型和方块状态
     * 关键：在所有面上设置 tintindex=0，以便 BlockColor 处理器能够染色
     */
    public void sakuraTintedLeavesBlock(Block block, ResourceLocation texture) {
        String name = this.blockName(block);
        BlockModelBuilder model = this.models().withExistingParent(name, ResourceLocation.fromNamespaceAndPath("minecraft", "block/block"))
                .texture("particle", texture)
                .texture("all", texture)
                .renderType("cutout");

        model.element()
                .from(0, 0, 0).to(16, 16, 16)
                .face(Direction.DOWN).texture("#all").cullface(Direction.DOWN).tintindex(0).end()
                .face(Direction.UP).texture("#all").cullface(Direction.UP).tintindex(0).end()
                .face(Direction.NORTH).texture("#all").cullface(Direction.NORTH).tintindex(0).end()
                .face(Direction.SOUTH).texture("#all").cullface(Direction.SOUTH).tintindex(0).end()
                .face(Direction.WEST).texture("#all").cullface(Direction.WEST).tintindex(0).end()
                .face(Direction.EAST).texture("#all").cullface(Direction.EAST).tintindex(0).end()
                .end();

        this.simpleBlock(block, model);
    }

    /**
     * 为树苗方块设置十字交叉模型（类似原版 sapling）
     */
    public void saplingBlock(Block block) {
        this.simpleBlock(block, ((BlockModelBuilder)this.models().cross(this.blockName(block), this.resourceBlock(this.blockName(block)))).renderType("cutout"));
    }

    public void leafPileBlock(Block block, ResourceLocation texture) {
        String name = this.blockName(block);
        BlockModelBuilder model = this.models().withExistingParent(name, ResourceLocation.fromNamespaceAndPath("minecraft", "block/block"))
                .texture("particle", texture)
                .texture("texture", texture)
                .renderType("cutout");

        model.element()
                .from(0, 0, 0).to(16, 2, 16)
                .face(Direction.UP).texture("#texture").uvs(0, 0, 16, 16).end()
                .face(Direction.DOWN).texture("#texture").uvs(0, 0, 16, 16).cullface(Direction.DOWN).end()
                .face(Direction.NORTH).texture("#texture").uvs(0, 14, 16, 16).end()
                .face(Direction.SOUTH).texture("#texture").uvs(0, 14, 16, 16).end()
                .face(Direction.EAST).texture("#texture").uvs(0, 14, 16, 16).end()
                .face(Direction.WEST).texture("#texture").uvs(0, 14, 16, 16).end()
                .end();

        this.simpleBlock(block, model);
    }

    public void doublePlantBlock(Block block) {
        ConfiguredModel.Builder var10000 = this.getVariantBuilder(block).partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER).modelForState();
        BlockModelProvider var10001 = this.models();
        String var10002 = this.blockName(block) + "_bottom";
        String var10004 = this.blockName(block);
        var10000 = ((VariantBlockStateBuilder)var10000.modelFile(((BlockModelBuilder)var10001.cross(var10002, this.resourceBlock(var10004 + "_bottom"))).renderType("cutout")).addModel()).partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER).modelForState();
        var10001 = this.models();
        var10002 = this.blockName(block) + "_top";
        var10004 = this.blockName(block);
        var10000.modelFile(((BlockModelBuilder)var10001.cross(var10002, this.resourceBlock(var10004 + "_top"))).renderType("cutout")).addModel();
    }


}