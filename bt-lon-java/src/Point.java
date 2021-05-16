import java.util.Arrays;

public class Point {
    private int[] coordinates = new int[3];
    public Point() {
        for(int i = 0; i < 3; i++) {
            this.coordinates[i] = 0;
        }
    }
    public int getX() {
        return this.coordinates[0];
    }
    public void setX(int newX) {
        this.coordinates[0] = newX;
    }
    public int getY() {
        return this.coordinates[1];
    }
    public void setY(int newY) {
        this.coordinates[1] = newY;
    }
    public int getZ() {
        return this.coordinates[2];
    }
    public void setZ(int newZ) {
        this.coordinates[2] = newZ;
    }
    public static void main(String[] args) {

    }
}
