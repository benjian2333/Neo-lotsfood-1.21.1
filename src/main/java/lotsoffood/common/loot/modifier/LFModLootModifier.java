package lotsoffood.common.loot.modifier;

import com.mojang.serialization.MapCodec;
import lotsoffood.LotsfoodMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class LFModLootModifier {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, LotsfoodMod.MODID);
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM=
            LOOT_MODIFIERS_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);
    public static void register(IEventBus eventBus) {
        LOOT_MODIFIERS_SERIALIZERS.register(eventBus);
    }
}
