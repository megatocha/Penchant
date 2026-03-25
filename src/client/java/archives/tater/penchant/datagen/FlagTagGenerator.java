package archives.tater.penchant.datagen;

import archives.tater.penchant.registry.PenchantFlag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public abstract class FlagTagGenerator extends FabricTagsProvider<PenchantFlag> {
    public FlagTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, PenchantFlag.REGISTRY_KEY, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var builder = builder(PenchantFlag.ENABLED);
        for (var flag : getFlags()) {
            builder.add(PenchantFlag.REGISTRY.getResourceKey(flag).orElseThrow());
        }
    }

    protected abstract PenchantFlag[] getFlags();

    public static FabricDataGenerator.Pack.RegistryDependentFactory<FlagTagGenerator> generator(PenchantFlag... flags) {
        return (output, registriesFuture) -> new FlagTagGenerator(output, registriesFuture) {
            @Override
            protected PenchantFlag[] getFlags() {
                return flags;
            }
        };
    }
}
