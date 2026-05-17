package lotsoffood.common.registry;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.neoforge.registries.DeferredRegister;
import lotsoffood.common.worldgen.filter.BiomeTagFilter;
import lotsoffood.common.worldgen.feature.placement.TreeSafetyFilter;

public class ModPlacementModifiers {
    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS;
    public static final Supplier<PlacementModifierType<BiomeTagFilter>> BIOME_TAG;
    public static final Supplier<PlacementModifierType<TreeSafetyFilter>> TREE_SAFETY_FILTER;

    private static <P extends PlacementModifier> PlacementModifierType<P> typeConvert(MapCodec<P> codec) {
        return () -> codec;
    }

    static {
        PLACEMENT_MODIFIERS = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE.key(), "lotsoffood");
        BIOME_TAG = PLACEMENT_MODIFIERS.register("biome_tag", () -> typeConvert(BiomeTagFilter.CODEC));
        TREE_SAFETY_FILTER = PLACEMENT_MODIFIERS.register("tree_safety_filter", () -> typeConvert(TreeSafetyFilter.CODEC));
    }
}
