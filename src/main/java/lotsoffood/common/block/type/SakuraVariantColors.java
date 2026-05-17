package lotsoffood.common.block.type;

import net.minecraft.world.item.DyeColor;

public final class SakuraVariantColors {
    private SakuraVariantColors() {}

    /**
     * Colors that are exposed as Sakura leaves/sapling variants.
     * White is reserved as the default raw texture (no tint variant item).
     */
    public static boolean isEnabledVariant(DyeColor color) {
        return color != DyeColor.WHITE
                && color != DyeColor.BLACK
                && color != DyeColor.GRAY
                && color != DyeColor.LIGHT_GRAY
                && color != DyeColor.BROWN;
    }
}

