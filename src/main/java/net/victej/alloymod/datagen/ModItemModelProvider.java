package net.victej.alloymod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.victej.alloymod.AlloyMod;
import net.victej.alloymod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AlloyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.COKE);
        simpleItem(ModItems.ELECTRUM_INGOT);
        simpleItem(ModItems.GOLD_DUST);
        simpleItem(ModItems.IRON_DUST);
        simpleItem(ModItems.PIG_IRON);
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.STEEL_INGOT);
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.SILVER_DUST);
        handHeldItem(ModItems.STEEL_SWORD);
        handHeldItem(ModItems.STEEL_PICKAXE);
        handHeldItem(ModItems.STEEL_SHOVEL);
        handHeldItem(ModItems.STEEL_HOE);
        handHeldItem(ModItems.STEEL_AXE);
        simpleItem(ModItems.STEEL_BOOTS);
        simpleItem(ModItems.STEEL_LEGGINGS);
        simpleItem(ModItems.STEEL_CHESTPLATE);
        simpleItem(ModItems.STEEL_HELMET);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(AlloyMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(AlloyMod.MOD_ID, "item/" + item.getId().getPath()));
    }


}
