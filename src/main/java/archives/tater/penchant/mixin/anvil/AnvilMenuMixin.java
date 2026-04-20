package archives.tater.penchant.mixin.anvil;

import archives.tater.penchant.registry.PenchantFlag;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
    @WrapOperation(
            method = "createResult",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;has(Lnet/minecraft/core/component/DataComponentType;)Z", ordinal = 0)
    )
    private boolean preventBookEnchanting(ItemStack instance, DataComponentType<ItemEnchantments> dataComponentType, Operation<Boolean> original) {
        return !PenchantFlag.NO_ANVIL_BOOKS.isEnabled() && original.call(instance, dataComponentType);
    }
}
