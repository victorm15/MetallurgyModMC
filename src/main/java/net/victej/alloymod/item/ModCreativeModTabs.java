package net.victej.alloymod.item;

import net.victej.alloymod.AlloyMod;
import net.victej.alloymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlloyMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ALLOYS = CREATIVE_MODE_TABS.register("alloymod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.IRON_DUST.get()))
                    .title(Component.translatable("creativetab.alloys"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.IRON_DUST.get());
                        pOutput.accept(ModItems.COKE.get());
                        pOutput.accept(ModItems.PIG_IRON.get());
                        pOutput.accept(ModItems.ELECTRUM_INGOT.get());
                        pOutput.accept(ModItems.SILVER_INGOT.get());
                        pOutput.accept(ModItems.GOLD_DUST.get());
                        pOutput.accept(ModItems.RAW_SILVER.get());
                        pOutput.accept(ModItems.SILVER_INGOT.get());
                        pOutput.accept(ModItems.SILVER_DUST.get());

                        pOutput.accept(ModItems.STEEL_SWORD.get());
                        pOutput.accept(ModItems.STEEL_PICKAXE.get());
                        pOutput.accept(ModItems.STEEL_SHOVEL.get());
                        pOutput.accept(ModItems.STEEL_HOE.get());
                        pOutput.accept(ModItems.STEEL_AXE.get());

                        pOutput.accept(ModItems.STEEL_BOOTS.get());
                        pOutput.accept(ModItems.STEEL_LEGGINGS.get());
                        pOutput.accept(ModItems.STEEL_CHESTPLATE.get());
                        pOutput.accept(ModItems.STEEL_HELMET.get());

                        pOutput.accept(ModBlocks.FORGE.get());
                        pOutput.accept(ModBlocks.STEEL_BLOCK.get());
                        pOutput.accept(ModBlocks.SILVER_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_SILVER_BLOCK.get());
                        pOutput.accept(ModBlocks.SILVER_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_SILVER_ORE.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
