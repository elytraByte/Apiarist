package net.apiaristmod.apiarist.hexgrid;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class HexGridLayout {
    private final List<HexCell> cells;

    private HexGridLayout(List<HexCell> cells) {
        this.cells = cells;
    }

    public static HexGridLayout buildHoneycombPixelGrid(
            int originX,
            int originY,
            int width,
            int height,
            int spriteSize,
            int gap,
            int interleaveOffsetX,
            int interleaveOffsetY
    ) {
        List<HexCell> cells = new ArrayList<>();

        float centerOffset = spriteSize / 2f;
        int step = spriteSize + gap;
        int id = 0;

        id = addLayer(
                cells,
                id,
                originX,
                originY,
                width,
                height,
                spriteSize,
                step,
                0,
                0,
                centerOffset,
                0
        );

        id = addLayer(
                cells,
                id,
                originX,
                originY,
                width,
                height,
                spriteSize,
                step,
                interleaveOffsetX,
                interleaveOffsetY,
                centerOffset,
                1
        );

        return new HexGridLayout(cells);
    }

    private static int addLayer(
            List<HexCell> cells,
            int startId,
            int originX,
            int originY,
            int width,
            int height,
            int spriteSize,
            int step,
            int offsetX,
            int offsetY,
            float centerOffset,
            int layer
    ) {
        int id = startId;

        for (int col = 0; ; col++) {
            float drawX = originX + offsetX + col * step;
            if (drawX + spriteSize > originX + width) {
                break;
            }

            for (int row = 0; ; row++) {
                float drawY = originY + offsetY + row * step;
                if (drawY + spriteSize > originY + height) {
                    break;
                }

                float centerX = drawX + centerOffset;
                float centerY = drawY + centerOffset;

                cells.add(new HexCell(
                        id++,
                        col * 2 + layer,
                        row,
                        centerX,
                        centerY,
                        spriteSize / 2f
                ));
            }
        }

        return id;
    }

    public List<HexCell> cells() {
        return cells;
    }

    public @Nullable HexCell cellAt(double x, double y) {
        for (HexCell cell : cells) {
            if (cell.contains(x, y)) {
                return cell;
            }
        }
        return null;
    }
}
