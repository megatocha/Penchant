package archives.tater.penchant.mixin.leveling;

import archives.tater.penchant.component.EnchantmentProgress;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(
            at = @At("HEAD"),
            method = "hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V"
    )
	private void updateProgress(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci) {
        EnchantmentProgress.onDurabilityDamage((ItemStack) (Object) this, player);
	}

    @Inject(
            at = @At("HEAD"),
            method = "hurtWithoutBreaking"
    )
    private void updateProgress(int amount, Player player, CallbackInfo ci) {
        if (player instanceof ServerPlayer)
            EnchantmentProgress.onDurabilityDamage((ItemStack) (Object) this, player);
    }
}
