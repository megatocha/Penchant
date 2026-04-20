package archives.tater.penchant.mixin.effect;

import archives.tater.penchant.enchantment.UnbreakableEffect;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Unbreakable;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ConstantValue")
@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Unique
    private static final Unbreakable UNBREAKABLE_INSTANCE = new Unbreakable(true);

    @ModifyExpressionValue(
            method = "isDamageableItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;has(Lnet/minecraft/core/component/DataComponentType;)Z", ordinal = 1)
    )
    private boolean unbreakableEnchantment(boolean original) {
        return original || UnbreakableEffect.isUnbreakable((ItemStack) (Object) this);
    }

    @SuppressWarnings("unchecked")
    @WrapOperation(
            method = "addToTooltip",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;get(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;")
    )
    private <T> @Nullable T unbreakableEnchantmentTooltip(ItemStack instance, DataComponentType<T> dataComponentType, Operation<T> original) {
        var originalResult = original.call(instance, dataComponentType);
        if (originalResult != null) return originalResult;
        return dataComponentType == DataComponents.UNBREAKABLE && UnbreakableEffect.isUnbreakable((ItemStack) (Object) this)
                ? (T) UNBREAKABLE_INSTANCE
                : null;
    }
}
