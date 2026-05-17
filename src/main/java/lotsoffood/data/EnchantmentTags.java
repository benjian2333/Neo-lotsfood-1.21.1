package lotsoffood.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EnchantmentTags extends EnchantmentTagsProvider {
    public EnchantmentTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, "lotsoffood", existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider provider) {
        this.tag(net.minecraft.tags.EnchantmentTags.NON_TREASURE);
    }
}