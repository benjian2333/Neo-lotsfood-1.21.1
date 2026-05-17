package lotsoffood.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @Inject(
        method = "burn",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"),
        cancellable = true
    )
    private static void handleCraftingRemainingItem(
        RegistryAccess registryAccess, RecipeHolder<?> recipe,
        NonNullList<ItemStack> inventory, int maxStackSize,
        AbstractFurnaceBlockEntity furnace, CallbackInfoReturnable<Boolean> cir) {

        ItemStack input = inventory.get(0);
        // 奶桶熔炼为奶酪桶时不保留空桶，原版奶桶的craftRemainder仅用于工作台
        if (input.hasCraftingRemainingItem() && !input.is(Items.MILK_BUCKET)) {
            inventory.set(0, input.getCraftingRemainingItem());
            cir.setReturnValue(true);
        }
    }
}
