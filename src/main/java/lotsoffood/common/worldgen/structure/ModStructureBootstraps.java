package lotsoffood.common.worldgen.structure;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.Structures.GingerbreadHouse;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class ModStructureBootstraps {

    public static final ResourceKey<Structure> GINGERBREAD_HOUSE_KEY =
        ResourceKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "gingerbread_house"));

    public static final ResourceKey<StructureSet> GINGERBREAD_HOUSE_SET_KEY =
        ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "gingerbread_house_set"));

    public static void bootstrapStructures(BootstrapContext<Structure> context) {
        HolderGetter<net.minecraft.world.level.biome.Biome> biomes = context.lookup(Registries.BIOME);

        Structure.StructureSettings settings = new Structure.StructureSettings.Builder(
            biomes.getOrThrow(BiomeTags.IS_OVERWORLD))
            .generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES)
            .terrainAdapation(TerrainAdjustment.BEARD_THIN)
            .build();

        context.register(GINGERBREAD_HOUSE_KEY, new GingerbreadHouse(settings));
    }

    public static void bootstrapStructureSets(BootstrapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        Holder<Structure> gingerbreadHouse = structures.getOrThrow(GINGERBREAD_HOUSE_KEY);

        StructureSet set = new StructureSet(
            gingerbreadHouse,
            new RandomSpreadStructurePlacement(50, 30, RandomSpreadType.LINEAR, 783216409)
        );

        context.register(GINGERBREAD_HOUSE_SET_KEY, set);
    }
}
