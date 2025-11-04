package net.victej.alloymod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.victej.alloymod.AlloyMod;
import net.victej.alloymod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(3, 3000, 6.0f, 2.0f, 14,
                    ModTags.Blocks.NEEDS_STEEL_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            new ResourceLocation(AlloyMod.MOD_ID, "steel"), List.of(Tiers.IRON), List.of());
}
