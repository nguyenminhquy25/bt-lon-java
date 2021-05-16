public class Point {
    private double[] coordinates = new double[3];
    public Point() {
        for(int i = 0; i < 3; i++) {
            this.coordinates[i] = 0.00d;
        }
    }
    public double getX() {
        return this.coordinates[0];
    }
    public void setX(double newX) {
        this.coordinates[0] = newX;
    }
    public double getY() {
        return this.coordinates[1];
    }
    public void setY(double newY) {
        this.coordinates[1] = newY;
    }
    public double getZ() {
        return this.coordinates[2];
    }
    public void setZ(double newZ) {
        this.coordinates[2] = newZ;
    }
    public static double getRange(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()) +
                (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ()));
    }
    public static double getDistance(Point p1, Point p2, Point p3, Point p4) {
        return Math.sqrt(1 / (1 / (getRange(p1, p2) * getRange(p1, p2)) + 1 / (getRange(p1, p3) * getRange(p1, p3)) +
                1 / (getRange(p1, p4) * getRange(p1, p4))));
    } 
    public void printPoint() { 
        System.out.println("Point X: " + this.coordinates[0] + " Y: " + this.coordinates[1] + " Z: " + this.coordinates[2]);
    }
    public static void main(String[] args) {
        
    }
}
