package lotsoffood.data;


import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;

import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.tag.CommonTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import lotsoffood.common.registry.ModItems;
import lotsoffood.common.tag.ModTags;
public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, blockTagProvider, "lotsoffood", existingFileHelper);
    }

    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.copy(ModTags.WILD_CROPS, ModTags.WILD_CROPS_ITEM);
        this.copy(BlockTags.SMALL_FLOWERS, net.minecraft.tags.ItemTags.SMALL_FLOWERS);
        this.registerMinecraftTags();
        this.registerModTags();
        this.registerNeoForgeTags();
        this.registerCompatibilityTags();
        this.registerToolsTags();

    }

    private void registerMinecraftTags() {
        this.tag(net.minecraft.tags.ItemTags.LOGS_THAT_BURN).add(ModBlocks.MAPLE_WOOD.get().asItem());
        this.tag(net.minecraft.tags.ItemTags.PLANKS).add(ModBlocks.SAKURA_PLANK.get().asItem());
        this.copy(net.minecraft.tags.BlockTags.STAIRS, net.minecraft.tags.ItemTags.STAIRS);
        this.copy(net.minecraft.tags.BlockTags.SLABS, net.minecraft.tags.ItemTags.SLABS);
    }


    private void registerToolsTags() {
        this.tag(ModTags.MAPLE_AXE).add((Item)ModItems.MAPLE_AXE.get());
        this.tag(ModTags.MAPLE_PICKAXE).add((Item)ModItems.MAPLE_PICKAXE.get());
        this.tag(ModTags.MAPLE_SHOVEL).add((Item)ModItems.MAPLE_SHOVEL.get());
        this.tag(ModTags.MAPLE_HOE).add((Item)ModItems.MAPLE_HOE.get());
        this.tag(ModTags.MAPLE_SWORD).add((Item)ModItems.MAPLE_SWORD.get());
    }

    private void registerModTags() {
        //零食
        this.tag(ModTags.SNACKS);
                //正餐
        this.tag(ModTags.MEALS);
                //饮料
        this.tag(ModTags.DRINKS);
                //甜食
        this.tag(ModTags.SWEETS);
                //大餐
        this.tag(ModTags.FEASTS);
                //食物容器（烹饪锅容器槽、快捷移动）
        this.tag(ModTags.SERVING_CONTAINERS).add(ModItems.GLASSCUP.get());


    }

    private void registerNeoForgeTags() {
        // 先定义子标签
        this.tag(CommonTags.FOODS_COOKED_COD)
                .add(net.minecraft.world.item.Items.COOKED_COD);
        this.tag(CommonTags.FOODS_COOKED_SALMON)
                .add(net.minecraft.world.item.Items.COOKED_SALMON);
        
        // 再定义 cooked_fish 标签，引用子标签和模组物品
        this.tag(Tags.Items.FOODS_COOKED_FISH).addTags(
                CommonTags.FOODS_COOKED_COD,
                CommonTags.FOODS_COOKED_SALMON
        );
        this.tag(Tags.Items.FOODS_COOKED_FISH).add(
                ModItems.COOKEDTRUITECRUE.get(),
                ModItems.COOKEDBARCRU.get(),
                ModItems.COOKEDCALAMARCRU.get(),
                ModItems.COOKEDCOLINCRU.get(),
                ModItems.SOLECUTE.get()
        );
    }


    public void registerCompatibilityTags() {

    }
}