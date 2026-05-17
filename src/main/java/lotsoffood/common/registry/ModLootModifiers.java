package lotsoffood.common.registry;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import lotsoffood.common.loot.modifier.AddItemModifier;
import lotsoffood.common.loot.modifier.LFAddTableLootModifier;
import lotsoffood.common.loot.modifier.ReplaceItemModifier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS;
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REPLACE_ITEM;
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_LOOT_TABLE;

    static {
        LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "lotsoffood");
        REPLACE_ITEM = LOOT_MODIFIERS.register("replace_item", ReplaceItemModifier.CODEC);
        ADD_LOOT_TABLE = LOOT_MODIFIERS.register("add_loot_table", LFAddTableLootModifier.CODEC);
    }
}