package lotsoffood;

import lotsoffood.common.block.SakuraLeavesBlock;
import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;


// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = LotsfoodMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = LotsfoodMod.MODID, value = Dist.CLIENT)
public class LotsfoodModClient {
    public LotsfoodModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        LotsfoodMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        LotsfoodMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }


    @SubscribeEvent
    static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, level, pos, tintIndex) -> tintIndex == 0
                        ? (state.getValue(SakuraLeavesBlock.VARIANT) == DyeColor.WHITE
                            ? -1
                            : state.getValue(SakuraLeavesBlock.VARIANT).getTextureDiffuseColor())
                        : -1,
                ModBlocks.SAKURA_LEAVES.get()
        );
    }

    @SubscribeEvent
    static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex != 0) return -1;

            BlockItemStateProperties props = stack.get(DataComponents.BLOCK_STATE);
            if (props != null) {
                DyeColor color = props.get(SakuraLeavesBlock.VARIANT);
                if (color != null) {
                    return color == DyeColor.WHITE ? -1 : color.getTextureDiffuseColor();
                }
            }

            // Default item (no blockstate component) should render as the raw white texture.
            return -1;
        }, ModItems.SAKURA_LEAVES.get());
    }

}
