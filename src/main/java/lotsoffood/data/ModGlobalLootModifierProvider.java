package lotsoffood.data;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.loot.modifier.AddItemModifier;
import lotsoffood.common.registry.ModItems;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LotsfoodMod.MODID);
    }
    @Override
    protected void start() {
        this.add("chili_seeds_to_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.CHILI_SEEDS.get()));
        this.add("chili_seeds_to_tall_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.CHILI_SEEDS.get()));
        this.add("tomate_seeds_to_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.TOMATE_SEEDS.get()));
        this.add("tomate_seeds_to_tall_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.TOMATE_SEEDS.get()));
        this.add("strawberry_to_tall_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.STRAWBERRY.get()));
        this.add("strawberry_to_short_grass",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.1F).build()}, ModItems.STRAWBERRY.get()));
        this.add("banane_to_jungle_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.25F).build()}, ModItems.BANANE.get()));
        this.add("vanille_to_birch_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BIRCH_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.25F).build()}, ModItems.VANILLE.get()));
        this.add("grape_to_acacia_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.ACACIA_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.25F).build()}, ModItems.GRAPE.get()));
        this.add("cafebeans_to_spruce_leaves",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SPRUCE_LEAVES).build(),
                        LootItemRandomChanceCondition.randomChance(0.25F).build()}, ModItems.CAFEBEANS.get()));
        this.add("chevalcru_from_horse",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.HORSE)).build()},
                        ModItems.CHEVALCRU.get(), 1, 3));
        this.add("calamarcru_from_squid",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.SQUID)).build()},
                        ModItems.CALAMARCRU.get(), 1, 3));
        this.add("calamarcru_from_glow_squid",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.GLOW_SQUID)).build()},
                        ModItems.CALAMARCRU.get(), 1, 3));
    }

}

