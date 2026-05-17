package lotsoffood.common.block.type;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

/**
 * 樱花树叶颜色枚举
 * 参考 Bamboo 模组的实现，支持 16 种不同颜色变体
 */
public enum SakuraLeavesType implements StringRepresentable {
    BLACK(0, "black", 0x3C3C44),
    RED(1, "red", 0xC80010),
    GREEN(2, "green", 0x3F9F3F),
    CACAO(3, "cacao", 0x997B4B),
    BLUE(4, "blue", 0x5F9FE4),
    PURPLE(5, "purple", 0xB064D4),
    CYAN(6, "cyan", 0x87CEEB),
    LIGHT_GRAY(7, "light_gray", 0xD1D1D1),
    GRAY(8, "gray", 0x8C8C8C),
    PINK(9, "pink", 0xFFC0CB),
    LIME(10, "lime", 0xBCBC34),
    YELLOW(11, "yellow", 0xF5F540),
    LIGHT_BLUE(12, "light_blue", 0x78A8E8),
    MAGENTA(13, "magenta", 0xFF5AFF),
    ORANGE(14, "orange", 0xFFA500),
    WHITE(15, "white", 0xFFFFFF);

    public static final SakuraLeavesType[] VALUES = values();
    
    private final int id;
    private final String name;
    private final int color;

    SakuraLeavesType(int id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * 根据 ID 获取颜色类型
     * @param id 颜色 ID (0-15)
     * @return 对应的颜色类型
     */
    public static SakuraLeavesType byId(int id) {
        if (id < 0 || id >= VALUES.length) {
            return VALUES[0]; // 默认返回黑色
        }
        return VALUES[id];
    }

    /**
     * 根据名称获取颜色类型
     * @param name 颜色名称
     * @return 对应的颜色类型
     */
    public static SakuraLeavesType byName(String name) {
        for (SakuraLeavesType type : VALUES) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return VALUES[0];
    }

    /**
     * 获取颜色 ID
     * @return 颜色 ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * 获取颜色值（用于小地图等显示）
     * @return RGB 颜色值
     */
    public int getColor() {
        return this.color;
    }

    @Override
    public String getSerializedName() {
        return this.name.toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
