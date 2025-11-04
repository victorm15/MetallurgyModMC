package net.victej.alloymod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.victej.alloymod.item.ModItems;
import net.victej.alloymod.screen.ForgeMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3);

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;



    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxprogress = 100;


    public ForgeBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FORGE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.progress;
                    case 1 -> ForgeBlockEntity.this.maxprogress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> ForgeBlockEntity.this.progress = pValue;
                    case 1 -> ForgeBlockEntity.this.maxprogress = pValue;

                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }











    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition,inventory);

    }



    @Override
    public Component getDisplayName() {
        return Component.translatable("block.alloymod.forge");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerid, Inventory pPlayerInventory, Player player) {
        return new ForgeMenu(pContainerid, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("forge.progress", progress);


        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("forge.progress");
    }


    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
            setChanged(pLevel, pPos, pState);
        }

    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.PIG_IRON.get(), 1);

        this.itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT_2, 1, false);

        // Ensure correct handling when setting the output slot
        if (this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()) {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, result);
        } else {
            this.itemHandler.getStackInSlot(OUTPUT_SLOT).grow(result.getCount());
        }


    }

    private boolean hasProgressFinished() {
        return progress >= maxprogress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem =
                ((this.itemHandler.getStackInSlot(INPUT_SLOT_1).getItem() == ModItems.IRON_DUST.get()) &&
                        (this.itemHandler.getStackInSlot(INPUT_SLOT_2).getItem() == ModItems.COKE.get())) ||
                        ((this.itemHandler.getStackInSlot(INPUT_SLOT_2).getItem() == ModItems.IRON_DUST.get()) &&
                                (this.itemHandler.getStackInSlot(INPUT_SLOT_1).getItem() == ModItems.COKE.get()));
        ItemStack result = new ItemStack(ModItems.PIG_IRON.get());

        return hasCraftingItem && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());




    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);

    }



    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() +
                count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();

    }
}
