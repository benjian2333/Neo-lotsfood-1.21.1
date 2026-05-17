package lotsoffood.common.worldgen.feature;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.worldgen.feature.ModOrePlacement;
import lotsoffood.common.worldgen.feature.placement.TreeSafetyFilter;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class ModPlacedFeatures {
    // 小型红枫钻石矿脉放置特征
    public static final ResourceKey<PlacedFeature> MAPLE_DIAMOND_SMALL_KEY = registerKey("maple_diamond_ore_small_placed");
    // 大型红枫钻石矿脉放置特征
    public static final ResourceKey<PlacedFeature> MAPLE_DIAMOND_LARGE_KEY = registerKey("maple_diamond_ore_large_placed");
    // 被掩埋的红枫钻石矿脉放置特征
    public static final ResourceKey<PlacedFeature> MAPLE_DIAMOND_BURIED_KEY = registerKey("maple_diamond_ore_buried_placed");
    // 中型红枫钻石矿脉放置特征（深层专用）
    public static final ResourceKey<PlacedFeature> MAPLE_DIAMOND_MEDIUM_KEY = registerKey("maple_diamond_ore_medium_placed");

    public static final ResourceKey<PlacedFeature> RED_MAPLE_WOOD_KEY = registerKey("red_maple_wood_placed");

    public static final ResourceKey<PlacedFeature> LIME_MAPLE_WOOD_KEY = registerKey("lime_maple_wood_placed");
    public static final ResourceKey<PlacedFeature> ORANGE_MAPLE_WOOD_KEY = registerKey("orange_maple_wood_placed");
    public static final ResourceKey<PlacedFeature> YELLOW_MAPLE_WOOD_KEY = registerKey("yellow_maple_wood_placed");

    public static final ResourceKey<PlacedFeature> SAKURA_WOOD_KEY = registerKey("sakura_wood_placed");
    public static final ResourceKey<PlacedFeature> COCONUT_WOOD_KEY = registerKey("coconut_wood_placed");
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // 小型矿脉：每区块尝试 7 次，Y=-80~80 三角形分布（类似原版 ORE_DIAMOND）
        register(context, MAPLE_DIAMOND_SMALL_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_DIAMOND_SMALL_KEY),
                lotsoffood.common.worldgen.feature.ModOrePlacement.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        // 中型矿脉：每区块尝试 2 次，Y=-64~-4 均匀分布，专门在深层生成（类似原版 ORE_DIAMOND_MEDIUM）
        register(context, MAPLE_DIAMOND_MEDIUM_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_DIAMOND_MEDIUM_KEY),
                lotsoffood.common.worldgen.feature.ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))));

        // 大型矿脉：稀有度过滤（9 次尝试中成功 1 次），Y=-80~80 三角形分布（类似原版 ORE_DIAMOND_LARGE）
        register(context, MAPLE_DIAMOND_LARGE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_DIAMOND_LARGE_KEY),
                lotsoffood.common.worldgen.feature.ModOrePlacement.rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        // 被掩埋的矿脉：每区块尝试 4 次，Y=-80~80 三角形分布（类似原版 ORE_DIAMOND_BURIED）
        register(context, MAPLE_DIAMOND_BURIED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_DIAMOND_BURIED_KEY),
                ModOrePlacement.commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        // 树木生成：极低的密度，大约每 10-20 个区块生成一棵树
        // 添加 TreeSafetyFilter 防止与原版树木冲突
        List<PlacementModifier> limeModifiers = List.of(
            PlacementUtils.countExtra(0, 0.02F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            // 注意：TreeSafetyFilter 必须在高度图之后执行，确保它检查的是“真实地表落点”的 pos.y
            // 否则会出现误判，导致模组树在原版树叶/原木遮挡的区域仍然尝试生成。
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context, LIME_MAPLE_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LIME_MAPLE_WOOD_KEY),
                limeModifiers);

        List<PlacementModifier> orangeModifiers = List.of(
            PlacementUtils.countExtra(0, 0.02F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context, ORANGE_MAPLE_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ORANGE_MAPLE_WOOD_KEY),
                orangeModifiers);

        List<PlacementModifier> yellowModifiers = List.of(
            PlacementUtils.countExtra(0, 0.02F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context,YELLOW_MAPLE_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_MAPLE_WOOD_KEY),
                yellowModifiers);

        List<PlacementModifier> redModifiers = List.of(
            PlacementUtils.countExtra(0, 0.02F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context, RED_MAPLE_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_MAPLE_WOOD_KEY),
                redModifiers);

        List<PlacementModifier> sakuraModifiers = List.of(
            PlacementUtils.countExtra(0, 0.05F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context, SAKURA_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SAKURA_WOOD_KEY),
                sakuraModifiers);

        List<PlacementModifier> coconutModifiers = List.of(
            PlacementUtils.countExtra(0, 0.05F, 1),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            new TreeSafetyFilter(false, 8),
            net.minecraft.world.level.levelgen.placement.BiomeFilter.biome()
        );
        register(context, COCONUT_WOOD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.COCONUT_WOOD_KEY),
                coconutModifiers);

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}