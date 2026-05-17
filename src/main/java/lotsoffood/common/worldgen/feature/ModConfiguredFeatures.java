package lotsoffood.common.worldgen.feature;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.block.SakuraLeavesBlock;
import lotsoffood.common.block.type.SakuraVariantColors;
import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.registry.ModBiomeFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    // 小型红枫钻石矿脉
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_DIAMOND_SMALL_KEY = registerKey("maple_diamond_ore_small");
    // 大型红枫钻石矿脉
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_DIAMOND_LARGE_KEY = registerKey("maple_diamond_ore_large");
    // 被掩埋的红枫钻石矿脉
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_DIAMOND_BURIED_KEY = registerKey("maple_diamond_ore_buried");
    // 中型红枫钻石矿脉（深层专用）
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_DIAMOND_MEDIUM_KEY = registerKey("maple_diamond_ore_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_DIAMOND_ORE_KEY = registerKey("maple_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_MAPLE_DIAMOND_ORE_KEY = registerKey("deepslate_maple_diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_WOOD_KEY = registerKey("red_maple_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_MAPLE_WOOD_KEY = registerKey("orange_maple_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIME_MAPLE_WOOD_KEY = registerKey("lime_maple_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MAPLE_WOOD_KEY = registerKey("yellow_maple_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_WOOD_KEY = registerKey("sakura_wood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_WOOD_KEY = registerKey("coconut_wood");

    public static ResourceKey<ConfiguredFeature<?, ?>> sakuraWoodKey(DyeColor color) {
        return color == DyeColor.WHITE ? SAKURA_WOOD_KEY : registerKey("sakura_wood_" + color.getName());
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldMapleDiamondOre = List.of(
                OreConfiguration.target(stoneReplaceable, ModBlocks.MAPLE_DIAMOND_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get().defaultBlockState())
        );

        // 小型矿脉：大小 4，50% 概率即使暴露也能生成
        register(context, MAPLE_DIAMOND_SMALL_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 4, 0.5F));

        // 大型矿脉：大小 12，70% 概率即使暴露也能生成
        register(context, MAPLE_DIAMOND_LARGE_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 12, 0.7F));

        // 被掩埋的矿脉：大小 8，100% 概率即使暴露也能生成
        register(context, MAPLE_DIAMOND_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 8, 1.0F));

        // 中型矿脉（深层）：大小 8，50% 概率即使暴露也能生成
        register(context, MAPLE_DIAMOND_MEDIUM_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 8, 0.5F));

        register(context, MAPLE_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 2, 0.5F));

        register(context, DEEPSLATE_MAPLE_DIAMOND_ORE_KEY, Feature.ORE, new OreConfiguration(overworldMapleDiamondOre, 2, 0.5F));

        register(context, RED_MAPLE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_WOOD.get()),
                new StraightTrunkPlacer(4, 2, 0),
                // 设置 persistent 属性防止自然凋落
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES_RED.get().defaultBlockState()
                    .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, Boolean.TRUE)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .dirt(BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT))
                .build());
        register(context, ORANGE_MAPLE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_WOOD.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES_ORANGE.get().defaultBlockState()
                    .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, Boolean.TRUE)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .dirt(BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT))
                .build());
        register(context, LIME_MAPLE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_WOOD.get()),
                new StraightTrunkPlacer(4, 2, 0 ),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES_LIME.get().defaultBlockState()
                    .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, Boolean.TRUE)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .dirt(BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT))
                .build());
        register(context, YELLOW_MAPLE_WOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MAPLE_WOOD.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.MAPLE_LEAVES_YELLOW.get().defaultBlockState()
                    .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, Boolean.TRUE)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0),3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .dirt(BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT))
                .build());
        registerSakuraTree(context, DyeColor.WHITE);
        for (DyeColor color : DyeColor.values()) {
            if (SakuraVariantColors.isEnabledVariant(color)) {
                registerSakuraTree(context, color);
            }
        }

        register(context, COCONUT_WOOD_KEY, ModBiomeFeatures.COCONUT_TREE.get(), NoneFeatureConfiguration.INSTANCE);

    }
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID,name));

    }
    @SuppressWarnings("unchecked")
    private static <FC extends FeatureConfiguration,F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                         ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>((Feature<FC>) feature, configuration));
    }

    private static void registerSakuraTree(BootstrapContext<ConfiguredFeature<?, ?>> context, DyeColor color) {
        register(context, sakuraWoodKey(color), Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SAKURA_WOOD.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(
                        ModBlocks.SAKURA_LEAVES.get().defaultBlockState()
                                .setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, Boolean.TRUE)
                                .setValue(SakuraLeavesBlock.VARIANT, color)
                ),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        )
                .ignoreVines()
                .dirt(BlockStateProvider.simple(net.minecraft.world.level.block.Blocks.DIRT))
                .build());
    }

}
