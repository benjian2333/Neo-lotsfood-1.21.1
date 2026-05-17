package lotsoffood.data;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import lotsoffood.data.loot.LFBlockLoot;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(
        modid = "lotsoffood"
)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        RegistrySetBuilder registrySetBuilder = (new RegistrySetBuilder())
                .add(Registries.CONFIGURED_FEATURE, lotsoffood.common.worldgen.feature.ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, lotsoffood.common.worldgen.feature.ModPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lotsoffood.common.worldgen.feature.ModBiomeModifiers::bootstrap)
                .add(Registries.STRUCTURE, lotsoffood.common.worldgen.structure.ModStructureBootstraps::bootstrapStructures)
                .add(Registries.STRUCTURE_SET, lotsoffood.common.worldgen.structure.ModStructureBootstraps::bootstrapStructureSets);
        BlockTags blockTags = new BlockTags(output, lookupProvider, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTags(output, lookupProvider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new EntityTags(output, lookupProvider, helper));
        DatapackBuiltinEntriesProvider datapackProvider = new DatapackBuiltinEntriesProvider(output, lookupProvider, registrySetBuilder, Set.of("lotsoffood"));
        CompletableFuture<HolderLookup.Provider> builtinLookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(event.includeServer(), datapackProvider);
        generator.addProvider(event.includeServer(), new EnchantmentTags(output, builtinLookupProvider, helper));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(output, lookupProvider));
        generator.addProvider(event.includeServer(), new Recipes(output, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(LFBlockLoot::new, LootContextParamSets.BLOCK)), lookupProvider));
        BlockStates blockStates = new BlockStates(output, helper);
        generator.addProvider(event.includeClient(), blockStates);
        generator.addProvider(event.includeClient(), new ItemModels(output, blockStates.models().existingFileHelper));
    }
}