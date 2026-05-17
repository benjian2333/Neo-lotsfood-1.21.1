package lotsoffood.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {
    /** @deprecated */
    @Deprecated
    public static final TagKey<Item> FOODS_COOKED_COD = commonItemTag("foods/cooked_cod");
    public static final TagKey<Item> FOODS_COOKED_SALMON = commonItemTag("foods/cooked_salmon");



    private static TagKey<Block> commonBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }

    private static TagKey<Item> commonItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
}