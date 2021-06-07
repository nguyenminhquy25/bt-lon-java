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
    public static boolean checkPointInRectangle(Point p1, Point p2, Point p3, Point p4) {
        return p1.getX() >= p2.getX() && p1.getX() <= p3.getX() &&
        p1.getY() >= p2.getY() && p1.getY() <= p4.getY() &&
        p1.getZ() == p2.getZ();
    }
    public static double getRange2Point(Point p1, Point p2) {
        return App.round(Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()) +
        (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ())));
    }
    public static boolean checkPointInCameraRange(Camera camera, Point point) {
        Point projectionXZ = new Point();
        Point projectionXY = new Point();
        projectionXZ.setX(point.getX());
        projectionXZ.setY(camera.getY());
        projectionXZ.setZ(point.getZ());
        projectionXY.setX(point.getX());
        projectionXY.setY(point.getY());
        projectionXY.setZ(camera.getZ());
        return App.round(Point.getRange2Point(projectionXZ, point) / Point.getRange2Point(camera, projectionXZ)) <= Math.tan(camera.getWideAngle() / 2)
        && App.round(Point.getRange2Point(projectionXY, point) / Point.getRange2Point(camera, projectionXY)) <= Math.tan(camera.getHighAngle() / 2);
    }
    public static void main(String[] args) {
        
    }
}
