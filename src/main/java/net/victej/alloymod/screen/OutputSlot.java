package net.victej.alloymod.screen;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.world.item.ItemStack;

public class OutputSlot extends SlotItemHandler {
    public OutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return false; // Prevent insertion into the output slot
    }
}