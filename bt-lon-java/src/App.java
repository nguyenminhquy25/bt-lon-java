import java.util.Arrays;

public class App {
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    public static double getRange(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()) +
                (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ()));
    }
    public static double getDistance(Point p1, Point p2, Point p3, Point p4) {
        return Math.sqrt(1 / (1 / (getRange(p1, p2) * getRange(p1, p2)) + 1 / (getRange(p1, p3) * getRange(p1, p3)) +
                1 / (getRange(p1, p4) * getRange(p1, p4))));
    } 
    public static void main(String[] args) {
        
    }
}
