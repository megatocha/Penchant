package archives.tater.penchant.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.providers.EnchantmentsByCostWithDifficulty;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;

import java.util.concurrent.CompletableFuture;

public class LootEnchantmentProviderGenerator extends FabricDynamicRegistryProvider {
    public LootEnchantmentProviderGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(VanillaEnchantmentProviders.MOB_SPAWN_EQUIPMENT, new EnchantmentsByCostWithDifficulty(
                registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT),
                5,
                25
        ));
    }

    @Override
    public String getName() {
        return "Enchantment Providers";
    }
}
