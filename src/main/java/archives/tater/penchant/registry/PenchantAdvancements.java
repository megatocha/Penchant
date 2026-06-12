package archives.tater.penchant.registry;

import archives.tater.penchant.Penchant;
import archives.tater.penchant.advancement.ExtractEnchantmentTrigger;
import archives.tater.penchant.advancement.OpenTableTrigger;

import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class PenchantAdvancements {

    private static <T extends CriterionTrigger<?>> T register(String path, T trigger) {
        return Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                Penchant.id(path),
                trigger
        );
    }

    public static final OpenTableTrigger OPEN_TABLE = register("open_table", new OpenTableTrigger());
    
    public static final ExtractEnchantmentTrigger EXTRACT_ENCHANTMENT = register("extract_enchantment", new ExtractEnchantmentTrigger());

    public static void init() {

    }
}
