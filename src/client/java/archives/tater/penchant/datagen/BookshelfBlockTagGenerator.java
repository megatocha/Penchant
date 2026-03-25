package archives.tater.penchant.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BookshelfBlockTagGenerator extends FabricTagsProvider.BlockTagsProvider {
    public BookshelfBlockTagGenerator(FabricPackOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider provider) {
        valueLookupBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER)
                .add(Blocks.CHISELED_BOOKSHELF, Blocks.LECTERN);
    }
}
