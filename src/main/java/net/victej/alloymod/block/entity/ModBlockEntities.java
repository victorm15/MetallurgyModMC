package net.victej.alloymod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.victej.alloymod.AlloyMod;
import net.victej.alloymod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AlloyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE_BE =
            BLOCK_ENTITIES.register("forge_be", () ->
                    BlockEntityType.Builder.of(ForgeBlockEntity::new, ModBlocks.FORGE.get()).build(null));










    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);


    }



}
