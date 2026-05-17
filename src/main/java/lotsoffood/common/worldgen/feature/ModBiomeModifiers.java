package lotsoffood.common.worldgen.feature;

import lotsoffood.LotsfoodMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // ==================== 矿石生成 ====================
        registerOres(context, placedFeatures);

        // ==================== 树木生成 ====================
        registerTrees(context, placedFeatures, biomes);
    }

    private static void registerOres(BootstrapContext<BiomeModifier> context, 
                                     HolderGetter<PlacedFeature> placedFeatures) {
        // 获取所有 4 种钻石矿脉 PlacedFeature
        Holder<PlacedFeature> mapleDiamondSmall = placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_DIAMOND_SMALL_KEY);
        Holder<PlacedFeature> mapleDiamondMedium = placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_DIAMOND_MEDIUM_KEY);
        Holder<PlacedFeature> mapleDiamondLarge = placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_DIAMOND_LARGE_KEY);
        Holder<PlacedFeature> mapleDiamondBuried = placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_DIAMOND_BURIED_KEY);

        // 创建 HolderSet，包含所有红枫钻石矿脉
        HolderSet<PlacedFeature> mapleDiamondOres = HolderSet.direct(
                mapleDiamondSmall,
                mapleDiamondMedium,
                mapleDiamondLarge,
                mapleDiamondBuried
        );

        // 创建生物群系修饰器，将所有矿石添加到所有主世界生物群系
        register(context, "add_maple_diamond_ore", new BiomeModifiers.AddFeaturesBiomeModifier(
                context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD),
                mapleDiamondOres,
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    private static void registerTrees(BootstrapContext<BiomeModifier> context,
                                      HolderGetter<PlacedFeature> placedFeatures,
                                      HolderGetter<Biome> biomes) {
        // 获取所有树木的 PlacedFeature
        Holder<PlacedFeature> sakuraTree = placedFeatures.getOrThrow(ModPlacedFeatures.SAKURA_WOOD_KEY);
        Holder<PlacedFeature> redTree = placedFeatures.getOrThrow(ModPlacedFeatures.RED_MAPLE_WOOD_KEY);
        Holder<PlacedFeature> orangeTree = placedFeatures.getOrThrow(ModPlacedFeatures.ORANGE_MAPLE_WOOD_KEY);
        Holder<PlacedFeature> yellowTree = placedFeatures.getOrThrow(ModPlacedFeatures.YELLOW_MAPLE_WOOD_KEY);
        Holder<PlacedFeature> limeTree = placedFeatures.getOrThrow(ModPlacedFeatures.LIME_MAPLE_WOOD_KEY);
        Holder<PlacedFeature> coconutTree = placedFeatures.getOrThrow(ModPlacedFeatures.COCONUT_WOOD_KEY);

        // 创建目标生物群系集合（森林和平原）
        HolderSet<net.minecraft.world.level.biome.Biome> targetBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.FOREST),
                biomes.getOrThrow(Biomes.PLAINS)
        );

        // 椰子树目标生物群系集合（仅海滩）
        HolderSet<net.minecraft.world.level.biome.Biome> beachBiomes = HolderSet.direct(
                biomes.getOrThrow(Biomes.BEACH)
        );

        // 为每种树创建独立的生物群系修饰器
        // 注意：这些树木会与原版树木共享生物群系，但由于概率极低，不会互相干扰
        register(context, "add_sakura_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(sakuraTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        register(context, "add_red_maple_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(redTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        register(context, "add_orange_maple_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(orangeTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        register(context, "add_yellow_maple_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(yellowTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        register(context, "add_lime_maple_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                targetBiomes,
                HolderSet.direct(limeTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        register(context, "add_coconut_tree", new BiomeModifiers.AddFeaturesBiomeModifier(
                beachBiomes,
                HolderSet.direct(coconutTree),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, name));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, BiomeModifier modifier) {
        context.register(registerKey(name), modifier);
    }
}
