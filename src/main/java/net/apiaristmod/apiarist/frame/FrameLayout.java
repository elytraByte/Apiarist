package net.apiaristmod.apiarist.frame;

public enum FrameLayout {
    LARGE(9, 6),
    MEDIUM(9, 4);

    private final int tileColumns;
    private final int tileRows;

    FrameLayout(int tileColumns, int tileRows) {
        this.tileColumns = tileColumns;
        this.tileRows = tileRows;
    }

    public int tileColumns() {
        return tileColumns;
    }

    public int tileRows() {
        return tileRows;
    }

    public int pixelWidth() {
        return tileColumns * 16;
    }

    public int pixelHeight() {
        return tileRows * 16;
    }

    public static FrameLayout fromOrdinal(int ordinal) {
        FrameLayout[] values = values();
        if (ordinal < 0 || ordinal >= values.length) {
            return LARGE;
        }
        return values[ordinal];
    }
}
