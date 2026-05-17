package lotsoffood.common.worldgen.modifier;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.common.world.BiomeModifier.Phase;
import lotsoffood.common.registry.ModBiomeModifiers;

public record AddFeaturesByFilterBiomeModifier(HolderSet<Biome> allowedBiomes, Optional<HolderSet<Biome>> deniedBiomes, Optional<Float> minimumTemperature, Optional<Float> maximumTemperature, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier {
    public void modify(Holder<Biome> biome, BiomeModifier.Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.allowedBiomes.contains(biome)) {
            if (this.deniedBiomes.isPresent() && ((HolderSet)this.deniedBiomes.get()).contains(biome)) {
                return;
            }

            if (this.minimumTemperature.isPresent() && ((Biome)biome.value()).getBaseTemperature() < (Float)this.minimumTemperature.get()) {
                return;
            }

            if (this.maximumTemperature.isPresent() && ((Biome)biome.value()).getBaseTemperature() > (Float)this.maximumTemperature.get()) {
                return;
            }

            BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
            this.features.forEach((holder) -> generationSettings.addFeature(this.step, holder));
        }

    }

    public MapCodec<? extends BiomeModifier> codec() {
        return (MapCodec)ModBiomeModifiers.ADD_FEATURES_BY_FILTER.get();
    }
}