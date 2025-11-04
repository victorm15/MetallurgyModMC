package net.victej.alloymod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.victej.alloymod.AlloyMod;
import net.victej.alloymod.block.ModBlocks;
import net.victej.alloymod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AlloyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SILVER_ORE.get(), ModBlocks.SILVER_BLOCK.get(),
                        ModBlocks.STEEL_BLOCK.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SILVER_ORE.get(), ModBlocks.SILVER_BLOCK.get(),
                        ModBlocks.RAW_SILVER_BLOCK.get(), ModBlocks.STEEL_BLOCK.get(),
                        ModBlocks.FORGE.get(), ModBlocks.DEEPSLATE_SILVER_ORE.get());
    }
}
