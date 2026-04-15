package net.apiaristmod.apiarist.menu;

import net.apiaristmod.apiarist.block.ModBlocks;
import net.apiaristmod.apiarist.block.blockentity.FrameBlockEntity;
import net.apiaristmod.apiarist.frame.FrameLayout;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;

public class FrameBlockMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final BlockPos blockPos;
    private final ContainerData data;

    public FrameBlockMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf buf) {
        this(
                containerId,
                playerInventory,
                ContainerLevelAccess.NULL,
                buf.readBlockPos(),
                new SimpleContainerData(1)
        );
    }

    public FrameBlockMenu(int containerId, Inventory playerInventory, FrameBlockEntity blockEntity, ContainerData data) {
        this(
                containerId,
                playerInventory,
                ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                blockEntity.getBlockPos(),
                data
        );
    }

    private FrameBlockMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access, BlockPos blockPos, ContainerData data) {
        super(ModMenuTypes.FRAME_BLOCK_MENU.get(), containerId);
        this.access = access;
        this.blockPos = blockPos;
        this.data = data;

        checkContainerDataCount(data, 1);
        this.addDataSlots(data);
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public FrameLayout getFrameLayout() {
        return FrameLayout.fromOrdinal(this.data.get(0));
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.FRAME_BLOCK.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
