package lotsoffood.common.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static final TagKey<Block> STRAW_BLOCKS = modBlockTag("straw_blocks");
    public static final TagKey<Block> WILD_CROPS = modBlockTag("wild_crops");
    public static final TagKey<Block> HEAT_SOURCES = modBlockTag("heat_sources");
    public static final TagKey<Block> HEAT_CONDUCTORS = modBlockTag("heat_conductors");
    public static final TagKey<Block> TRAY_HEAT_SOURCES = modBlockTag("tray_heat_sources");
    public static final TagKey<Item> KNIFE_ENCHANTABLE = modItemTag("enchantable/knife");
    public static final TagKey<Item> SNACKS = modItemTag("snacks");
    public static final TagKey<Item> MEALS = modItemTag("meals");
    public static final TagKey<Item> DRINKS = modItemTag("drinks");
    public static final TagKey<Item> SWEETS = modItemTag("sweets");
    public static final TagKey<Item> FEASTS = modItemTag("feasts");
    public static final TagKey<Item> MAPLEDIAMOND_ITEMS = modItemTag("maplediamond_items");
    public static final TagKey<Item> WILD_CROPS_ITEM = modItemTag("wild_crops");
    public static final TagKey<Item> SERVING_CONTAINERS = modItemTag("serving_containers");
    public static final TagKey<Block> NEEDS_MAPLE_DIAMOND_TOOL = modBlockTag("needs_maple_diamond_tool");
    public static final TagKey<Block> INCORRECT_FOR_MAPLE_DIAMOND_TOOL = modBlockTag("incorrect_for_maple_diamond_tool");
    public static final TagKey<Item> MAPLE_SWORD = modItemTag("maple_sword");
    public static final TagKey<Item> MAPLE_PICKAXE = modItemTag("maple_pickaxe");
    public static final TagKey<Item> MAPLE_AXE = modItemTag("maple_axe");
    public static final TagKey<Item> MAPLE_SHOVEL = modItemTag("maple_shovels");
    public static final TagKey<Item> MAPLE_HOE = modItemTag("maple_hoes");


    private static TagKey<Item> modItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("lotsoffood", path));
    }

    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("lotsoffood", path));
    }

    private static TagKey<EntityType<?>> modEntityTag(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("lotsoffood", path));
    }
}
