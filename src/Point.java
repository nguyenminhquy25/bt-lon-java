import java.util.Arrays;

public class Point {
    private int[] coordinates = new int[3];
    public Point() {
        Arrays.fill(coordinates, 0);
    }
    public int getX() {
        return coordinates[0];
    }
    public void setX(int newX) {
        this.coordinates[0] = newX;
    }
    public int getY() {
        return coordinates[1];
    }
    public void setY(int newY) {
        this.coordinates[1] = newY;
    }
    public int getZ() {
        return coordinates[2];
    }
    public void setZ(int newZ) {
        this.coordinates[2] = newZ;
    }
    public static void main(String[] args) {

    }
}
