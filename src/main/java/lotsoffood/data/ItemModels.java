package lotsoffood.data;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lotsoffood.LotsfoodMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import lotsoffood.LotsfoodMod;
import lotsoffood.common.registry.ModItems;

public class ItemModels extends ItemModelProvider {
    public static final String GENERATED = "item/generated";
    public static final String HANDHELD = "item/handheld";
    public static final ResourceLocation MUG = ResourceLocation.fromNamespaceAndPath("lotsoffood", "item/mug");

    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "lotsoffood", existingFileHelper);
    }

    protected void registerModels() {
        Set<Item> items = (Set)BuiltInRegistries.ITEM.stream().filter((i) -> "lotsoffood".equals(BuiltInRegistries.ITEM.getKey(i).getNamespace())).collect(Collectors.toSet());
       /* Set<Item> mugItems = Sets.newHashSet(new Item[]{(Item)ModItems.APPLE_JUICE.get(), (Item)ModItems.APPLE_CIDER.get(), (Item)ModItems.MELON_JUICE.get()});
        takeAll(items, (Item[])mugItems.toArray(new Item[0])).forEach((item) -> this.itemMugModel(item, this.resourceItem(this.itemName(item))));

        */
        Set<Item> handheldItems = Sets.newHashSet(new Item[]{(Item)ModItems.MAPLE_HOE.get(), (Item)ModItems.MAPLE_PICKAXE.get(), (Item)ModItems.MAPLE_AXE.get(), (Item)ModItems.MAPLE_SHOVEL.get(), (Item)ModItems.MAPLE_SWORD.get()});
        takeAll(items, (Item[])handheldItems.toArray(new Item[0])).forEach((item) -> this.itemHandheldModel(item, this.resourceItem(this.itemName(item))));

        items.forEach((item) -> {
            ResourceLocation itemTexture = this.resourceItem(this.itemName(item));

            // If an item texture exists, keep the usual flat generated model.
            if (this.hasItemTexture(itemTexture)) {
                this.itemGeneratedModel(item, itemTexture);
                return;
            }

            // For block items, fall back to the block model so the item uses block textures.
            // Exclude ItemNameBlockItem (seeds/produce) which should remain flat items.
            if (item instanceof BlockItem && !(item instanceof ItemNameBlockItem)) {
                // Some BlockItems intentionally reuse a differently-named block model.
                String itemId = this.itemName(item);
                String blockModelId = itemId.equals("coconut_block") ? "cocobloc" : itemId;

                ResourceLocation blockModel = this.resourceBlock(blockModelId);
                if (this.existingFileHelper.exists(blockModel, ModelProvider.MODEL)) {
                    // Use the mapped model name directly to avoid relying on item/block id matching.
                    this.withExistingParent(itemId, blockModel);
                    return;
                }
            }

            // Otherwise, preserve previous behavior: warn and skip.
            this.warnMissingItemTexture(item, itemTexture);
        });


    }

    public void blockBasedModel(Item item, String suffix) {
        String itemName = this.itemName(item);
        
        // For BlockItems, reference the block model directly
        if (item instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) item;
            String blockName = BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).getPath();

            // Special-case: coconut_block uses the cocobloc block model/texture set.
            if (blockName.equals("coconut_block")) {
                this.withExistingParent(itemName, this.resourceBlock("cocobloc"));
                return;
            }
            
            // For crops with stages, use the last stage for the item model
            // Check if this is a staged crop by looking for common patterns
            if (blockName.contains("tomate") || blockName.contains("strawberry") || 
                blockName.contains("chili") || blockName.contains("grape") || 
                blockName.contains("vanille") || blockName.contains("cafebeans")) {
                // These crops use _stageX naming, use highest stage (typically stage5 or stage4)
                this.withExistingParent(itemName, this.resourceBlock(blockName + "_stage5"));
            } else if (blockName.contains("mais")) {
                // Mais uses different naming: mais_block_bottom/top
                // MAX_AGE=4, so use stage4 for the item model
                if (blockName.equals("cropmaistop")) {
                    this.withExistingParent(itemName, this.resourceBlock("mais_block_top_stage4"));
                } else if (blockName.equals("cropmaisbottom")) {
                    this.withExistingParent(itemName, this.resourceBlock("mais_block_bottom_stage4"));
                } else {
                    this.withExistingParent(itemName, this.resourceBlock(blockName));
                }
            } else {
                // Regular blocks without stages
                this.withExistingParent(itemName, this.resourceBlock(blockName));
            }
        } else {
            // Fallback for non-BlockItems
            this.withExistingParent(itemName, this.resourceBlock(itemName + suffix));
        }
    }

    public void itemHandheldModel(Item item, ResourceLocation texture) {
        if (!this.hasItemTexture(texture)) {
            this.warnMissingItemTexture(item, texture);
            return;
        }
        ((ItemModelBuilder)this.withExistingParent(this.itemName(item), "item/handheld")).texture("layer0", texture);
    }

    public void itemGeneratedModel(Item item, ResourceLocation texture) {
        if (!this.hasItemTexture(texture)) {
            this.warnMissingItemTexture(item, texture);
            return;
        }
        ((ItemModelBuilder)this.withExistingParent(this.itemName(item), "item/generated")).texture("layer0", texture);
    }

    public void itemMugModel(Item item, ResourceLocation texture) {
        ((ItemModelBuilder)this.withExistingParent(this.itemName(item), MUG)).texture("layer0", texture);
    }

    private String itemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath("lotsoffood", "block/" + path);
    }

    public ResourceLocation resourceItem(String path) {
        return ResourceLocation.fromNamespaceAndPath("lotsoffood", "item/" + path);
    }

    private boolean hasItemTexture(ResourceLocation texture) {
        return this.existingFileHelper.exists(texture, ModelProvider.TEXTURE);
    }

    private void warnMissingItemTexture(Item item, ResourceLocation texture) {
        LotsfoodMod.LOGGER.warn(
                "Datagen skipped item model for {} because texture is missing: {}",
                BuiltInRegistries.ITEM.getKey(item),
                texture
        );
    }

    @SafeVarargs
    public static <T> Collection<T> takeAll(Set<? extends T> src, T... items) {
        List<T> ret = Arrays.asList(items);

        for(T item : items) {
            if (!src.contains(item)) {
                LotsfoodMod.LOGGER.warn("Item {} not found in set", item);
            }
        }

        if (!src.removeAll(ret)) {
            LotsfoodMod.LOGGER.warn("takeAll array didn't yield anything ({})", Arrays.toString(items));
        }

        return ret;
    }

    public static <T> Collection<T> takeAll(Set<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList();
        Iterator<T> iter = src.iterator();

        while(iter.hasNext()) {
            T item = (T)iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            LotsfoodMod.LOGGER.warn("takeAll predicate yielded nothing", new Throwable());
        }

        return ret;
    }
}