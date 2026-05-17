package lotsoffood.common.registry;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import lotsoffood.common.block.ModCake.*;
import lotsoffood.common.block.CoconutBlock;
import lotsoffood.common.block.FlammableLeavesBlock;
import lotsoffood.common.block.FlammableRotatedPillarBlock;
import lotsoffood.common.block.ModFailableRotatedPillarBlock;
import lotsoffood.common.block.SakuraLeavesBlock;
import lotsoffood.common.block.SakuraSaplingBlock;
import lotsoffood.common.block.ModGrops.MaisBlock;
import lotsoffood.common.block.ModGrops.GrapeBlock;
import lotsoffood.common.block.ModGrops.CafeBeansBlock;
import lotsoffood.common.block.ModGrops.StrawBerryBlock;
import lotsoffood.common.block.ModGrops.TomateBlock;
import lotsoffood.common.block.ModGrops.VanilleBlock;
import lotsoffood.common.block.WoodSpileBlock;
import lotsoffood.common.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;
import lotsoffood.common.block.LeafPileBlock;
import lotsoffood.common.block.ModGrops.ChiliBlock;
import lotsoffood.common.block.ModCake.FraiseCakeBlock;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS;

    public static final Supplier<CoconutBlock> COCONUT_BLOCK;
    public static final Supplier<Block> COCONUT_SAPLING;
    public static final Supplier<Block> MAPLE_DIAMOND_ORE;
    public static final Supplier<Block> DEEPSLATE_MAPLE_DIAMOND_ORE;
    public static final Supplier<Block> MAPLE_DIAMOND_BLOCK;
    public static final Supplier<TomateBlock> TOMATE_BLOCK;
    public static final Supplier<StrawBerryBlock> STRAWBERRY_BLOCK;
    public static final Supplier<ChiliBlock> CHILI_BLOCK;
    public static final Supplier<CafeBeansBlock> CAFEBEANS_BLOCK;
    public static final Supplier<GrapeBlock> GRAPE_BLOCK;
    public static final Supplier<VanilleBlock> VANILLE_BLOCK;
    public static final Supplier<MaisBlock.CropMaisBottomBlock> MAIS_BOTTOM_BLOCK;
    public static final Supplier<MaisBlock.CropMaisTopBlock> MAIS_TOP_BLOCK;
    public static final Supplier<MaisBlock.CropMaisBottomBlock>MAIS_BLOCK;
    public static final Supplier<TartAtainBlock> TARTATAIN_BLOCK;
    public static final Supplier<FraiseCakeBlock> FRAISECAKE_BLOCK;
    public static final Supplier<CarrotCakeBlock> CARROTCAKE_BLOCK;
    public static final Supplier<ChocoCakeBlock> CHOCOCAKE_BLOCK;
    public static final Supplier<CheeseCakeBlock> CHEESECAKE_BLOCK;
    public static final Supplier<BlackForestCakeBlock> BLACKFORESTCAKE_BLOCK;
    public static final Supplier<Block> MAPLE_WOOD;
    public static final Supplier<Block> MAPLE_LEAVES_LIME;
    public static final Supplier<Block> MAPLE_LEAVES_YELLOW;
    public static final Supplier<Block> MAPLE_LEAVES_RED;
    public static final Supplier<Block> MAPLE_LEAVES_ORANGE;
    public static final Supplier<Block> BRICKSUCRE;
    public static final Supplier<Block> CARAM;
    public static final Supplier<Block> CHOCOBLOC;
    public static final Supplier<Block> BRICKSUCRE_SLAB;
    public static final Supplier<Block> BRICKSUCRE_STAIRS;
    public static final Supplier<Block> BRICKSUCRE_FENCE;
    public static final Supplier<Block> BRICKSUCRE_TRAPDOOR;
    public static final Supplier<Block> CARAM_SLAB;
    public static final Supplier<Block> CARAM_STAIRS;
    public static final Supplier<Block> CARAM_FENCE;
    public static final Supplier<Block> CARAM_TRAPDOOR;
    public static final Supplier<Block> CHOCOBLOC_SLAB;
    public static final Supplier<Block> CHOCOBLOC_STAIRS;
    public static final Supplier<Block> CHOCOBLOC_FENCE;
    public static final Supplier<Block> CHOCOBLOC_FENCE_GATE;
    public static final Supplier<Block> CHOCOBLOC_TRAPDOOR;
    public static final Supplier<Block> BRICKSUCRE_FENCE_GATE;
    public static final Supplier<Block> CARAM_FENCE_GATE;
    public static final Supplier<WoodSpileBlock> WOOD_SPILE;
    public static final Supplier<Block> MAPLES_ORANGE;
    public static final Supplier<Block> MAPLES_YELLOW;
    public static final Supplier<Block> MAPLES_LIME;
    public static final Supplier<Block> MAPLES_RED;
    public static final Supplier<Block> SAKURA;
    public static final Supplier<Block> SAKURA_LEAVES;
    public static final Supplier<Block> SAKURA_WOOD;
    public static final Supplier<Block> SAKURA_PLANK;
    public static final Supplier<Block> SAKURA_STAIRS;
    public static final Supplier<Block> SAKURA_SLAB;
    public static final Supplier<LeafPileBlock> LEAVES_RED_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_YELLOW_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_ORANGE_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_LIME_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_SAKURA_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_SAKURA_RED_FAST;
    public static final Supplier<LeafPileBlock> LEAVES_SAKURA_YELLOW_FAST;
    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> (Boolean)state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    static {
        BLOCKS = DeferredRegister.create(Registries.BLOCK, "lotsoffood");

        COCONUT_BLOCK = BLOCKS.register("coconut_block", ()-> new CoconutBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD).noOcclusion()));
        COCONUT_SAPLING = BLOCKS.register("poussecocotier", () -> new SaplingBlock(ModTreeGrowers.COCONUT_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        MAPLE_DIAMOND_ORE = BLOCKS.register("maple_diamond_ore", ()-> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
        DEEPSLATE_MAPLE_DIAMOND_ORE =    BLOCKS.register("deepslate_maple_diamond_ore", ()-> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
        MAPLE_DIAMOND_BLOCK =    BLOCKS.register("maple_diamond_block", ()-> new Block(BlockBehaviour.Properties.of().strength(4f).sound(SoundType.METAL).requiresCorrectToolForDrops()));
        TOMATE_BLOCK = BLOCKS.register("tomate_block", ()-> new TomateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
        STRAWBERRY_BLOCK = BLOCKS.register("strawberry_block", ()-> new StrawBerryBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        CHILI_BLOCK = BLOCKS.register("chili_block", ()-> new ChiliBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
        CAFEBEANS_BLOCK = BLOCKS.register("cafebeans_block", ()-> new CafeBeansBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        GRAPE_BLOCK = BLOCKS.register("grape_block", ()-> new GrapeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        VANILLE_BLOCK = BLOCKS.register("vanille_block", ()-> new VanilleBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        // 两格结构作物：玉米
        MAIS_BOTTOM_BLOCK = BLOCKS.register("cropmaisbottom", () -> new MaisBlock.CropMaisBottomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        MAIS_TOP_BLOCK = BLOCKS.register("cropmaistop", () -> new MaisBlock.CropMaisTopBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARROTS)));
        // 兼容现有的 ModItems 引用命名：Mais_BLOCK -> 底部方块
        MAIS_BLOCK = MAIS_BOTTOM_BLOCK;
        TARTATAIN_BLOCK = BLOCKS.register("tartatatin_block", () -> new TartAtainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        FRAISECAKE_BLOCK = BLOCKS.register("fraisecake_block", ()-> new FraiseCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        CARROTCAKE_BLOCK = BLOCKS.register("carrotcake_block", ()-> new CarrotCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        CHOCOCAKE_BLOCK = BLOCKS.register("chococake_block", ()-> new ChocoCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        CHEESECAKE_BLOCK = BLOCKS.register("cheesecake_block", ()-> new CheeseCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        BLACKFORESTCAKE_BLOCK = BLOCKS.register("blackforestcake_block", ()-> new BlackForestCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
        // 树木相关方块
        MAPLE_WOOD = BLOCKS.register("maple_wood", () -> new ModFailableRotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW).ignitedByLava()));
        MAPLE_LEAVES_LIME =BLOCKS.register("maple_leaves_lime", () -> new FlammableLeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_GREEN).noOcclusion()));
        MAPLE_LEAVES_YELLOW =BLOCKS.register("maple_leaves_yellow", () -> new FlammableLeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_YELLOW).noOcclusion()));
        MAPLE_LEAVES_RED =BLOCKS.register("maple_leaves_red", () -> new FlammableLeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_RED).noOcclusion()));
        MAPLE_LEAVES_ORANGE =BLOCKS.register("maple_leaves_orange", () -> new FlammableLeavesBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_ORANGE).noOcclusion()));
        MAPLES_YELLOW = BLOCKS.register("maples_yellow", () -> new SaplingBlock(ModTreeGrowers.MAPLE_YELLOW_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        MAPLES_LIME = BLOCKS.register("maples_lime", () -> new SaplingBlock(ModTreeGrowers.MAPLE_LIME_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        MAPLES_ORANGE = BLOCKS.register("maples_orange", () -> new SaplingBlock(ModTreeGrowers.MAPLE_ORANGE_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        MAPLES_RED = BLOCKS.register("maples_red", () -> new SaplingBlock(ModTreeGrowers.MAPLE_RED_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        SAKURA = BLOCKS.register("sakura", () -> new SakuraSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
        // 樱花树叶 - 支持 16 种颜色变体，使用 variant 属性管理
        SAKURA_LEAVES = BLOCKS.register(
                "sakura_leaves",
                () -> new SakuraLeavesBlock(
                        BlockBehaviour.Properties.of()
                                .strength(0.2f)
                                .sound(SoundType.GRASS)
                                .mapColor(MapColor.COLOR_ORANGE)
                                .noOcclusion()
                                .lightLevel((state) -> 15)
                )
        );
        SAKURA_WOOD = BLOCKS.register("sakura_wood", () -> new ModFailableRotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW).ignitedByLava()));
        SAKURA_PLANK = BLOCKS.register("sakura_plank", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW)));


        // 糖和巧克力相关方块
        BRICKSUCRE =    BLOCKS.register("bricksucre", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));
        CARAM =    BLOCKS.register("caram", () -> new Block(BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));
        CHOCOBLOC =    BLOCKS.register("chocobloc", () -> new Block(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));

        // BRICKSUCRE variants
        BRICKSUCRE_SLAB = BLOCKS.register("bricksucre_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));
        BRICKSUCRE_STAIRS = BLOCKS.register("bricksucre_stairs", () -> new StairBlock(BRICKSUCRE.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));
        BRICKSUCRE_FENCE = BLOCKS.register("bricksucre_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));
        BRICKSUCRE_FENCE_GATE = BLOCKS.register("bricksucre_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));
        BRICKSUCRE_TRAPDOOR = BLOCKS.register("bricksucre_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.SAND)));

        // CARAM variants
        CARAM_SLAB = BLOCKS.register("caram_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));
        CARAM_STAIRS = BLOCKS.register("caram_stairs", () -> new StairBlock(CARAM.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));
        CARAM_FENCE = BLOCKS.register("caram_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));
        CARAM_FENCE_GATE = BLOCKS.register("caram_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));
        CARAM_TRAPDOOR = BLOCKS.register("caram_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(1.8f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_ORANGE)));

        // CHOCOBLOC variants
        CHOCOBLOC_SLAB = BLOCKS.register("chocobloc_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));
        CHOCOBLOC_STAIRS = BLOCKS.register("chocobloc_stairs", () -> new StairBlock(CHOCOBLOC.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));
        CHOCOBLOC_FENCE = BLOCKS.register("chocobloc_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));
        CHOCOBLOC_FENCE_GATE = BLOCKS.register("chocobloc_fence_gate", () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));
        CHOCOBLOC_TRAPDOOR = BLOCKS.register("chocobloc_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(1.5f, 6.0f).sound(SoundType.METAL).mapColor(MapColor.COLOR_BROWN)));

        // 甜枫木 - 特殊掉落物
        WOOD_SPILE =    BLOCKS.register("woodspile", () -> new WoodSpileBlock(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW)));

        // 落叶堆
        LEAVES_RED_FAST = BLOCKS.register("leaves_red_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_RED).noOcclusion().ignitedByLava()));
        LEAVES_YELLOW_FAST = BLOCKS.register("leaves_yellow_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_YELLOW).noOcclusion().ignitedByLava()));
        LEAVES_ORANGE_FAST = BLOCKS.register("leaves_orange_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_ORANGE).noOcclusion().ignitedByLava()));
        LEAVES_LIME_FAST = BLOCKS.register("leaves_lime_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_GREEN).noOcclusion().ignitedByLava()));
        LEAVES_SAKURA_FAST = BLOCKS.register("leaves_sakura_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_ORANGE).noOcclusion().ignitedByLava()));
        LEAVES_SAKURA_RED_FAST = BLOCKS.register("leaves_sakura_red_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_RED).noOcclusion().ignitedByLava()));
        LEAVES_SAKURA_YELLOW_FAST = BLOCKS.register("leaves_sakura_yellow_fast", () -> new LeafPileBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.GRASS).mapColor(MapColor.COLOR_YELLOW).noOcclusion().ignitedByLava()));

        // 樱木建筑方块
        SAKURA_STAIRS = BLOCKS.register("sakura_stairs", () -> new StairBlock(SAKURA_PLANK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW)));
        SAKURA_SLAB = BLOCKS.register("sakura_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0f).sound(SoundType.WOOD).mapColor(MapColor.COLOR_YELLOW)));

        }
}
