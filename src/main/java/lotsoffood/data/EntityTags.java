package lotsoffood.data;

import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import lotsoffood.common.tag.ModTags;

public class EntityTags extends EntityTypeTagsProvider {
    public EntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "lotsoffood", existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider provider) {
    }
}