package lotsoffood.common.registry;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import lotsoffood.common.worldgen.feature.CoconutTreeFeature;
import lotsoffood.common.worldgen.feature.ModSafeTreeFeature;

/*import lotsoffood.common.world.feature.WildRiceFeature;*/

public class ModBiomeFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES;
    public static final Supplier<Feature<?>> MOD_SAFE_TREE;
    public static final Supplier<Feature<NoneFeatureConfiguration>> COCONUT_TREE;
    /*public static final Supplier<Feature<RandomPatchConfiguration>> WILD_RICE;*/


    static {
        FEATURES = DeferredRegister.create(Registries.FEATURE, "lotsoffood");
        MOD_SAFE_TREE = FEATURES.register("mod_safe_tree", () -> new ModSafeTreeFeature());
        COCONUT_TREE = FEATURES.register("coconut_tree", () -> new CoconutTreeFeature());
        /*WILD_RICE = FEATURES.register("wild_rice", () -> new WildRiceFeature(RandomPatchConfiguration.CODEC));*/

    }
}