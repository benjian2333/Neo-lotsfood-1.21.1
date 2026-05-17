package lotsoffood;

import lotsoffood.common.loot.modifier.LFModLootModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lotsoffood.common.registry.ModBiomeFeatures;
import lotsoffood.common.registry.ModBiomeModifiers;
import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.registry.ModCreativeTabs;
import lotsoffood.common.registry.ModItems;
import lotsoffood.common.registry.ModLootFunctions;
import lotsoffood.common.registry.ModLootModifiers;
import lotsoffood.common.registry.ModPlacementModifiers;
import lotsoffood.common.registry.ModRecipeSerializers;
import lotsoffood.common.registry.ModStructures;
@Mod("lotsoffood")
public class LotsfoodMod {
    public static final String MODID = "lotsoffood";
    public static final Logger LOGGER = LogManager.getLogger();

    public LotsfoodMod(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModBiomeFeatures.FEATURES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        ModPlacementModifiers.PLACEMENT_MODIFIERS.register(modEventBus);
        ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        ModLootFunctions.LOOT_FUNCTIONS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        LFModLootModifier.LOOT_MODIFIERS_SERIALIZERS.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModStructures.STRUCTURE_PIECES.register(modEventBus);
    }
}
