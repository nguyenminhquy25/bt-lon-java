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
    public void printPoint() { 
        System.out.println("Point X: " + this.coordinates[0] + " Y: " + this.coordinates[1] + " Z: " + this.coordinates[2]);
    }
    public static void main(String[] args) {
        
    }
}
