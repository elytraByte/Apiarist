package net.apiaristmod.apiarist.block.blockentity;

import net.apiaristmod.apiarist.frame.FrameLayout;
import net.apiaristmod.apiarist.menu.FrameBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class FrameBlockEntity extends BlockEntity implements MenuProvider {
    private FrameLayout frameLayout = FrameLayout.LARGE;

    private final ContainerData menuData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> frameLayout.ordinal();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                frameLayout = FrameLayout.fromOrdinal(value);
                setChanged();
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public FrameBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FRAME_BLOCK_ENTITY.get(), pos, state);
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public ContainerData getMenuData() {
        return menuData;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("screen.apiarist.frameblock");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new FrameBlockMenu(containerId, playerInventory, this, menuData);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("frame_layout", frameLayout.ordinal());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        frameLayout = FrameLayout.fromOrdinal(input.getIntOr("frame_layout", 0));
    }
}
