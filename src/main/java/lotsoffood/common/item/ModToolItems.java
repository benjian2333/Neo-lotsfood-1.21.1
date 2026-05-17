package lotsoffood.common.item;

import lotsoffood.common.registry.ModItems;
import lotsoffood.common.tag.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;


public class ModToolItems{

    // 红枫钻石工具 - 只调整伤害和挖掘速度与钻石持平，保留高耐久和高附魔值
    public static final Tier MAPLE_DIAMOND = new SimpleTier(ModTags.INCORRECT_FOR_MAPLE_DIAMOND_TOOL,
            2341,   // 耐久度：保持原样（高于钻石）
            8.0F,   // 挖掘速度：改为与钻石相同
            3.0F,   // 攻击伤害：改为与钻石相同
            33,     // 附魔值：保持原样（高于钻石）
            ()-> Ingredient.of(ModItems.MAPLE_DIAMOND.get()));
}
