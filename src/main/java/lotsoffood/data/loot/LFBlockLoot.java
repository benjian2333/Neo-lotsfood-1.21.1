package lotsoffood.data.loot;

import java.util.HashSet;
import java.util.Set;

import lotsoffood.common.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import lotsoffood.common.registry.ModBlocks;
public class LFBlockLoot extends BlockLootSubProvider {
    private final Set<Block> generatedLootTables = new HashSet();

    public LFBlockLoot(HolderLookup.Provider holder) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), holder);
    }

    protected void generate() {
        // 椰子方块：掉落 1 个椰子，50% 概率掉落 1 个椰子树苗
        this.add(ModBlocks.COCONUT_BLOCK.get(), block -> this.createCoconutBlockDrops(block));
        this.dropSelf(ModBlocks.COCONUT_SAPLING.get());
        this.dropSelf(ModBlocks.MAPLE_WOOD.get());
        this.dropSelf(ModBlocks.BRICKSUCRE.get());
        this.dropSelf(ModBlocks.WOOD_SPILE.get());
        this.dropSelf(ModBlocks.SAKURA.get());
        this.dropSelf(ModBlocks.SAKURA_PLANK.get());
        this.dropSelf(ModBlocks.CARAM.get());
        this.dropSelf(ModBlocks.CHOCOBLOC.get());

        // BRICKSUCRE variants
        this.dropSelf(ModBlocks.BRICKSUCRE_SLAB.get());
        this.dropSelf(ModBlocks.BRICKSUCRE_STAIRS.get());
        this.dropSelf(ModBlocks.BRICKSUCRE_FENCE.get());
        this.dropSelf(ModBlocks.BRICKSUCRE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.BRICKSUCRE_TRAPDOOR.get());

        // CARAM variants
        this.dropSelf(ModBlocks.CARAM_SLAB.get());
        this.dropSelf(ModBlocks.CARAM_STAIRS.get());
        this.dropSelf(ModBlocks.CARAM_FENCE.get());
        this.dropSelf(ModBlocks.CARAM_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CARAM_TRAPDOOR.get());

        // CHOCOBLOC variants
        this.dropSelf(ModBlocks.CHOCOBLOC_SLAB.get());
        this.dropSelf(ModBlocks.CHOCOBLOC_STAIRS.get());
        this.dropSelf(ModBlocks.CHOCOBLOC_FENCE.get());
        this.dropSelf(ModBlocks.CHOCOBLOC_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CHOCOBLOC_TRAPDOOR.get());

        this.dropSelf(ModBlocks.MAPLE_DIAMOND_BLOCK.get());

        // 枫木钻石矿：需要铁镐或更高等级镐子挖掘，受时运影响
        this.add(ModBlocks.MAPLE_DIAMOND_ORE.get(), block -> this.createOreDrop(block, ModItems.MAPLE_DIAMOND.get()));
        this.add(ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get(), block -> this.createOreDrop(block, ModItems.MAPLE_DIAMOND.get()));
            
        // 枫叶树叶掉落：使用剪刀或精准采集工具掉落树叶本身，否则概率掉落树苗和木棍
        this.add(ModBlocks.MAPLE_LEAVES_ORANGE.get(), block -> this.createLeavesDrops(block, ModBlocks.MAPLES_ORANGE.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.MAPLE_LEAVES_YELLOW.get(), block -> this.createLeavesDrops(block, ModBlocks.MAPLES_YELLOW.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.MAPLE_LEAVES_RED.get(), block -> this.createLeavesDrops(block, ModBlocks.MAPLES_RED.get(), NORMAL_LEAVES_SAPLING_CHANCES));
           
        this.add(ModBlocks.MAPLE_LEAVES_LIME.get(), block -> this.createLeavesDrops(block, ModBlocks.MAPLES_LIME.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            
        // 樱花树叶掉落：除了掉落树苗外，还有几率额外掉落樱桃（概率同原版橡树落叶掉苹果）
        this.add(ModBlocks.SAKURA_LEAVES.get(), block -> this.createSakuraLeavesDrops(block));

        // 落叶堆
        this.dropSelf(ModBlocks.LEAVES_RED_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_YELLOW_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_ORANGE_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_LIME_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_SAKURA_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_SAKURA_RED_FAST.get());
        this.dropSelf(ModBlocks.LEAVES_SAKURA_YELLOW_FAST.get());
        this.dropSelf(ModBlocks.SAKURA_PLANK.get());
        this.dropSelf(ModBlocks.SAKURA_STAIRS.get());
        this.dropSelf(ModBlocks.SAKURA_SLAB.get());
    }

    protected void dropNamedContainer(Block block) {
        this.add(block, (x$0) -> this.createNameableBlockEntityTable(x$0));
    }

    protected void add(Block block, LootTable.Builder builder) {
        this.generatedLootTables.add(block);
        this.map.put(block.getLootTable(), builder);
    }

    protected Iterable<Block> getKnownBlocks() {
        return this.generatedLootTables;
    }
    
    /**
     * 椰子方块掉落：精准采集掉落自身，否则掉落 1 个椰子 + 50% 概率掉落 1 个椰子树苗
     */
    protected LootTable.Builder createCoconutBlockDrops(Block block) {
        return LootTable.lootTable()
            .withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(
                        LootItem.lootTableItem(block)
                            .when(this.hasSilkTouch())
                            .otherwise(LootItem.lootTableItem(ModItems.COCONUT.get()))
                    )
            )
            .withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .when(this.hasSilkTouch().invert())
                    .add(
                        LootItem.lootTableItem(ModItems.COCONUT_SAPLING.get())
                            .when(LootItemRandomChanceCondition.randomChance(0.5F))
                    )
            );
    }

    /**
     * 樱花树叶掉落：树苗 + 樱桃（概率同原版橡树落叶掉苹果）
     * 无时运：0.5% 掉落率
     * 时运 I: 0.556% 掉落率
     * 时运 II: 0.625% 掉落率
     * 时运 III: 0.833% 掉落率
     * 时运 IV+: 2.5% 掉落率
     */
    protected LootTable.Builder createSakuraLeavesDrops(Block block) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 基础树叶掉落（树苗和木棍）
        return this.createLeavesDrops(block, ModBlocks.SAKURA.get(), NORMAL_LEAVES_SAPLING_CHANCES)
            // 额外添加樱桃掉落，概率参照原版橡树落叶掉苹果
            .withPool(
                LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    // 没有剪刀或精准采集时才掉落樱桃
                    .when(HAS_SHEARS.or(this.hasSilkTouch()).invert())
                    .add(
                        ((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(
                                block, LootItem.lootTableItem(ModItems.CHERRY.get())))
                            .when(
                                BonusLevelTableCondition.bonusLevelFlatChance(
                                    registrylookup.getOrThrow(Enchantments.FORTUNE), 
                                    0.05F,
                                    0.1F,
                                    0.15F,
                                    0.2F
                                )
                            )
                    )
            );
    }
}