package archives.tater.penchant.mixin.client.keymapping;

import archives.tater.penchant.client.ScreenKeyMapping;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

@Mixin(KeyMapping.class)
public class KeyMappingMixin {
    @Shadow
    @Final
    private static Map<InputConstants.Key, KeyMapping> MAP;

    @WrapOperation(
            method = {
                    "<init>(Ljava/lang/String;Lcom/mojang/blaze3d/platform/InputConstants$Type;ILjava/lang/String;)V",
                    "resetMapping"
            },
            at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")
    )
    private static <K, V> @Nullable V preventPutScreenKey(Map<K, V> instance, K k, V v, Operation<V> original) {
        return instance == MAP && v instanceof ScreenKeyMapping ? null : original.call(instance, k, v);
    }
}
