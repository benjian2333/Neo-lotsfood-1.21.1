package lotsoffood.data;
import lotsoffood.LotsfoodMod;
import lotsoffood.common.worldgen.feature.ModConfiguredFeatures;
import lotsoffood.common.worldgen.feature.ModPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()


            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            ;


    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider>  registries) {
        super(output, registries, BUILDER, Set.of(LotsfoodMod.MODID));

    }
}