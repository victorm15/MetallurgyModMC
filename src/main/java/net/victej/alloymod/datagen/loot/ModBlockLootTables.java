package net.victej.alloymod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.victej.alloymod.block.ModBlocks;
import net.victej.alloymod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SILVER_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SILVER_BLOCK.get());
        this.dropSelf(ModBlocks.STEEL_BLOCK.get());
        this.dropSelf(ModBlocks.FORGE.get());

        // duplicate createRedstoneOreDrop and change params for Custom Drops
        this.add(ModBlocks.SILVER_ORE.get(),
                block -> createSilkTouchDispatchTable(ModBlocks.SILVER_ORE.get(),
                        (LootPoolEntryContainer.Builder)this.applyExplosionDecay(ModBlocks.SILVER_ORE.get(),
                                LootItem.lootTableItem(ModItems.RAW_SILVER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                block -> createSilkTouchDispatchTable(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                        (LootPoolEntryContainer.Builder)this.applyExplosionDecay(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                                LootItem.lootTableItem(ModItems.RAW_SILVER.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
