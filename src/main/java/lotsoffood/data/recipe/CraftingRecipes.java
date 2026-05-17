package lotsoffood.data.recipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import lotsoffood.common.registry.ModItems;

public class CraftingRecipes {
    public static void register(RecipeOutput output) {
        recipesOreSmelting(output);
        recipesFoodCooking(output);
        recipesTools(output);
        recipesFoodstuffs(output);
        recipesBlocks(output);
    }

    private static void recipesOreSmelting(RecipeOutput output) {
        // 红枫钻石矿石 -> 红枫钻石 (熔炉)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.MAPLE_DIAMOND_ORE.get()),
            RecipeCategory.MISC,
            ModItems.MAPLE_DIAMOND.get(),
            1.0f,
            200
        ).unlockedBy("has_maple_diamond_ore", TriggerInstance.hasItems(ModItems.MAPLE_DIAMOND_ORE.get()))
         .save(output, "lotsoffood:maple_diamond_from_smelting");

        // 红枫钻石矿石 -> 红枫钻石 (高炉)
        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(ModItems.MAPLE_DIAMOND_ORE.get()),
            RecipeCategory.MISC,
            ModItems.MAPLE_DIAMOND.get(),
            1.0f,
            100
        ).unlockedBy("has_maple_diamond_ore", TriggerInstance.hasItems(ModItems.MAPLE_DIAMOND_ORE.get()))
         .save(output, "lotsoffood:maple_diamond_from_blasting");

        // 深层红枫钻石矿石 -> 红枫钻石 (熔炉)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.DEEPSLATE_MAPLE_DIAMOND_ORE.get()),
            RecipeCategory.MISC,
            ModItems.MAPLE_DIAMOND.get(),
            1.0f,
            200
        ).unlockedBy("has_deepslate_maple_diamond_ore", TriggerInstance.hasItems(ModItems.DEEPSLATE_MAPLE_DIAMOND_ORE.get()))
         .save(output, "lotsoffood:maple_diamond_from_deepslate_smelting");

        // 深层红枫钻石矿石 -> 红枫钻石 (高炉)
        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(ModItems.DEEPSLATE_MAPLE_DIAMOND_ORE.get()),
            RecipeCategory.MISC,
            ModItems.MAPLE_DIAMOND.get(),
            1.0f,
            100
        ).unlockedBy("has_deepslate_maple_diamond_ore", TriggerInstance.hasItems(ModItems.DEEPSLATE_MAPLE_DIAMOND_ORE.get()))
         .save(output, "lotsoffood:maple_diamond_from_deepslate_blasting");

        // 腐肉 -> 纯净肉 (熔炉)
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.ROTTEN_FLESH),
            RecipeCategory.FOOD,
            ModItems.ZOMBIEPURE.get(),
            0.35f,
            200
        ).unlockedBy("has_rotten_flesh", TriggerInstance.hasItems(Items.ROTTEN_FLESH))
         .save(output, "lotsoffood:zombiepure_from_smelting");

        // 腐肉 -> 纯净肉 (烟熏炉)
        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.ROTTEN_FLESH),
            RecipeCategory.FOOD,
            ModItems.ZOMBIEPURE.get(),
            0.35f,
            100
        ).unlockedBy("has_rotten_flesh", TriggerInstance.hasItems(Items.ROTTEN_FLESH))
         .save(output, "lotsoffood:zombiepure_from_smoking");
    }

    private static void recipesFoodCooking(RecipeOutput output) {
        // 鳟鱼 -> 熟鳟鱼
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.TRUITECRUE.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDTRUITECRUE.get(),
            0.35f,
            200
        ).unlockedBy("has_truitecrue", TriggerInstance.hasItems(ModItems.TRUITECRUE.get()))
         .save(output, "lotsoffood:cookedtruitecrue_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.TRUITECRUE.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDTRUITECRUE.get(),
            0.35f,
            100
        ).unlockedBy("has_truitecrue", TriggerInstance.hasItems(ModItems.TRUITECRUE.get()))
         .save(output, "lotsoffood:cookedtruitecrue_from_smoking");

        // 培根 -> 熟培根
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.BACON.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDBACON.get(),
            0.35f,
            200
        ).unlockedBy("has_bacon", TriggerInstance.hasItems(ModItems.BACON.get()))
         .save(output, "lotsoffood:cookedbacon_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.BACON.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDBACON.get(),
            0.35f,
            100
        ).unlockedBy("has_bacon", TriggerInstance.hasItems(ModItems.BACON.get()))
         .save(output, "lotsoffood:cookedbacon_from_smoking");

        // 生鳕鱼 -> 熟鳕鱼
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.BARCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDBARCRU.get(),
            0.35f,
            200
        ).unlockedBy("has_barcru", TriggerInstance.hasItems(ModItems.BARCRU.get()))
         .save(output, "lotsoffood:cookedbarcru_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.BARCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDBARCRU.get(),
            0.35f,
            100
        ).unlockedBy("has_barcru", TriggerInstance.hasItems(ModItems.BARCRU.get()))
         .save(output, "lotsoffood:cookedbarcru_from_smoking");

        // 生马肉 -> 熟马肉
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.CHEVALCRU.get()),
            RecipeCategory.FOOD,
            ModItems.CHEVALCUIT.get(),
            0.35f,
            200
        ).unlockedBy("has_chevalcru", TriggerInstance.hasItems(ModItems.CHEVALCRU.get()))
         .save(output, "lotsoffood:chevalcuit_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.CHEVALCRU.get()),
            RecipeCategory.FOOD,
            ModItems.CHEVALCUIT.get(),
            0.35f,
            100
        ).unlockedBy("has_chevalcru", TriggerInstance.hasItems(ModItems.CHEVALCRU.get()))
         .save(output, "lotsoffood:chevalcuit_from_smoking");

        // 鱿鱼须 -> 熟鱿鱼须
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.CALAMARCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDCALAMARCRU.get(),
            0.35f,
            200
        ).unlockedBy("has_calamarcru", TriggerInstance.hasItems(ModItems.CALAMARCRU.get()))
         .save(output, "lotsoffood:cookedcalamarcru_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.CALAMARCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDCALAMARCRU.get(),
            0.35f,
            100
        ).unlockedBy("has_calamarcru", TriggerInstance.hasItems(ModItems.CALAMARCRU.get()))
         .save(output, "lotsoffood:cookedcalamarcru_from_smoking");

        // 白糖 -> 焦糖
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.SUGAR),
            RecipeCategory.FOOD,
            ModItems.CARAMEL.get(),
            0.35f,
            200
        ).unlockedBy("has_sugar", TriggerInstance.hasItems(Items.SUGAR))
         .save(output, "lotsoffood:caramel_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.SUGAR),
            RecipeCategory.FOOD,
            ModItems.CARAMEL.get(),
            0.35f,
            100
        ).unlockedBy("has_sugar", TriggerInstance.hasItems(Items.SUGAR))
         .save(output, "lotsoffood:caramel_from_smoking");

        // 鲈鱼 -> 熟鲈鱼
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.COLINCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDCOLINCRU.get(),
            0.35f,
            200
        ).unlockedBy("has_colincru", TriggerInstance.hasItems(ModItems.COLINCRU.get()))
         .save(output, "lotsoffood:cookedcolincru_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.COLINCRU.get()),
            RecipeCategory.FOOD,
            ModItems.COOKEDCOLINCRU.get(),
            0.35f,
            100
        ).unlockedBy("has_colincru", TriggerInstance.hasItems(ModItems.COLINCRU.get()))
         .save(output, "lotsoffood:cookedcolincru_from_smoking");

        // 奶酪桶 -> 奶酪
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.SEAUFROMAGE.get()),
            RecipeCategory.FOOD,
            ModItems.FROMAGE.get(),
            0.35f,
            200
        ).unlockedBy("has_seaufromage", TriggerInstance.hasItems(ModItems.SEAUFROMAGE.get()))
         .save(output, "lotsoffood:fromage_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.SEAUFROMAGE.get()),
            RecipeCategory.FOOD,
            ModItems.FROMAGE.get(),
            0.35f,
            100
        ).unlockedBy("has_seaufromage", TriggerInstance.hasItems(ModItems.SEAUFROMAGE.get()))
         .save(output, "lotsoffood:fromage_from_smoking");

        // 棉花糖串 -> 烤棉花糖
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.MARSHMALLOWSTICK.get()),
            RecipeCategory.FOOD,
            ModItems.MARSHMALLOWSTICKCOOKED.get(),
            0.35f,
            200
        ).unlockedBy("has_marshmallowstick", TriggerInstance.hasItems(ModItems.MARSHMALLOWSTICK.get()))
         .save(output, "lotsoffood:marshmallowstickcooked_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.MARSHMALLOWSTICK.get()),
            RecipeCategory.FOOD,
            ModItems.MARSHMALLOWSTICKCOOKED.get(),
            0.35f,
            100
        ).unlockedBy("has_marshmallowstick", TriggerInstance.hasItems(ModItems.MARSHMALLOWSTICK.get()))
         .save(output, "lotsoffood:marshmallowstickcooked_from_smoking");

        // 鸡蛋 -> 水煮蛋
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.EGG),
            RecipeCategory.FOOD,
            ModItems.OEUFDUR.get(),
            0.35f,
            200
        ).unlockedBy("has_egg", TriggerInstance.hasItems(Items.EGG))
         .save(output, "lotsoffood:oeufdur_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.EGG),
            RecipeCategory.FOOD,
            ModItems.OEUFDUR.get(),
            0.35f,
            100
        ).unlockedBy("has_egg", TriggerInstance.hasItems(Items.EGG))
         .save(output, "lotsoffood:oeufdur_from_smoking");

        // 玉米 -> 爆米花
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.CORN.get()),
            RecipeCategory.FOOD,
            ModItems.POPCORN.get(),
            0.35f,
            200
        ).unlockedBy("has_corn", TriggerInstance.hasItems(ModItems.CORN.get()))
         .save(output, "lotsoffood:popcorn_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.CORN.get()),
            RecipeCategory.FOOD,
            ModItems.POPCORN.get(),
            0.35f,
            100
        ).unlockedBy("has_corn", TriggerInstance.hasItems(ModItems.CORN.get()))
         .save(output, "lotsoffood:popcorn_from_smoking");

        // 牛奶桶 -> 奶酪桶
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.MILK_BUCKET),
            RecipeCategory.FOOD,
            ModItems.SEAUFROMAGE.get(),
            0.35f,
            200
        ).unlockedBy("has_milk_bucket", TriggerInstance.hasItems(Items.MILK_BUCKET))
         .save(output, "lotsoffood:seaufromage_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(Items.MILK_BUCKET),
            RecipeCategory.FOOD,
            ModItems.SEAUFROMAGE.get(),
            0.35f,
            100
        ).unlockedBy("has_milk_bucket", TriggerInstance.hasItems(Items.MILK_BUCKET))
         .save(output, "lotsoffood:seaufromage_from_smoking");

        // 鳎鱼 -> 烤鳎鱼
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.SOLECRUE.get()),
            RecipeCategory.FOOD,
            ModItems.SOLECUTE.get(),
            0.35f,
            200
        ).unlockedBy("has_solecrue", TriggerInstance.hasItems(ModItems.SOLECRUE.get()))
         .save(output, "lotsoffood:solecuite_from_smelting");

        SimpleCookingRecipeBuilder.smoking(
            Ingredient.of(ModItems.SOLECRUE.get()),
            RecipeCategory.FOOD,
            ModItems.SOLECUTE.get(),
            0.35f,
            100
        ).unlockedBy("has_solecrue", TriggerInstance.hasItems(ModItems.SOLECRUE.get()))
         .save(output, "lotsoffood:solecuite_from_smoking");
    }

    private static void recipesTools(RecipeOutput output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_SWORD.get())
                .pattern("m")
                .pattern("m")
                .pattern("s")
                .define('m', ModItems.MAPLE_DIAMOND.get())
                .define('s', Items.STICK)
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_AXE.get())
                .pattern("mm")
                .pattern("ms")
                .pattern(" s")
                .define('m', ModItems.MAPLE_DIAMOND.get())
                .define('s', Items.STICK)
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_PICKAXE.get())
                .pattern("mmm")
                .pattern(" s ")
                .pattern(" s ")
                .define('m', ModItems.MAPLE_DIAMOND.get())
                .define('s', Items.STICK)
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_SHOVEL.get())
                .pattern("m")
                .pattern("s")
                .pattern("s")
                .define('m', ModItems.MAPLE_DIAMOND.get())
                .define('s', Items.STICK)
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_HOE.get())
                .pattern("mm")
                .pattern(" s")
                .pattern(" s")
                .define('m', ModItems.MAPLE_DIAMOND.get())
                .define('s', Items.STICK)
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s', Items.SUGAR)
                .unlockedBy("has_maple_sugar", TriggerInstance.hasItems(new ItemLike[]{Items.SUGAR}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CARAM.get())
                .pattern("ccc")
                .pattern("ccc")
                .pattern("ccc")
                .define('c', ModItems.CARAMEL.get())
                .unlockedBy("has_maple_caramel", TriggerInstance.hasItems(new ItemLike[]{ModItems.CARAMEL.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CHOCOBLOC.get())
                .pattern("ccc")
                .pattern("ccc")
                .pattern("ccc")
                .define('c', ModItems.CHOCOLATE.get())
                .unlockedBy("has_chocolate", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHOCOLATE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_DIAMOND_BLOCK.get())
                .pattern("ddd")
                .pattern("ddd")
                .pattern("ddd")
                .define('d', ModItems.MAPLE_DIAMOND.get())
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND.get()}))
                .save(output);

      }
    private static void recipesBlocks(RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.MAPLE_DIAMOND.get(),9)
                .requires(ModItems.MAPLE_DIAMOND_BLOCK.get())
                .unlockedBy("has_maple_diamond", TriggerInstance.hasItems(new ItemLike[]{ModItems.MAPLE_DIAMOND_BLOCK.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CHOCOLATE.get(),9)
                .requires(ModItems.CHOCOBLOC.get())
                .unlockedBy("has_chocobloc", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHOCOBLOC.get()}))
                .save(output, "lotsoffood:chocolate_from_chocobloc");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CARAMEL.get(),9)
                .requires(ModItems.CARAM.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(new ItemLike[]{ModItems.CARAM.get()}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)Items.SUGAR,9)
                .requires(ModItems.BRICKSUCRE.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(new ItemLike[]{ModItems.CARAM.get()}))
                .save(output);
        // CARAM variant recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CARAM_SLAB.get(), 6)
                .pattern("xxx")
                .define('x', ModItems.CARAM.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(ModItems.CARAM.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CARAM_STAIRS.get(), 4)
                .pattern("x  ")
                .pattern("xx ")
                .pattern("xxx")
                .define('x', ModItems.CARAM.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(ModItems.CARAM.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CARAM_FENCE.get(), 3)
                .pattern("xsx")
                .pattern("xsx")
                .define('x', ModItems.CARAM.get())
                .define('s', ModItems.CARAMEL.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(ModItems.CARAM.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CARAM_FENCE_GATE.get(), 1)
                .pattern("csc")
                .pattern("csc")
                .define('s', ModItems.CARAMEL.get())
                .define('c', ModItems.CARAM.get())
                .unlockedBy("has_caram", TriggerInstance.hasItems(ModItems.CARAM.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CARAM_TRAPDOOR.get(), 2)
                .pattern("xxx")
                .pattern("xxx")
                .define('x', ModItems.CARAMEL.get())
                .unlockedBy("has_caramel", TriggerInstance.hasItems(ModItems.CARAMEL.get()))
                .save(output);

        // CHOCOBLOC variant recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CHOCOBLOC_SLAB.get(), 6)
                .pattern("xxx")
                .define('x', ModItems.CHOCOBLOC.get())
                .unlockedBy("has_chocobloc", TriggerInstance.hasItems(ModItems.CHOCOBLOC.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CHOCOBLOC_STAIRS.get(), 4)
                .pattern("x  ")
                .pattern("xx ")
                .pattern("xxx")
                .define('x', ModItems.CHOCOBLOC.get())
                .unlockedBy("has_chocobloc", TriggerInstance.hasItems(ModItems.CHOCOBLOC.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CHOCOBLOC_FENCE.get(), 3)
                .pattern("xsx")
                .pattern("xsx")
                .define('x', ModItems.CHOCOBLOC.get())
                .define('s', ModItems.CHOCOLATE.get())
                .unlockedBy("has_chocobloc", TriggerInstance.hasItems(ModItems.CHOCOBLOC.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CHOCOBLOC_FENCE_GATE.get(), 1)
                .pattern("bsb")
                .pattern("bsb")
                .define('s', ModItems.CHOCOLATE.get())
                .define('b', ModItems.CHOCOBLOC.get())
                .unlockedBy("has_chocobloc", TriggerInstance.hasItems(ModItems.CHOCOBLOC.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.CHOCOBLOC_TRAPDOOR.get(), 2)
                .pattern("sss")
                .pattern("sss")
                .define('s', ModItems.CHOCOLATE.get())
                .unlockedBy("has_chocolate", TriggerInstance.hasItems(ModItems.CHOCOLATE.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLASSCUP.get(),3)
                .pattern("g g")
                .pattern(" g ")
                .define('g', Items.GLASS_PANE)
                .unlockedBy("has_glass_pane", TriggerInstance.hasItems(new ItemLike[]{Items.GLASS_PANE}))
                .save(output);

        // 落叶堆配方
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_RED_FAST.get(), 6)
                .pattern("xx")
                .define('x', ModItems.MAPLE_LEAVES_RED.get())
                .unlockedBy("has_maple_leaves_red", TriggerInstance.hasItems(ModItems.MAPLE_LEAVES_RED.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_YELLOW_FAST.get(), 6)
                .pattern("xx")
                .define('x', ModItems.MAPLE_LEAVES_YELLOW.get())
                .unlockedBy("has_maple_leaves_yellow", TriggerInstance.hasItems(ModItems.MAPLE_LEAVES_YELLOW.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_ORANGE_FAST.get(), 6)
                .pattern("xx")
                .define('x', ModItems.MAPLE_LEAVES_ORANGE.get())
                .unlockedBy("has_maple_leaves_orange", TriggerInstance.hasItems(ModItems.MAPLE_LEAVES_ORANGE.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_LIME_FAST.get(), 6)
                .pattern("xx")
                .define('x', ModItems.MAPLE_LEAVES_LIME.get())
                .unlockedBy("has_maple_leaves_lime", TriggerInstance.hasItems(ModItems.MAPLE_LEAVES_LIME.get()))
                .save(output);

        // 樱叶堆配方
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_SAKURA_FAST.get(), 4)
                .pattern("xx")
                .define('x', ModItems.SAKURA_LEAVES.get())
                .unlockedBy("has_sakura_leaves", TriggerInstance.hasItems(ModItems.SAKURA_LEAVES.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_SAKURA_RED_FAST.get(), 4)
                .pattern("xx")
                .define('x', ModItems.SAKURA_LEAVES.get())
                .unlockedBy("has_sakura_leaves", TriggerInstance.hasItems(ModItems.SAKURA_LEAVES.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.LEAVES_SAKURA_YELLOW_FAST.get(), 4)
                .pattern("xx")
                .define('x', ModItems.SAKURA_LEAVES.get())
                .unlockedBy("has_sakura_leaves", TriggerInstance.hasItems(ModItems.SAKURA_LEAVES.get()))
                .save(output);

        // 樱木板配方
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.SAKURA_PLANK.get(), 4)
                .pattern("w")
                .define('w', ModItems.SAKURA_WOOD.get())
                .unlockedBy("has_sakura_wood", TriggerInstance.hasItems(ModItems.SAKURA_WOOD.get()))
                .save(output);

        // 樱木台阶/楼梯配方
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.SAKURA_SLAB.get(), 6)
                .pattern("xxx")
                .define('x', ModItems.SAKURA_PLANK.get())
                .unlockedBy("has_sakura_plank", TriggerInstance.hasItems(ModItems.SAKURA_PLANK.get()))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ModItems.SAKURA_STAIRS.get(), 4)
                .pattern("x  ")
                .pattern("xx ")
                .pattern("xxx")
                .define('x', ModItems.SAKURA_PLANK.get())
                .unlockedBy("has_sakura_plank", TriggerInstance.hasItems(ModItems.SAKURA_PLANK.get()))
                .save(output);
    }

    private static void recipesFoodstuffs(RecipeOutput output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.APPLE_JUICE.get())
                .requires(net.minecraft.world.item.Items.APPLE)
                .requires(ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSBANANE.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(ModItems.BANANE.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSCACTUS.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(net.minecraft.world.item.Items.CACTUS)
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSCAROTTE.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(net.minecraft.world.item.Items.CARROT)
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSCOCO.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(ModItems.HALFCOCONUT.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.HALFCOCONUT.get(), 2)
                .requires(ModItems.COCONUT.get())
                .unlockedBy("has_coconut", TriggerInstance.hasItems(new ItemLike[]{ModItems.COCONUT.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSMELON.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(Items.MELON)
                .requires(Items.MELON)
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSRAISIN.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(ModItems.GRAPE.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.JUSTOMATE.get())
                .requires(ModItems.GLASSCUP.get())
                .requires(ModItems.TOMATE.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.SPICY_CHOCOLATE.get())
                .requires(ModItems.CHILI.get())
                .requires(ModItems.CHOCOLATE.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CHORIZO.get(),2)
                .requires(ModItems.CHILI.get())
                .requires(ModItems.CHILI.get())
                .requires(Items.PORKCHOP)
                .unlockedBy("has_chili", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHILI.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.FISHSOUP.get(),1)
                .requires(net.neoforged.neoforge.common.Tags.Items.FOODS_COOKED_FISH)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.SUSHI.get(),2)
                .requires(net.neoforged.neoforge.common.Tags.Items.FOODS_COOKED_FISH)
                .requires(Items.KELP)
                .unlockedBy("has_kelp", TriggerInstance.hasItems(new ItemLike[]{Items.KELP}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.TAKOYAKI.get(),2)
                .requires(net.neoforged.neoforge.common.Tags.Items.FOODS_COOKED_FISH)
                .requires(Items.KELP).requires(Items.EGG).requires(Items.WHEAT).requires(ModItems.CALAMARCRU.get())
                .unlockedBy("has_kelp", TriggerInstance.hasItems(new ItemLike[]{Items.KELP}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CHILI_SEEDS.get())
                .requires(ModItems.CHILI.get())
                .unlockedBy("has_chili", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHILI.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.TOMATE_SEEDS.get())
                .requires(ModItems.TOMATE.get())
                .unlockedBy("has_tomate", TriggerInstance.hasItems(new ItemLike[]{ModItems.TOMATE.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CACTUSOUP.get())
                .requires(Items.CACTUS)
                .requires(Items.BOWL)
                .unlockedBy("has_cactus", TriggerInstance.hasItems(new ItemLike[]{Items.CACTUS}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CITROUILSOUP.get())
                .requires(Items.PUMPKIN)
                .requires(Items.BOWL)
                .unlockedBy("has_pumpkin", TriggerInstance.hasItems(new ItemLike[]{Items.PUMPKIN}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.MARSHMALLOWSTICK.get())
                .requires(ModItems.MARSHMALLOW.get())
                .requires(Items.STICK)
                .unlockedBy("has_marshmallow", TriggerInstance.hasItems(new ItemLike[]{ModItems.MARSHMALLOW.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.PATECARBONARA.get())
                .requires(ModItems.PATES.get())
                .requires(ModItems.FROMAGE.get())
                .requires(Items.EGG)
                .requires(Items.PORKCHOP)
                .unlockedBy("has_pates", TriggerInstance.hasItems(new ItemLike[]{ModItems.PATES.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.PATETOMATE.get())
                .requires(ModItems.PATES.get())
                .requires(ModItems.TOMATE.get())
                .unlockedBy("has_pates", TriggerInstance.hasItems(new ItemLike[]{ModItems.PATES.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.PATECHAMPI.get())
                .requires(ModItems.PATES.get())
                .requires(Items.RED_MUSHROOM)
                .requires(Items.BROWN_MUSHROOM)
                .unlockedBy("has_pates", TriggerInstance.hasItems(new ItemLike[]{ModItems.PATES.get()}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.PATEPIMENT.get())
                .requires(ModItems.PATES.get())
                .requires(ModItems.CHILI.get())
                .unlockedBy("has_pates", TriggerInstance.hasItems(new ItemLike[]{ModItems.PATES.get()}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.PATEPOULET.get())
                .requires(ModItems.PATES.get())
                .requires(Items.CHICKEN)
                .unlockedBy("has_pates", TriggerInstance.hasItems(new ItemLike[]{ModItems.PATES.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.RAGOUTALGUES.get())
                .requires(Items.KELP)
                .requires(Items.KELP)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.SALADEFRUITS.get(),3)
                .requires(ModItems.BANANE.get()).requires(ModItems.GRAPE.get()).requires(ModItems.STRAWBERRY.get())
                .requires(Items.APPLE).requires(ModItems.CHERRY.get()).requires(Items.MELON)
                .requires(Items.BOWL).requires(Items.BOWL).requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.SOUPELEGUMES.get(),2)
                .requires(ModItems.TOMATE.get())
                .requires(Items.BAKED_POTATO).requires(Items.CARROT)
                .requires(Items.BOWL).requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.SOUPEPOULET.get(),2)
                .requires(Items.COOKED_CHICKEN).requires(Items.CARROT)
                .requires(Items.BOWL).requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.TRANCHE.get(),2)
                .requires(Items.WHEAT)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACEBANANE.get())
                .pattern(" s ")
                .pattern("sbs")
                .pattern(" g ")
                .define('b', ModItems.BANANE.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDBACON.get())
                .pattern("t")
                .pattern("c")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('c', ModItems.COOKEDBACON.get())
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDBOEUF.get())
                .pattern("t")
                .pattern("c")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('c', Items.COOKED_BEEF)
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDFROM.get())
                .pattern("t")
                .pattern("f")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('f', ModItems.FROMAGE.get())
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDJAMB.get())
                .pattern("t")
                .pattern("p")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('p', Items.PORKCHOP)
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDPOISS.get())
                .pattern("t")
                .pattern("f")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('f', net.neoforged.neoforge.common.Tags.Items.FOODS_COOKED_FISH)
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.SANDPOUL.get())
                .pattern("t")
                .pattern("c")
                .pattern("t")
                .define('t', ModItems.TRANCHE.get())
                .define('c', Items.COOKED_CHICKEN)
                .unlockedBy("has_tranche", TriggerInstance.hasItems(new ItemLike[]{ModItems.TRANCHE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.STEAKTARTARE.get())
                .pattern("www")
                .pattern("wbw")
                .pattern("www")
                .define('w', Items.WHEAT_SEEDS)
                .define('b', Items.BEEF)
                .unlockedBy("has_beef", TriggerInstance.hasItems(new ItemLike[]{Items.BEEF}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.PIZZA.get(),8)
                .pattern(" f ")
                .pattern("fpf")
                .pattern("wtw")
                .define('f', ModItems.FROMAGE.get())
                .define('p', Items.PORKCHOP)
                .define('w', Items.WHEAT)
                .define('t', ModItems.TOMATE.get())
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.POMMEDAMOUR.get())
                .pattern(" c ")
                .pattern("cac")
                .pattern(" s ")
                .define('c', ModItems.CARAMEL.get())
                .define('s', Items.STICK)
                .define('a', Items.APPLE)
                .unlockedBy("has_apple", TriggerInstance.hasItems(new ItemLike[]{Items.APPLE}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.PORCARAMEL.get())
                .pattern(" c ")
                .pattern("coc")
                .pattern(" c ")
                .define('c', ModItems.CARAMEL.get())
                .define('o', Items.COOKED_PORKCHOP)
                .unlockedBy("has_apple", TriggerInstance.hasItems(new ItemLike[]{Items.APPLE}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.PUREE.get(),2)
                .pattern("p")
                .pattern("p")
                .pattern("b")
                .define('p', Items.BAKED_POTATO)
                .define('b', Items.BOWL)
                .unlockedBy("has_baked_potato", TriggerInstance.hasItems(new ItemLike[]{Items.BAKED_POTATO}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECAFE.get())
                .pattern("scs")
                .pattern("scs")
                .pattern(" g ")
                .define('c', ModItems.CAFEBEANS.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECARAMEL.get())
                .pattern("scs")
                .pattern("scs")
                .pattern(" g ")
                .define('c', ModItems.CARAM.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECERISE.get())
                .pattern("scs")
                .pattern("scs")
                .pattern(" g ")
                .define('c', ModItems.CHERRY.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECHOCO.get())
                .pattern(" s ")
                .pattern("scs")
                .pattern(" g ")
                .define('c', ModItems.CHOCOLATE.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECHOCOBLANC.get())
                .pattern(" s ")
                .pattern("sws")
                .pattern(" g ")
                .define('w', ModItems.WHITE_CHOCOLATE.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACEMELON.get())
                .pattern("sss")
                .pattern("mmm")
                .pattern(" g ")
                .define('m', Items.MELON)
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACECOCO.get())
                .pattern("shs")
                .pattern("shs")
                .pattern(" g ")
                .define('h', ModItems.HALFCOCONUT.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACEFRAISE.get())
                .pattern(" s ")
                .pattern("sts")
                .pattern(" g ")
                .define('t', ModItems.STRAWBERRY.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACEPOMME.get())
                .pattern(" s ")
                .pattern("sps")
                .pattern(" g ")
                .define('p', Items.PUMPKIN)
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACERAISIN.get())
                .pattern(" s ")
                .pattern("srs")
                .pattern(" g ")
                .define('r', ModItems.GRAPE.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.GLACEVANILLE.get())
                .pattern(" s ")
                .pattern("svs")
                .pattern(" g ")
                .define('v', ModItems.VANILLE.get())
                .define('s', Items.SNOWBALL)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MILKSHAKECARAMEL.get())
                .pattern("ccc")
                .pattern("cmc")
                .pattern("cgc")
                .define('c', ModItems.CARAMEL.get())
                .define('m', Items.MILK_BUCKET)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MILKSHAKECHOCO.get())
                .pattern("c")
                .pattern("m")
                .pattern("g")
                .define('c', ModItems.CHOCOLATE.get())
                .define('m', Items.MILK_BUCKET)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MILKSHAKECHOCOBLANC.get())
                .pattern("hmh")
                .pattern(" g ")
                .define('h', ModItems.HALFCOCONUT.get())
                .define('m', Items.MILK_BUCKET)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MILKSHAKEFRAISE.get())
                .pattern("sms")
                .pattern(" g ")
                .define('s', ModItems.STRAWBERRY.get())
                .define('m', Items.MILK_BUCKET)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MILKSHAKEVANILLE.get())
                .pattern("vmv")
                .pattern(" g ")
                .define('v', ModItems.VANILLE.get())
                .define('m', Items.MILK_BUCKET)
                .define('g', ModItems.GLASSCUP.get())
                .unlockedBy("has_glasscup", TriggerInstance.hasItems(new ItemLike[]{ModItems.GLASSCUP.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.TARTATATIN.get())
                .pattern("vvv")
                .pattern("aaa")
                .pattern("www")
                .define('v', ModItems.CARAMEL.get())
                .define('w', Items.WHEAT)
                .define('a', Items.APPLE)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BLACKFORESTCAKE.get())
                .pattern("cmc")
                .pattern("ehe")
                .pattern("www")
                .define('c', ModItems.CHERRY.get())
                .define('h', ModItems.CHOCOLATE.get())
                .define('w', Items.WHEAT)
                .define('e', Items.EGG)
                .define('m', Items.MILK_BUCKET)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CHEESECAKE.get())
                .pattern("eme")
                .pattern("fff")
                .pattern("www")
                .define('f', ModItems.FROMAGE.get())
                .define('w', Items.WHEAT)
                .define('e', Items.EGG)
                .define('m', Items.MILK_BUCKET)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CHOCOCAKE.get())
                .pattern("sms")
                .pattern("ece")
                .pattern("www")
                .define('c', ModItems.CHOCOLATE.get())
                .define('w', Items.WHEAT)
                .define('e', Items.EGG)
                .define('m', Items.MILK_BUCKET)
                .define('s', Items.SUGAR)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CARROTCAKE.get())
                .pattern("cmc")
                .pattern("ese")
                .pattern("www")
                .define('c', Items.CARROT)
                .define('w', Items.WHEAT)
                .define('e', Items.EGG)
                .define('m', Items.MILK_BUCKET)
                .define('s', Items.SUGAR)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.FRAISECAKE.get())
                .pattern("sss")
                .pattern("mvm")
                .pattern("www")
                .define('s', ModItems.STRAWBERRY.get())
                .define('v', ModItems.VANILLE.get())
                .define('w', Items.WHEAT)
                .define('m', Items.MILK_BUCKET)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.WHITE_CHOCOLATE.get(),8)
                .pattern("ccc")
                .pattern("cmc")
                .pattern("ccc")
                .define('c', ModItems.CHOCOLATE.get())
                .define('m', Items.MILK_BUCKET)
                .unlockedBy("has_chocolate", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHOCOLATE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BANANACHERRYPIE.get(),3)
                .pattern("ccc")
                .pattern("gvr")
                .pattern(" b ")
                .define('c', ModItems.CHERRY.get())
                .define('g', ModItems.GLACECHOCO.get())
                .define('v', ModItems.GLACEVANILLE.get())
                .define('r', ModItems.GLACERAISIN.get())
                .define('b', ModItems.BANANE.get())
                .unlockedBy("has_cherry", TriggerInstance.hasItems(new ItemLike[]{ModItems.CHERRY.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CREPE.get(),4)
                .pattern("wew")
                .pattern("mwm")
                .define('w', Items.WHEAT)
                .define('e', Items.EGG)
                .define('m', Items.MILK_BUCKET)
                .unlockedBy("has_wheat", TriggerInstance.hasItems(new ItemLike[]{Items.WHEAT}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CREPESUCRE.get())
                .pattern("sss")
                .pattern(" c ")
                .define('s', Items.SUGAR)
                .define('c', ModItems.CREPE.get())
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CREPECARAM.get())
                .requires(ModItems.CREPE.get())
                .requires(ModItems.CARAMEL.get())
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CHILIMEAT.get(),2)
                .requires(ModItems.CHILI.get())
                .requires(Items.COOKED_BEEF)
                .requires(Items.BOWL)
                .requires(Items.BOWL)
                .unlockedBy("has_bowl", TriggerInstance.hasItems(new ItemLike[]{Items.BOWL}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CREPECHOCO.get())
                .requires(ModItems.CREPE.get())
                .requires(ModItems.CHOCOLATE.get())
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, (ItemLike)ModItems.CREPEFROM.get())
                .requires(ModItems.CREPE.get())
                .requires(ModItems.FROMAGE.get())
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CUPCAKE.get(),8)
                .pattern("pcp")
                .pattern(" p ")
                .define('c', Items.CAKE)
                .define('p', Items.PAPER)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CUPCAKECHOCO.get(),8)
                .pattern("pcp")
                .pattern(" p ")
                .define('c', ModItems.CHOCOCAKE.get())
                .define('p', Items.PAPER)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CUPCAKEFRAISE.get(),8)
                .pattern("pfp")
                .pattern(" p ")
                .define('f', ModItems.FRAISECAKE.get())
                .define('p', Items.PAPER)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.DONUTRAISIN.get(),2)
                .pattern("sss")
                .pattern("geg")
                .pattern("www")
                .define('g', ModItems.GRAPE.get())
                .define('e', Items.EGG)
                .define('w', Items.WHEAT)
                .define('s', Items.SUGAR)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.DONUTCHOCO.get(),2)
                .pattern("sss")
                .pattern("cec")
                .pattern("www")
                .define('c', ModItems.CHOCOLATE.get())
                .define('e', Items.EGG)
                .define('w', Items.WHEAT)
                .define('s', Items.SUGAR)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.DONUTGLACE.get(),2)
                .pattern("sss")
                .pattern("nen")
                .pattern("www")
                .define('n', Items.SNOWBALL)
                .define('e', Items.EGG)
                .define('w', Items.WHEAT)
                .define('s', Items.SUGAR)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.DONUTFRAISE.get(),2)
                .pattern("sss")
                .pattern("tet")
                .pattern("www")
                .define('t', ModItems.STRAWBERRY.get())
                .define('e', Items.EGG)
                .define('w', Items.WHEAT)
                .define('s', Items.SUGAR)
                .unlockedBy("has_crepe", TriggerInstance.hasItems(new ItemLike[]{ModItems.CREPE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.CHOCOLATE.get(),2)
                .pattern("csc")
                .define('c', Items.COCOA_BEANS)
                .define('s', Items.SUGAR)
                .unlockedBy("has_sugar", TriggerInstance.hasItems(new ItemLike[]{Items.SUGAR}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE_FENCE.get(),6)
                .pattern("bbb")
                .pattern("bbb")
                .define('b', ModItems.BRICKSUCRE.get())
                .unlockedBy("has_bricksugar", TriggerInstance.hasItems(new ItemLike[]{ModItems.BRICKSUCRE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE_FENCE_GATE.get(),2)
                .pattern("bsb")
                .pattern("bsb")
                .define('b', ModItems.BRICKSUCRE.get())
                .define('s', Items.SUGAR)
                .unlockedBy("has_sugar", TriggerInstance.hasItems(new ItemLike[]{Items.SUGAR}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE_TRAPDOOR.get(),2)
                .pattern("sss")
                .pattern("sss")
                .define('s', Items.SUGAR)
                .unlockedBy("has_sugar", TriggerInstance.hasItems(new ItemLike[]{Items.SUGAR}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE_SLAB.get(),6)
                .pattern("bbb")
                .define('b', ModItems.BRICKSUCRE.get())
                .unlockedBy("has_bricksugar", TriggerInstance.hasItems(new ItemLike[]{ModItems.BRICKSUCRE.get()}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.BRICKSUCRE_STAIRS.get(),4)
                .pattern("b  ")
                .pattern("bb ")
                .pattern("bbb")
                .define('b', ModItems.BRICKSUCRE.get())
                .unlockedBy("has_bricksugar", TriggerInstance.hasItems(new ItemLike[]{ModItems.BRICKSUCRE.get()}))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.COOKIECHOCOBLANC.get(),2)
                .pattern("cwc")
                .define('w', ModItems.WHITE_CHOCOLATE.get())
                .define('c', Items.COCOA_BEANS)
                .unlockedBy("has_cocoa_beans", TriggerInstance.hasItems(new ItemLike[]{Items.COCOA_BEANS}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.MARSHMALLOW.get(),3)
                .pattern("scs")
                .define('c', ModItems.CORN.get())
                .define('s', Items.SUGAR)
                .unlockedBy("has_sugar", TriggerInstance.hasItems(new ItemLike[]{Items.SUGAR}))
                .save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, (ItemLike)ModItems.PATES.get(),1)
                .pattern("cec")
                .pattern(" b ")
                .define('c', ModItems.CORN.get())
                .define('e', Items.EGG)
                .define('b', Items.BOWL)
                .unlockedBy("has_corn", TriggerInstance.hasItems(new ItemLike[]{ModItems.CORN.get()}))
                .save(output);
    }


}