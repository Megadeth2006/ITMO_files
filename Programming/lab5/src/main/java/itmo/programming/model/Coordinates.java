package itmo.programming.model;

public class Coordinates {
    private final int x;
    private final double y;

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(x: " + x + "; y: " + y + ")";
    }
}
