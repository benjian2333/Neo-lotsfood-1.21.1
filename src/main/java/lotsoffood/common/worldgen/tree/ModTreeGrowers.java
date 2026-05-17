package lotsoffood.common.worldgen.tree;

import lotsoffood.LotsfoodMod;
import lotsoffood.common.block.type.SakuraVariantColors;
import lotsoffood.common.worldgen.feature.ModConfiguredFeatures;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.EnumMap;
import java.util.Optional;

public class ModTreeGrowers {
    // 使用与原版一致的 TreeGrower 命名和构造方式
    public static final TreeGrower MAPLE_RED_TREE_GROWER = createTreeGrower("maple_red", ModConfiguredFeatures.RED_MAPLE_WOOD_KEY);
    public static final TreeGrower MAPLE_YELLOW_TREE_GROWER = createTreeGrower("maple_yellow", ModConfiguredFeatures.YELLOW_MAPLE_WOOD_KEY);
    public static final TreeGrower MAPLE_ORANGE_TREE_GROWER = createTreeGrower("maple_orange", ModConfiguredFeatures.ORANGE_MAPLE_WOOD_KEY);
    public static final TreeGrower MAPLE_LIME_TREE_GROWER = createTreeGrower("maple_lime", ModConfiguredFeatures.LIME_MAPLE_WOOD_KEY);
    public static final TreeGrower SAKURA_TREE_GROWER = createTreeGrower("sakura", ModConfiguredFeatures.SAKURA_WOOD_KEY);
    public static final TreeGrower COCONUT_TREE_GROWER = createTreeGrower("coconut", ModConfiguredFeatures.COCONUT_WOOD_KEY);
    private static final EnumMap<DyeColor, TreeGrower> SAKURA_VARIANT_GROWERS = new EnumMap<>(DyeColor.class);

    static {
        for (DyeColor color : DyeColor.values()) {
            if (color == DyeColor.WHITE || SakuraVariantColors.isEnabledVariant(color)) {
                SAKURA_VARIANT_GROWERS.put(
                        color,
                        createTreeGrower("sakura_" + color.getName(), ModConfiguredFeatures.sakuraWoodKey(color))
                );
            }
        }
    }
    /**
     * 创建 TreeGrower 的辅助方法，确保正确注册
     */
    private static TreeGrower createTreeGrower(String name, net.minecraft.resources.ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> featureKey) {
        return new TreeGrower(
                name,
                Optional.empty(),  // megaTree: 大型树木变体
                Optional.of(featureKey),  // tree: 普通树木
                Optional.empty()   // flowers: 花朵生成器
        );
    }

    public static TreeGrower getSakuraTreeGrower(DyeColor color) {
        return SAKURA_VARIANT_GROWERS.getOrDefault(color, SAKURA_TREE_GROWER);
    }
}
