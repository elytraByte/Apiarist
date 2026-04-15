package net.apiaristmod.apiarist.screen;

import net.apiaristmod.apiarist.Apiarist;
import net.apiaristmod.apiarist.frame.FrameCellState;
import net.apiaristmod.apiarist.hexgrid.HexCell;
import net.apiaristmod.apiarist.hexgrid.HexGridLayout;
import net.apiaristmod.apiarist.menu.FrameBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FrameBlockScreen extends AbstractContainerScreen<FrameBlockMenu> {
    private static final Identifier BG =
            Identifier.fromNamespaceAndPath(Apiarist.MODID, "textures/gui/frame_block.png");

    private static final Identifier HOVER_SPRITE =
            Identifier.fromNamespaceAndPath(Apiarist.MODID, "textures/gui/frame_cells/hover.png");

    // Actual honeycomb panel bounds inside the GUI texture
    private static final int FRAME_X = 25;
    private static final int FRAME_Y = 18;
    private static final int FRAME_WIDTH = 160;
    private static final int FRAME_HEIGHT = 96;

    private static final int CELL_SPRITE_SIZE = 5;
    private static final int CELL_GAP = 3;
    private static final int CELL_INTERLEAVE_OFFSET_X = 4;
    private static final int CELL_INTERLEAVE_OFFSET_Y = 4;

    private static final int GUI_OFFSET_X = 24;
    private static final int GUI_OFFSET_Y = 0;

    private HexGridLayout grid;

    public FrameBlockScreen(FrameBlockMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        this.imageWidth = 256;
        this.imageHeight = 256;

        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
        this.titleLabelX = 8;
        this.titleLabelY = 6;
    }

    @Override
    protected void init() {
        super.init();


        this.leftPos += GUI_OFFSET_X;
        this.topPos += GUI_OFFSET_Y;

        rebuildGrid();
    }

    private void rebuildGrid() {
        this.grid = HexGridLayout.buildHoneycombPixelGrid(
                FRAME_X,
                FRAME_Y,
                FRAME_WIDTH,
                FRAME_HEIGHT,
                CELL_SPRITE_SIZE,
                CELL_GAP,
                CELL_INTERLEAVE_OFFSET_X,
                CELL_INTERLEAVE_OFFSET_Y
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);

        HexCell hovered = getHoveredCell(mouseX, mouseY);
        if (hovered != null) {
            FrameCellState state = getDebugState(hovered);

            List<Component> tooltip = new ArrayList<>();
            tooltip.add(Component.literal("Cell " + hovered.id()));
            tooltip.add(Component.literal("Type: ").append(state.displayName()));
            tooltip.add(Component.literal("q=" + hovered.q() + ", r=" + hovered.r()));

            graphics.setTooltipForNextFrame(this.font, tooltip, Optional.empty(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                BG,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                256,
                256
        );

        HexCell hovered = getHoveredCell(mouseX, mouseY);

        for (HexCell cell : grid.cells()) {
            int localDrawX = cell.drawX(CELL_SPRITE_SIZE);
            int localDrawY = cell.drawY(CELL_SPRITE_SIZE);

            // Hard clip to the honeycomb panel
            if (localDrawX < FRAME_X || localDrawY < FRAME_Y
                    || localDrawX + CELL_SPRITE_SIZE > FRAME_X + FRAME_WIDTH
                    || localDrawY + CELL_SPRITE_SIZE > FRAME_Y + FRAME_HEIGHT) {
                continue;
            }

            FrameCellState state = getDebugState(cell);

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    state.sprite(),
                    this.leftPos + localDrawX,
                    this.topPos + localDrawY,
                    0,
                    0,
                    CELL_SPRITE_SIZE,
                    CELL_SPRITE_SIZE,
                    CELL_SPRITE_SIZE,
                    CELL_SPRITE_SIZE
            );
        }

        if (hovered != null) {
            int hoverX = hovered.drawX(CELL_SPRITE_SIZE);
            int hoverY = hovered.drawY(CELL_SPRITE_SIZE);

            if (hoverX >= FRAME_X && hoverY >= FRAME_Y
                    && hoverX + CELL_SPRITE_SIZE <= FRAME_X + FRAME_WIDTH
                    && hoverY + CELL_SPRITE_SIZE <= FRAME_Y + FRAME_HEIGHT) {
                graphics.blit(
                        RenderPipelines.GUI_TEXTURED,
                        HOVER_SPRITE,
                        this.leftPos + hoverX,
                        this.topPos + hoverY,
                        0,
                        0,
                        CELL_SPRITE_SIZE,
                        CELL_SPRITE_SIZE,
                        CELL_SPRITE_SIZE,
                        CELL_SPRITE_SIZE
                );
            }
        }
    }

    private @Nullable HexCell getHoveredCell(double mouseX, double mouseY) {
        if (grid == null) {
            return null;
        }

        double relativeX = mouseX - this.leftPos;
        double relativeY = mouseY - this.topPos;
        return grid.cellAt(relativeX, relativeY);
    }

    // Demo only. Replace with real synced frame data later.
    private FrameCellState getDebugState(HexCell cell) {
        int n = Math.floorMod(cell.id(), 10);
        return switch (n) {
            case 0 -> FrameCellState.EMPTY;
            case 1 -> FrameCellState.EGG;
            case 2 -> FrameCellState.LARVA;
            case 3 -> FrameCellState.PUPA;
            case 4 -> FrameCellState.BROOD;
            case 5 -> FrameCellState.POLLEN;
            case 6 -> FrameCellState.NECTAR;
            case 7 -> FrameCellState.HONEY;
            case 8 -> FrameCellState.CAPPED_HONEY;
            default -> FrameCellState.DISEASED;
        };
    }
}