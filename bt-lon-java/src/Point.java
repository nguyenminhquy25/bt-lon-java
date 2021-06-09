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
    public static boolean checkPointInCameraRange(Camera camera, Point point,Room room) {
		Point projectionXZ = new Point();
		Point projectionXY = new Point();
		Point O = new Point();
		if (camera.getX() == 0 || camera.getX() == room.getMaxX()) {
			projectionXZ.setX(point.getX());
			projectionXZ.setY(camera.getY());
			projectionXZ.setZ(point.getZ());
			projectionXY.setX(point.getX());
			projectionXY.setY(point.getY());
			projectionXY.setZ(camera.getZ());
			O.setX(point.getX());
			O.setY(camera.getY());
			O.setZ(camera.getZ());
			return App.round(Point.getRange2Point(projectionXZ, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getWideAngle()/2) / 180))
					&& App.round(Point.getRange2Point(projectionXY, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getHighAngle()/2) / 180));
		}
		if (camera.getY() == 0 || camera.getY() == room.getMaxY()) {
			projectionXZ.setX(point.getX());
			projectionXZ.setY(point.getY());
			projectionXZ.setZ(camera.getZ());
			projectionXY.setX(camera.getX());
			projectionXY.setY(point.getY());
			projectionXY.setZ(point.getZ());
			O.setX(camera.getX());
			O.setY(point.getY());
			O.setZ(camera.getZ());
			return App.round(Point.getRange2Point(projectionXZ, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getWideAngle()/2) / 180))
					&& App.round(Point.getRange2Point(projectionXY, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getHighAngle()/2) / 180));
		}
		if (camera.getZ() == (room.getMaxZ() )) {

			projectionXZ.setX(point.getX());
			projectionXZ.setY(camera.getY());
			projectionXZ.setZ(point.getZ());

			projectionXY.setX(camera.getX());
			projectionXY.setY(point.getY());
			projectionXY.setZ(point.getZ());

			O.setX(camera.getX());
			O.setY(camera.getY());
			O.setZ(point.getZ());
			point.printPoint();
			projectionXZ.printPoint();
			projectionXY.printPoint();
			O.printPoint();
			return App.round(Point.getRange2Point(projectionXZ, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getWideAngle()/2) / 180))
					&& App.round(Point.getRange2Point(projectionXY, O) / Point.getRange2Point(O, camera)) <= Math
					.abs(Math.tan(Math.PI * (camera.getHighAngle()/2) / 180));
		}

		return false;
	}
    public static void main(String[] args) {
        
    }
}