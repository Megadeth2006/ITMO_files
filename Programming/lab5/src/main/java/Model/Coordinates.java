package Model;

public class Coordinates {
    private int x;
    private double y;

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    @Override
    public String toString() {
        return "(x: " + x + "; y: " + y + ")";
    }
}
