package lotsoffood.data;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import lotsoffood.common.registry.ModBlocks;
import lotsoffood.common.tag.ModTags;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "lotsoffood", existingFileHelper);
    }

    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.registerMinecraftTags();
        this.registerBlockMineables();
        this.registerModTags();
    }

    protected void registerBlockMineables() {
        this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(new Block[]{ModBlocks.MAPLE_WOOD.get(), ModBlocks.SAKURA_WOOD.get(), ModBlocks.SAKURA_PLANK.get(), ModBlocks.SAKURA_STAIRS.get(), ModBlocks.SAKURA_SLAB.get()});
        this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(new Block[]{});
        this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(new Block[]{
            ModBlocks.MAPLE_DIAMOND_ORE.get(), ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get(), ModBlocks.MAPLE_DIAMOND_BLOCK.get(),
            ModBlocks.BRICKSUCRE.get(), ModBlocks.BRICKSUCRE_SLAB.get(), ModBlocks.BRICKSUCRE_STAIRS.get(), ModBlocks.BRICKSUCRE_FENCE.get(), ModBlocks.BRICKSUCRE_TRAPDOOR.get(), ModBlocks.BRICKSUCRE_FENCE_GATE.get(),
            ModBlocks.CARAM.get(), ModBlocks.CARAM_SLAB.get(), ModBlocks.CARAM_STAIRS.get(), ModBlocks.CARAM_FENCE.get(), ModBlocks.CARAM_TRAPDOOR.get(), ModBlocks.CARAM_FENCE_GATE.get(),
            ModBlocks.CHOCOBLOC.get(), ModBlocks.CHOCOBLOC_SLAB.get(), ModBlocks.CHOCOBLOC_STAIRS.get(), ModBlocks.CHOCOBLOC_FENCE.get(), ModBlocks.CHOCOBLOC_TRAPDOOR.get(), ModBlocks.CHOCOBLOC_FENCE_GATE.get()
        });
        this.tag(net.minecraft.tags.BlockTags.FENCES).add(new Block[]{
            ModBlocks.BRICKSUCRE_FENCE.get(), ModBlocks.CARAM_FENCE.get(), ModBlocks.CHOCOBLOC_FENCE.get()
        });
        this.tag(net.minecraft.tags.BlockTags.FENCE_GATES).add(new Block[]{
            ModBlocks.BRICKSUCRE_FENCE_GATE.get(), ModBlocks.CARAM_FENCE_GATE.get(), ModBlocks.CHOCOBLOC_FENCE_GATE.get()
        });
        this.tag(net.minecraft.tags.BlockTags.TRAPDOORS).add(new Block[]{
            ModBlocks.BRICKSUCRE_TRAPDOOR.get(), ModBlocks.CARAM_TRAPDOOR.get(), ModBlocks.CHOCOBLOC_TRAPDOOR.get()
        });
        this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(new Block[]{
            ModBlocks.LEAVES_RED_FAST.get(), ModBlocks.LEAVES_YELLOW_FAST.get(), ModBlocks.LEAVES_ORANGE_FAST.get(), ModBlocks.LEAVES_LIME_FAST.get(),
            ModBlocks.LEAVES_SAKURA_FAST.get(), ModBlocks.LEAVES_SAKURA_RED_FAST.get(), ModBlocks.LEAVES_SAKURA_YELLOW_FAST.get()
        });
        this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL).add(new Block[]{});
        this.tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL).add(new Block[]{ModBlocks.MAPLE_DIAMOND_ORE.get(), ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get()});

    }

    protected void registerMinecraftTags() {
        this.tag(net.minecraft.tags.BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MAPLE_WOOD.get());
        this.tag(net.minecraft.tags.BlockTags.PLANKS).add(ModBlocks.SAKURA_PLANK.get());
        this.tag(net.minecraft.tags.BlockTags.STAIRS).add(ModBlocks.SAKURA_STAIRS.get());
        this.tag(net.minecraft.tags.BlockTags.SLABS).add(ModBlocks.SAKURA_SLAB.get());
        this.tag(net.minecraft.tags.BlockTags.SMALL_FLOWERS);
    }

    protected void registerModTags() {
        // 用 CookingPot 的托盘模型切换：锅放在这些"热源方块"上时展示托盘（tray）
        // 岩浆块仅作烹饪热源（HEAT_SOURCES），不触发托盘模型
        this.tag(ModTags.WILD_CROPS);
        this.tag(ModTags.TRAY_HEAT_SOURCES)
                .add(Blocks.LAVA)
                .addTag(net.minecraft.tags.BlockTags.CAMPFIRES)
                .addTag(net.minecraft.tags.BlockTags.FIRE);

        this.tag(ModTags.HEAT_SOURCES).addTag(ModTags.TRAY_HEAT_SOURCES).add(Blocks.MAGMA_BLOCK);
        this.tag(ModTags.HEAT_CONDUCTORS);
    }

    }