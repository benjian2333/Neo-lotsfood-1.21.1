package lotsoffood.common.registry;

import java.util.function.Supplier;
import lotsoffood.LotsfoodMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.component.BlockItemStateProperties;
import lotsoffood.common.block.SakuraLeavesBlock;
import lotsoffood.common.block.type.SakuraVariantColors;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS;
    public static final Supplier<CreativeModeTab> TAB_FARMERS_DELIGHT;
    public static final Supplier<CreativeModeTab> TAB_BLOCKS;

    public static final ResourceKey<CreativeModeTab> TAB_KEY_ITEMS = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "lotsoffood")
    );
    public static final ResourceKey<CreativeModeTab> TAB_KEY_BLOCKS = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(LotsfoodMod.MODID, "lotsoffood_blocks")
    );

    static {
        CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LotsfoodMod.MODID);

        TAB_FARMERS_DELIGHT = CREATIVE_TABS.register("lotsoffood", () -> CreativeModeTab.builder()
                .title(Component.translatable("creativetab.lotsoffood.lotsfood_items"))
                .icon(() -> new ItemStack((ItemLike)ModItems.GLASSCUP.get()))
                .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().stream()
                        .map(holder -> holder.value())
                        .filter(item -> !(item instanceof BlockItem))
                        .forEach(output::accept))
                .build());
        TAB_BLOCKS = CREATIVE_TABS.register("lotsoffood_blocks", () -> CreativeModeTab.builder()
                .title(Component.translatable("creativetab.lotsoffood.lotsfood_blocks"))
                .icon(() -> new ItemStack((ItemLike) ModBlocks.CARAM.get()))
                // Put this tab right next to the main Lots of Food tab.
                .withTabsBefore(TAB_KEY_ITEMS)
                .displayItems((parameters, output) -> ModItems.ITEMS.getEntries().stream()
                        .map(holder -> holder.value())
                        .filter(item -> item instanceof BlockItem)
                        .forEach(item -> {
                            if (item == ModItems.SAKURA_LEAVES.get()) {
                                for (DyeColor color : DyeColor.values()) {
                                    // Default SAKURA_LEAVES is the raw white texture (no tint).
                                    // Don't expose an extra "white variant" item, and hide some dark colors.
                                    if (!SakuraVariantColors.isEnabledVariant(color)) {
                                        continue;
                                    }
                                    ItemStack stack = new ItemStack(item);
                                    stack.set(
                                            DataComponents.BLOCK_STATE,
                                            BlockItemStateProperties.EMPTY.with(SakuraLeavesBlock.VARIANT, color)
                                    );
                                    output.accept(stack);
                                }
                            } else {
                                output.accept(item);
                            }
                        }))
                .build());
    }
}
