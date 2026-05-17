package lotsoffood.common.registry;
import com.google.common.collect.Sets;
import java.util.LinkedHashSet;
import java.util.function.Supplier;
import lotsoffood.common.item.ModToolItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredRegister;
import lotsoffood.common.FoodValues;
import lotsoffood.common.block.entity.container.ConsumableItem;
import lotsoffood.common.item.DrinkableItem;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS;
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS;
    public static final Supplier<Item> COCONUT_BLOCK;
    public static final Supplier<Item> COCONUT_SAPLING;
    public static final Supplier<Item> MAPLE_DIAMOND_ORE;
    public static final Supplier<Item> DEEPSLATE_MAPLE_DIAMOND_ORE;
    public static final Supplier<Item> MAPLE_DIAMOND_BLOCK;
    public static final Supplier<Item> MAPLE_WOOD;
    public static final Supplier<Item> MAPLE_LEAVES_LIME;
    public static final Supplier<Item> MAPLE_LEAVES_YELLOW;
    public static final Supplier<Item> MAPLE_LEAVES_RED;
    public static final Supplier<Item> MAPLE_LEAVES_ORANGE;
    public static final Supplier<Item> BRICKSUCRE;
    public static final Supplier<Item> CARAM;
    public static final Supplier<Item> CHOCOBLOC;
    public static final Supplier<Item> BRICKSUCRE_SLAB;
    public static final Supplier<Item> BRICKSUCRE_STAIRS;
    public static final Supplier<Item> BRICKSUCRE_FENCE;
    public static final Supplier<Item> BRICKSUCRE_TRAPDOOR;
    public static final Supplier<Item> CARAM_SLAB;
    public static final Supplier<Item> CARAM_STAIRS;
    public static final Supplier<Item> CARAM_FENCE;
    public static final Supplier<Item> CARAM_TRAPDOOR;
    public static final Supplier<Item> CHOCOBLOC_SLAB;
    public static final Supplier<Item> CHOCOBLOC_STAIRS;
    public static final Supplier<Item> CHOCOBLOC_FENCE;
    public static final Supplier<Item> CHOCOBLOC_FENCE_GATE;
    public static final Supplier<Item> CHOCOBLOC_TRAPDOOR;
    public static final Supplier<Item> BRICKSUCRE_FENCE_GATE;
    public static final Supplier<Item> CARAM_FENCE_GATE;
    public static final Supplier<Item> WOOD_SPILE;
    public static final Supplier<Item> TRANCHE;
    public static final Supplier<Item> CREPE;
    public static final Supplier<Item> CREPECARAM;
    public static final Supplier<Item> CREPECHOCO;
    public static final Supplier<Item> CREPEFROM;
    public static final Supplier<Item> CREPESUCRE;
    public static final Supplier<Item> CUPCAKE;
    public static final Supplier<Item> CUPCAKECHOCO;
    public static final Supplier<Item> CUPCAKEFRAISE;
    public static final Supplier<Item> DONUTCHOCO;
    public static final Supplier<Item> DONUTFRAISE;
    public static final Supplier<Item> DONUTGLACE;
    public static final Supplier<Item> FROMAGE;
    public static final Supplier<Item> DONUTRAISIN;
    public static final Supplier<Item> FISHANDCHIPS;
    public static final Supplier<Item> FISHSOUP;
    public static final Supplier<Item> GLASSCUP;
    public static final Supplier<Item> COCONUT;
    public static final Supplier<Item> VANILLE;
    public static final Supplier<Item> HALFCOCONUT;
    public static final Supplier<Item> ZOMBIEPURE;
    public static final Supplier<Item> CHOCOLATE;
    public static final Supplier<Item> WHITE_CHOCOLATE;
    public static final Supplier<Item> SPICY_CHOCOLATE;
    public static final Supplier<Item> APPLE_JUICE;
    public static final Supplier<Item> GLACEBANANE;
    public static final Supplier<Item> GLACECAFE;
    public static final Supplier<Item> GLACECARAMEL;
    public static final Supplier<Item> GLACECERISE;
    public static final Supplier<Item> GLACECHOCO;
    public static final Supplier<Item> GLACECHOCOBLANC;
    public static final Supplier<Item> GLACECOCO;
    public static final Supplier<Item> GLACEFRAISE;
    public static final Supplier<Item> GLACEMELON;
    public static final Supplier<Item> GLACEPOMME;
    public static final Supplier<Item> GLACERAISIN;
    public static final Supplier<Item> GLACEVANILLE;
    public static final Supplier<Item> JUSBANANE;
    public static final Supplier<Item> JUSCACTUS;
    public static final Supplier<Item> JUSCAROTTE;
    public static final Supplier<Item> JUSCOCO;
    public static final Supplier<Item> JUSMELON;
    public static final Supplier<Item> JUSRAISIN;
    public static final Supplier<Item> JUSTOMATE;
    public static final Supplier<Item> MILKSHAKECARAMEL;
    public static final Supplier<Item> MILKSHAKECHOCO;
    public static final Supplier<Item> MILKSHAKECHOCOBLANC;
    public static final Supplier<Item> MILKSHAKEFRAISE;
    public static final Supplier<Item> MILKSHAKEVANILLE;
    public static final Supplier<Item> OEUFDUR;
    public static final Supplier<Item> PATECARBONARA;
    public static final Supplier<Item> PATETOMATE;
    public static final Supplier<Item> PATECHAMPI;
    public static final Supplier<Item> PATEPIMENT;
    public static final Supplier<Item> PATEPOULET;
    public static final Supplier<Item> PIZZA;
    public static final Supplier<Item> POMMEDAMOUR;
    public static final Supplier<Item> PATES;
    public static final Supplier<Item> POPCORN;
    public static final Supplier<Item> PORCARAMEL;
    public static final Supplier<Item> PUREE;
    public static final Supplier<Item> RAGOUTALGUES;
    public static final Supplier<Item> SALADEFRUITS;
    public static final Supplier<Item> SEAUFROMAGE;
    public static final Supplier<Item> SOLECRUE;
    public static final Supplier<Item> SOLECUTE;
    public static final Supplier<Item> SOUPELEGUMES;
    public static final Supplier<Item> STEAKTARTARE;
    public static final Supplier<Item> SUSHI;
    public static final Supplier<Item> SOUPEPOULET;
    public static final Supplier<Item> TAKOYAKI;
    public static final Supplier<Item> SANDBACON;
    public static final Supplier<Item> SANDBOEUF;
    public static final Supplier<Item> SANDFROM;
    public static final Supplier<Item> SANDJAMB;
    public static final Supplier<Item> SANDPOISS;
    public static final Supplier<Item> SANDPOUL;
    public static final Supplier<Item> MARSHMALLOW;
    public static final Supplier<Item> MARSHMALLOWSTICK;
    public static final Supplier<Item> MARSHMALLOWSTICKCOOKED;
    public static final Supplier<Item> TOMATE_SEEDS;
    public static final Supplier<Item> CHILI_SEEDS;
    public static final Supplier<Item> CHILI;
    public static final Supplier<Item> TOMATE;
    public static final Supplier<Item> MAPLE_DIAMOND;
    public static final Supplier<Item> BANANE;
    public static final Supplier<Item> STRAWBERRY;
    public static final Supplier<Item> CAFEBEANS;
    public static final Supplier<Item> GRAPE;
    public static final Supplier<Item> CHERRY;
    public static final Supplier<Item> TRUITECRUE;
    public static final Supplier<Item> COOKEDTRUITECRUE;
    public static final Supplier<Item> CHEVALCRU;
    public static final Supplier<Item> CHEVALCUIT;
    public static final Supplier<Item> TARTATATIN;
    public static final Supplier<Item> FRAISECAKE;
    public static final Supplier<Item> CHOCOCAKE;
    public static final Supplier<Item> CARROTCAKE;
    public static final Supplier<Item> CHEESECAKE;
    public static final Supplier<Item> BLACKFORESTCAKE;
    public static final Supplier<Item> CORN;
    public static final Supplier<Item> BACON;
    public static final Supplier<Item> COOKEDBACON;
    public static final Supplier<Item> BANANACHERRYPIE;
    public static final Supplier<Item> BARCRU;
    public static final Supplier<Item> COOKEDBARCRU;
    public static final Supplier<Item> CACTUSOUP;
    public static final Supplier<Item> CALAMARCRU;
    public static final Supplier<Item> COOKEDCALAMARCRU;
    public static final Supplier<Item> CARAMEL;
    public static final Supplier<Item> CHILIMEAT;
    public static final Supplier<Item> CHORIZO;
    public static final Supplier<Item> CITROUILSOUP;
    public static final Supplier<Item> COLINCRU;
    public static final Supplier<Item> COOKEDCOLINCRU;
    public static final Supplier<Item> COOKIECHOCOBLANC;
    public static final Supplier<Item> MAPLES_LIME;
    public static final Supplier<Item> MAPLES_RED;
    public static final Supplier<Item> MAPLES_YELLOW;
    public static final Supplier<Item> MAPLES_ORANGE;
    public static final Supplier<Item> SAKURA;
    public static final Supplier<Item> SAKURA_LEAVES;
    public static final Supplier<Item> SAKURA_WOOD;
    public static final Supplier<Item> SAKURA_PLANK;
    public static final Supplier<Item> SAKURA_STAIRS;
    public static final Supplier<Item> SAKURA_SLAB;
    public static final Supplier<Item> LEAVES_RED_FAST;
    public static final Supplier<Item> LEAVES_YELLOW_FAST;
    public static final Supplier<Item> LEAVES_ORANGE_FAST;
    public static final Supplier<Item> LEAVES_LIME_FAST;
    public static final Supplier<Item> LEAVES_SAKURA_FAST;
    public static final Supplier<Item> LEAVES_SAKURA_RED_FAST;
    public static final Supplier<Item> LEAVES_SAKURA_YELLOW_FAST;
    public static  final Supplier<SwordItem> MAPLE_SWORD;
    public static  final Supplier<PickaxeItem>MAPLE_PICKAXE;
    public static  final Supplier<AxeItem> MAPLE_AXE;
    public static  final Supplier<ShovelItem> MAPLE_SHOVEL;
    public static  final Supplier<HoeItem> MAPLE_HOE;


    public static Supplier<Item> registerWithTab(String name, Supplier<Item> supplier) {
        Supplier<Item> block = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(block);
        return block;
    }

    public static Item.Properties basicItem() {
        return new Item.Properties();
    }



    public static Item.Properties foodItem(FoodProperties food) {
        return (new Item.Properties()).food(food);
    }

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }

    public static Item.Properties drinkItem() {
        return (new Item.Properties()).craftRemainder(ModItems.GLASSCUP.get()).stacksTo(16);
    }

    static {
        ITEMS = DeferredRegister.create(Registries.ITEM, "lotsoffood");
        CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
        
        // 红枫工具 - 使用正确的属性创建方法
        MAPLE_PICKAXE = ITEMS.register("maple_pickaxe", 
            ()->new PickaxeItem(ModToolItems.MAPLE_DIAMOND,
                new Item.Properties().attributes(
                    PickaxeItem.createAttributes(ModToolItems.MAPLE_DIAMOND, 1.0F, -2.8F)
                )));
        
        MAPLE_AXE = ITEMS.register("maple_axe", 
            ()->new AxeItem(ModToolItems.MAPLE_DIAMOND,
                new Item.Properties().attributes(
                    AxeItem.createAttributes(ModToolItems.MAPLE_DIAMOND, 6.0F, -3.2F)
                )));
        
        MAPLE_SHOVEL = ITEMS.register("maple_shovel", 
            ()->new ShovelItem(ModToolItems.MAPLE_DIAMOND,
                new Item.Properties().attributes(
                    ShovelItem.createAttributes(ModToolItems.MAPLE_DIAMOND, 1.5F, -3.0F)
                )));
        
        MAPLE_HOE = ITEMS.register("maple_hoe", 
            ()->new HoeItem(ModToolItems.MAPLE_DIAMOND,
                new Item.Properties().attributes(
                    HoeItem.createAttributes(ModToolItems.MAPLE_DIAMOND, -3.0F, 0.0F)
                )));

        MAPLE_SWORD = ITEMS.register("maple_sword", 
            ()->new SwordItem(ModToolItems.MAPLE_DIAMOND,
                new Item.Properties().attributes(
                    SwordItem.createAttributes(ModToolItems.MAPLE_DIAMOND, 3.0F, -2.4F)
                )));

        //食物
        COCONUT_BLOCK = registerWithTab("coconut_block", () -> new BlockItem(ModBlocks.COCONUT_BLOCK.get(), basicItem()));
        BRICKSUCRE = registerWithTab("bricksucre", () -> new BlockItem(ModBlocks.BRICKSUCRE.get(), basicItem()));
        CARAM = registerWithTab("caram", () -> new BlockItem(ModBlocks.CARAM.get(), basicItem()));
        CHOCOBLOC= registerWithTab("chocobloc", () -> new BlockItem(ModBlocks.CHOCOBLOC.get(), basicItem()));

        // BRICKSUCRE variants
        BRICKSUCRE_SLAB = registerWithTab("bricksucre_slab", () -> new BlockItem(ModBlocks.BRICKSUCRE_SLAB.get(), basicItem()));
        BRICKSUCRE_STAIRS = registerWithTab("bricksucre_stairs", () -> new BlockItem(ModBlocks.BRICKSUCRE_STAIRS.get(), basicItem()));
        BRICKSUCRE_FENCE = registerWithTab("bricksucre_fence", () -> new BlockItem(ModBlocks.BRICKSUCRE_FENCE.get(), basicItem()));
        BRICKSUCRE_FENCE_GATE = registerWithTab("bricksucre_fence_gate", () -> new BlockItem(ModBlocks.BRICKSUCRE_FENCE_GATE.get(), basicItem()));
        BRICKSUCRE_TRAPDOOR = registerWithTab("bricksucre_trapdoor", () -> new BlockItem(ModBlocks.BRICKSUCRE_TRAPDOOR.get(), basicItem()));

        // CARAM variants
        CARAM_SLAB = registerWithTab("caram_slab", () -> new BlockItem(ModBlocks.CARAM_SLAB.get(), basicItem()));
        CARAM_STAIRS = registerWithTab("caram_stairs", () -> new BlockItem(ModBlocks.CARAM_STAIRS.get(), basicItem()));
        CARAM_FENCE = registerWithTab("caram_fence", () -> new BlockItem(ModBlocks.CARAM_FENCE.get(), basicItem()));
        CARAM_FENCE_GATE = registerWithTab("caram_fence_gate", () -> new BlockItem(ModBlocks.CARAM_FENCE_GATE.get(), basicItem()));
        CARAM_TRAPDOOR = registerWithTab("caram_trapdoor", () -> new BlockItem(ModBlocks.CARAM_TRAPDOOR.get(), basicItem()));

        // CHOCOBLOC variants
        CHOCOBLOC_SLAB = registerWithTab("chocobloc_slab", () -> new BlockItem(ModBlocks.CHOCOBLOC_SLAB.get(), basicItem()));
        CHOCOBLOC_STAIRS = registerWithTab("chocobloc_stairs", () -> new BlockItem(ModBlocks.CHOCOBLOC_STAIRS.get(), basicItem()));
        CHOCOBLOC_FENCE = registerWithTab("chocobloc_fence", () -> new BlockItem(ModBlocks.CHOCOBLOC_FENCE.get(), basicItem()));
        CHOCOBLOC_FENCE_GATE = registerWithTab("chocobloc_fence_gate", () -> new BlockItem(ModBlocks.CHOCOBLOC_FENCE_GATE.get(), basicItem()));
        CHOCOBLOC_TRAPDOOR = registerWithTab("chocobloc_trapdoor", () -> new BlockItem(ModBlocks.CHOCOBLOC_TRAPDOOR.get(), basicItem()));

        // 红枫矿石
        MAPLE_DIAMOND_ORE = registerWithTab("maple_diamond_ore", () -> new BlockItem(ModBlocks.MAPLE_DIAMOND_ORE.get(), basicItem()));
        DEEPSLATE_MAPLE_DIAMOND_ORE = registerWithTab("deepslate_maple_diamond_ore", () -> new BlockItem(ModBlocks.DEEPSLATE_MAPLE_DIAMOND_ORE.get(), basicItem()));
        MAPLE_DIAMOND_BLOCK = registerWithTab("maple_diamond_block", () -> new BlockItem(ModBlocks.MAPLE_DIAMOND_BLOCK.get(), basicItem()));
        MAPLE_WOOD = registerWithTab("maple_wood", () -> new BlockItem(ModBlocks.MAPLE_WOOD.get(), basicItem()));
        MAPLE_DIAMOND = registerWithTab("maple_diamond", () -> new Item(new Item.Properties()));
        //木头树叶
        MAPLE_LEAVES_LIME = registerWithTab("maple_leaves_lime", () -> new BlockItem(ModBlocks.MAPLE_LEAVES_LIME.get(), basicItem()));
        MAPLE_LEAVES_YELLOW = registerWithTab("maple_leaves_yellow", () -> new BlockItem(ModBlocks.MAPLE_LEAVES_YELLOW.get(), basicItem()));
        MAPLE_LEAVES_RED = registerWithTab("maple_leaves_red", () -> new BlockItem(ModBlocks.MAPLE_LEAVES_RED.get(), basicItem()));
        MAPLE_LEAVES_ORANGE = registerWithTab("maple_leaves_orange", () -> new BlockItem(ModBlocks.MAPLE_LEAVES_ORANGE.get(), basicItem()));
        SAKURA_LEAVES = registerWithTab("sakura_leaves", () -> new BlockItem(ModBlocks.SAKURA_LEAVES.get(), basicItem()));
        WOOD_SPILE = registerWithTab("woodspile", () -> new BlockItem(ModBlocks.WOOD_SPILE.get(), basicItem()));
        MAPLES_ORANGE = registerWithTab("maples_orange", () -> new BlockItem(ModBlocks.MAPLES_ORANGE.get(), basicItem()));
        MAPLES_RED = registerWithTab("maples_red", () -> new BlockItem(ModBlocks.MAPLES_RED.get(), basicItem()));
        MAPLES_YELLOW = registerWithTab("maples_yellow", () -> new BlockItem(ModBlocks.MAPLES_YELLOW.get(), basicItem()));
        MAPLES_LIME = registerWithTab("maples_lime", () -> new BlockItem(ModBlocks.MAPLES_LIME.get(), basicItem()));
        SAKURA = registerWithTab("sakura", () -> new BlockItem(ModBlocks.SAKURA.get(), basicItem()));
        SAKURA_WOOD = registerWithTab("sakura_wood", () -> new BlockItem(ModBlocks.SAKURA_WOOD.get(), basicItem()));
        SAKURA_PLANK = registerWithTab("sakura_plank", () -> new BlockItem(ModBlocks.SAKURA_PLANK.get(), basicItem()));
        SAKURA_STAIRS = registerWithTab("sakura_stairs", () -> new BlockItem(ModBlocks.SAKURA_STAIRS.get(), basicItem()));
        SAKURA_SLAB = registerWithTab("sakura_slab", () -> new BlockItem(ModBlocks.SAKURA_SLAB.get(), basicItem()));
        COCONUT_SAPLING = registerWithTab("poussecocotier", () -> new BlockItem(ModBlocks.COCONUT_SAPLING.get(), basicItem()));
        LEAVES_RED_FAST = registerWithTab("leaves_red_fast", () -> new BlockItem(ModBlocks.LEAVES_RED_FAST.get(), basicItem()));
        LEAVES_YELLOW_FAST = registerWithTab("leaves_yellow_fast", () -> new BlockItem(ModBlocks.LEAVES_YELLOW_FAST.get(), basicItem()));
        LEAVES_ORANGE_FAST = registerWithTab("leaves_orange_fast", () -> new BlockItem(ModBlocks.LEAVES_ORANGE_FAST.get(), basicItem()));
        LEAVES_LIME_FAST = registerWithTab("leaves_lime_fast", () -> new BlockItem(ModBlocks.LEAVES_LIME_FAST.get(), basicItem()));
        LEAVES_SAKURA_FAST = registerWithTab("leaves_sakura_fast", () -> new BlockItem(ModBlocks.LEAVES_SAKURA_FAST.get(), basicItem()));
        LEAVES_SAKURA_RED_FAST = registerWithTab("leaves_sakura_red_fast", () -> new BlockItem(ModBlocks.LEAVES_SAKURA_RED_FAST.get(), basicItem()));
        LEAVES_SAKURA_YELLOW_FAST = registerWithTab("leaves_sakura_yellow_fast", () -> new BlockItem(ModBlocks.LEAVES_SAKURA_YELLOW_FAST.get(), basicItem()));

        //物品(饮品杯子必须先注册)
        GLASSCUP = registerWithTab("glasscup", () -> new Item(new Item.Properties()));

        //饮品
        APPLE_JUICE = registerWithTab("apple_juice", () -> new DrinkableItem(drinkItem().food(FoodValues.APPLE_JUICE).stacksTo(16)));
        GLACEBANANE = registerWithTab("glacebanane", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACEBANANE).stacksTo(16)));
        GLACECAFE = registerWithTab("glacecafe", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECAFE).stacksTo(16)));
        GLACECARAMEL = registerWithTab("glacecaramel", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECARAMEL).stacksTo(16)));
        GLACECERISE = registerWithTab("glacecerise", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECERISE).stacksTo(16)));
        GLACECHOCO = registerWithTab("glacechoco", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECHOCO).stacksTo(16)));
        GLACECHOCOBLANC = registerWithTab("glacechocoblanc", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECHOCOBLANC).stacksTo(16)));
        GLACECOCO = registerWithTab("glacecoco", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACECOCO).stacksTo(16)));
        GLACEFRAISE = registerWithTab("glacefraise", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACEFRAISE).stacksTo(16)));
        GLACEMELON = registerWithTab("glacemelon", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACEMELON).stacksTo(16)));
        GLACEPOMME = registerWithTab("glacepomme", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACEPOMME).stacksTo(16)));
        GLACERAISIN = registerWithTab("glaceraisin", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACERAISIN).stacksTo(16)));
        GLACEVANILLE = registerWithTab("glacevanille", () -> new DrinkableItem(drinkItem().food(FoodValues.GLACEVANILLE).stacksTo(16)));
        JUSBANANE = registerWithTab("jusbanane", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSBANANE).stacksTo(16)));
        JUSCACTUS = registerWithTab("juscactus", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSCACTUS).stacksTo(16)));
        JUSCAROTTE = registerWithTab("juscarotte", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSCAROTTE).stacksTo(16)));
        JUSCOCO = registerWithTab("juscoco", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSCOCO).stacksTo(16)));
        JUSMELON = registerWithTab("jusmelon", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSMELON).stacksTo(16)));
        JUSRAISIN = registerWithTab("jusraisin", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSRAISIN).stacksTo(16)));
        JUSTOMATE = registerWithTab("justomate", () -> new DrinkableItem(drinkItem().food(FoodValues.JUSTOMATE).stacksTo(16)));
        MILKSHAKECARAMEL = registerWithTab("milkshakecaramel", () -> new DrinkableItem(drinkItem().food(FoodValues.MILKSHAKECARAMEL).stacksTo(16)));
        MILKSHAKECHOCO = registerWithTab("milkshakechoco", () -> new DrinkableItem(drinkItem().food(FoodValues.MILKSHAKECHOCO).stacksTo(16)));
        MILKSHAKECHOCOBLANC = registerWithTab("milkshakechocoblanc", () -> new DrinkableItem(drinkItem().food(FoodValues.MILKSHAKECHOCOBLANC).stacksTo(16)));
        MILKSHAKEFRAISE = registerWithTab("milkshakefraise", () -> new DrinkableItem(drinkItem().food(FoodValues.MILKSHAKEFRAISE).stacksTo(16)));
        MILKSHAKEVANILLE = registerWithTab("milkshakevanille", () -> new DrinkableItem(drinkItem().food(FoodValues.MILKSHAKEVANILLE).stacksTo(16)));

        //物品
        SEAUFROMAGE = registerWithTab("seaufromage", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET)));
        TOMATE_SEEDS = registerWithTab("tomate_seeds", () -> new ItemNameBlockItem(ModBlocks.TOMATE_BLOCK.get(), new Item.Properties()));
        CHILI_SEEDS = registerWithTab("chili_seeds", () -> new ItemNameBlockItem(ModBlocks.CHILI_BLOCK.get(), new Item.Properties()));
        COCONUT = registerWithTab("coconut", () -> new Item(new Item.Properties()));
        VANILLE = registerWithTab("vanille", () -> new ItemNameBlockItem(ModBlocks.VANILLE_BLOCK.get(), new Item.Properties()));

        //蛋糕
        TARTATATIN = registerWithTab("tartatatin", () -> new ItemNameBlockItem(ModBlocks.TARTATAIN_BLOCK.get(), new Item.Properties()));
        FRAISECAKE = registerWithTab("fraisecake", () -> new ItemNameBlockItem(ModBlocks.FRAISECAKE_BLOCK.get(), new Item.Properties()));
        CHOCOCAKE = registerWithTab("chococake", () -> new ItemNameBlockItem(ModBlocks.CHOCOCAKE_BLOCK.get(), new Item.Properties()));
        CARROTCAKE = registerWithTab("carrotcake", () -> new ItemNameBlockItem(ModBlocks.CARROTCAKE_BLOCK.get(), new Item.Properties()));
        CHEESECAKE = registerWithTab("cheesecake", () -> new ItemNameBlockItem(ModBlocks.CHEESECAKE_BLOCK.get(), new Item.Properties()));
        BLACKFORESTCAKE = registerWithTab("blackforestcake", () -> new ItemNameBlockItem(ModBlocks.BLACKFORESTCAKE_BLOCK.get(), new Item.Properties()));

//食物
        TRANCHE = registerWithTab("tranche", () -> new Item(foodItem(FoodValues.TRANCHE)));
        CREPE = registerWithTab("crepe", () -> new Item(foodItem(FoodValues.CREPE)));
        CREPECARAM = registerWithTab("crepecaram", () -> new Item(foodItem(FoodValues.CREPECARAM)));
        CREPECHOCO = registerWithTab("crepechoco", () -> new Item(foodItem(FoodValues.CREPECHOCO)));
        CREPEFROM = registerWithTab("crepefrom", () -> new Item(foodItem(FoodValues.CREPEFROM)));
        CREPESUCRE = registerWithTab("crepesucre", () -> new Item(foodItem(FoodValues.CREPESUCRE)));
        CUPCAKE = registerWithTab("cupcake", () -> new Item(foodItem(FoodValues.CUPCAKE)));
        CUPCAKECHOCO = registerWithTab("cupcakechoco", () -> new Item(foodItem(FoodValues.CUPCAKECHOCO)));
        CUPCAKEFRAISE = registerWithTab("cupcakefraise", () -> new Item(foodItem(FoodValues.CUPCAKEFRAISE)));
        DONUTCHOCO = registerWithTab("donutchoco", () -> new Item(foodItem(FoodValues.DONUTCHOCO)));
        DONUTFRAISE = registerWithTab("donutfraise", () -> new Item(foodItem(FoodValues.DONUTFRAISE)));
        DONUTGLACE = registerWithTab("donutglace", () -> new Item(foodItem(FoodValues.DONUTGLACE)));
        FROMAGE = registerWithTab("fromage", () -> new Item(foodItem(FoodValues.FROMAGE)));
        DONUTRAISIN = registerWithTab("donutraisin", () -> new Item(foodItem(FoodValues.DONUTRAISIN)));
        FISHANDCHIPS = registerWithTab("fishandchips", () -> new Item(foodItem(FoodValues.FISHANDCHIPS)));
        FISHSOUP = registerWithTab("fishsoup", () -> new Item(foodItem(FoodValues.FISHSOUP)));
        CORN = registerWithTab("corn", () -> new ItemNameBlockItem(ModBlocks.MAIS_BLOCK.get(), foodItem(FoodValues.CORN)));
        BACON = registerWithTab("bacon", () -> new Item(foodItem(FoodValues.BACON)));
        COOKEDBACON = registerWithTab("cookedbacon", () -> new Item(foodItem(FoodValues.COOKEDBACON)));
        BANANACHERRYPIE = registerWithTab("bananacherrypie", () -> new Item(foodItem(FoodValues.BANANACHERRYPIE)));
        BARCRU = registerWithTab("barcru", () -> new Item(foodItem(FoodValues.BARCRU)));
        COOKEDBARCRU = registerWithTab("cookedbarcru", () -> new Item(foodItem(FoodValues.COOKEDBARCRU)));
        CACTUSOUP = registerWithTab("cactusoup", () -> new Item(foodItem(FoodValues.CACTUSOUP)));
        CALAMARCRU = registerWithTab("calamarcru", () -> new Item(foodItem(FoodValues.CALAMARCRU)));
        COOKEDCALAMARCRU = registerWithTab("cookedcalamarcru", () -> new Item(foodItem(FoodValues.COOKEDCALAMARCRU)));
        CHEVALCRU = registerWithTab("chevalcru", () -> new Item(foodItem(FoodValues.CHEVALCRU)));
        CHEVALCUIT = registerWithTab("chevalcuit", () -> new Item(foodItem(FoodValues.CHEVALCUIT)));
        CARAMEL = registerWithTab("caramel", () -> new Item(foodItem(FoodValues.CARAMEL)));
        CHILIMEAT = registerWithTab("chilimeat", () -> new Item(foodItem(FoodValues.CHILIMEAT)));
        CHORIZO = registerWithTab("chorizo", () -> new Item(foodItem(FoodValues.CHORIZO)));
        CITROUILSOUP = registerWithTab("citrouilsoup", () -> new Item(foodItem(FoodValues.CITROUILSOUP)));
        COLINCRU = registerWithTab("colincru", () -> new Item(foodItem(FoodValues.COLINCRU)));
        COOKEDCOLINCRU = registerWithTab("cookedcolincru", () -> new Item(foodItem(FoodValues.COOKEDCOLINCRU)));
        COOKIECHOCOBLANC = registerWithTab("cookiechocoblanc", () -> new Item(foodItem(FoodValues.COOKIECHOCOBLANC)));
        BANANE = registerWithTab("banane", () -> new Item(foodItem(FoodValues.BANANE)));
        STRAWBERRY = registerWithTab("strawberry", () -> new ItemNameBlockItem(ModBlocks.STRAWBERRY_BLOCK.get(), new Item.Properties()));
        CAFEBEANS = registerWithTab("cafebeans", () -> new ItemNameBlockItem(ModBlocks.CAFEBEANS_BLOCK.get(), new Item.Properties()));
        GRAPE = registerWithTab("grape", () -> new ItemNameBlockItem(ModBlocks.GRAPE_BLOCK.get(), foodItem(FoodValues.GRAPE)));
        CHERRY = registerWithTab("cherry", () -> new Item(foodItem(FoodValues.CHERRY)));
        TRUITECRUE = registerWithTab("truitecrue", () -> new Item(foodItem(FoodValues.TRUITECRUE)));
        COOKEDTRUITECRUE = registerWithTab("cookedtruitecrue", () -> new Item(foodItem(FoodValues.COOKEDTRUITECRUE)));
        CHILI = registerWithTab("chili", () -> new ConsumableItem(foodItem(FoodValues.CHILI), true));
        TOMATE = registerWithTab("tomate", () -> new Item(foodItem(FoodValues.TOMATE)));
        SOLECRUE = registerWithTab("solecrue", () -> new Item(foodItem(FoodValues.SOLECRUE)));
        SOLECUTE = registerWithTab("solecuite", () -> new Item(foodItem(FoodValues.SOLECUTE)));
        SOUPELEGUMES = registerWithTab("soupelegumes", () -> new Item(foodItem(FoodValues.SOUPELEGUMES)));
        STEAKTARTARE = registerWithTab("steaktartare", () -> new Item(foodItem(FoodValues.STEAKTARTARE)));
        SUSHI = registerWithTab("sushi", () -> new Item(foodItem(FoodValues.SUSHI)));
        SOUPEPOULET = registerWithTab("soupepoulet", () -> new Item(foodItem(FoodValues.SOUPEPOULET)));
        TAKOYAKI = registerWithTab("takoyaki", () -> new Item(foodItem(FoodValues.TAKOYAKI)));
        SANDBACON = registerWithTab("sandbacon", () -> new Item(foodItem(FoodValues.SANDBACON)));
        SANDBOEUF = registerWithTab("sandboeuf", () -> new Item(foodItem(FoodValues.SANDBOEUF)));
        SANDFROM = registerWithTab("sandfrom", () -> new Item(foodItem(FoodValues.SANDFROM)));
        SANDJAMB = registerWithTab("sandjamb", () -> new Item(foodItem(FoodValues.SANDJAMB)));
        SANDPOISS = registerWithTab("sandpoiss", () -> new Item(foodItem(FoodValues.SANDPOISS)));
        SANDPOUL = registerWithTab("sandpoul", () -> new Item(foodItem(FoodValues.SANDPOUL)));
        MARSHMALLOW = registerWithTab("marshmallow", () -> new Item(foodItem(FoodValues.MARSHMALLOW)));
        MARSHMALLOWSTICK = registerWithTab("marshmallowstick", () -> new Item(foodItem(FoodValues.MARSHMALLOWSTICK)));
        MARSHMALLOWSTICKCOOKED = registerWithTab("marshmallowstickcooked", () -> new Item(foodItem(FoodValues.MARSHMALLOWSTICKCOOKED)));
        OEUFDUR = registerWithTab("oeufdur", () -> new Item(foodItem(FoodValues.OEUFDUR)));
        PATECARBONARA = registerWithTab("patecarbonara", () -> new Item(foodItem(FoodValues.PATECARBONARA)));
        PATETOMATE = registerWithTab("patetomate", () -> new Item(foodItem(FoodValues.PATETOMATE)));
        PATECHAMPI = registerWithTab("patechampi", () -> new Item(foodItem(FoodValues.PATECHAMPI)));
        PATEPIMENT = registerWithTab("patepiment", () -> new Item(foodItem(FoodValues.PATEPIMENT)));
        PATEPOULET = registerWithTab("patepoulet", () -> new Item(foodItem(FoodValues.PATEPOULET)));
        PIZZA = registerWithTab("pizza", () -> new Item(foodItem(FoodValues.PIZZA)));
        POMMEDAMOUR = registerWithTab("pommedamour", () -> new Item(foodItem(FoodValues.POMMEDAMOUR)));
        PATES = registerWithTab("pates", () -> new Item(foodItem(FoodValues.PATES)));
        POPCORN = registerWithTab("popcorn", () -> new Item(foodItem(FoodValues.POPCORN)));
        PORCARAMEL = registerWithTab("porcaramel", () -> new Item(foodItem(FoodValues.PORCARAMEL)));
        PUREE = registerWithTab("puree", () -> new Item(foodItem(FoodValues.PUREE)));
        RAGOUTALGUES = registerWithTab("ragoutalgues", () -> new Item(foodItem(FoodValues.RAGOUTALGUES)));
        SALADEFRUITS = registerWithTab("saladefruits", () -> new Item(foodItem(FoodValues.SALADEFRUITS)));
         HALFCOCONUT = registerWithTab("halfcoconut", () -> new Item(foodItem(FoodValues.HALFCOCONUT)));
        ZOMBIEPURE = registerWithTab("zombiepure", () -> new Item(foodItem(FoodValues.ZOMBIEPURE)));
        CHOCOLATE = registerWithTab("chocolate", () -> new Item(foodItem(FoodValues.CHOCOLATE)));
        WHITE_CHOCOLATE = registerWithTab("white_chocolate", () -> new Item(foodItem(FoodValues.WHITE_CHOCOLATE)));
        SPICY_CHOCOLATE = registerWithTab("spicy_chocolate", () -> new Item(foodItem(FoodValues.SPICY_CHOCOLATE)));


    }
}
