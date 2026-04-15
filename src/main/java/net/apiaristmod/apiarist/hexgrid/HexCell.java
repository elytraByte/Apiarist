package net.apiaristmod.apiarist.hexgrid;

public final class HexCell {
    private final int id;
    private final int q;
    private final int r;
    private final float centerX;
    private final float centerY;
    private final float[] vx = new float[6];
    private final float[] vy = new float[6];

    public HexCell(int id, int q, int r, float centerX, float centerY, float radius) {
        this.id = id;
        this.q = q;
        this.r = r;
        this.centerX = centerX;
        this.centerY = centerY;

        // Flat-top hex
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(i * 60.0);
            vx[i] = (float) (centerX + radius * Math.cos(angle));
            vy[i] = (float) (centerY + radius * Math.sin(angle));
        }
    }

    public int id() {
        return id;
    }

    public int q() {
        return q;
    }

    public int r() {
        return r;
    }

    public float centerX() {
        return centerX;
    }

    public float centerY() {
        return centerY;
    }

    public boolean contains(double x, double y) {
        boolean inside = false;
        for (int i = 0, j = 5; i < 6; j = i++) {
            boolean intersects =
                    ((vy[i] > y) != (vy[j] > y)) &&
                            (x < (vx[j] - vx[i]) * (y - vy[i]) / (vy[j] - vy[i]) + vx[i]);

            if (intersects) {
                inside = !inside;
            }
        }
        return inside;
    }

    public int drawX(int spriteSize) {
        return Math.round(centerX - spriteSize / 2f);
    }

    public int drawY(int spriteSize) {
        return Math.round(centerY - spriteSize / 2f);
    }
}