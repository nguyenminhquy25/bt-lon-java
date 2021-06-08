

public class LineSegment {
    private double[] directionVector = new double[3];
    private double[] range = new double[6];
    private double[] point = new double[3];
    public LineSegment(Point p1, Point p2) {
        this.directionVector[0] = p1.getX() - p2.getX();
        this.directionVector[1] = p1.getY() - p2.getY();
        this.directionVector[2] = p1.getZ() - p2.getZ();
        this.range[0] = Math.min(p1.getX(), p2.getX());
        this.range[1] = Math.max(p1.getX(), p2.getX());
        this.range[2] = Math.min(p1.getY(), p2.getY());
        this.range[3] = Math.max(p1.getY(), p2.getY());
        this.range[4] = Math.min(p1.getZ(), p2.getZ());
        this.range[5] = Math.max(p1.getZ(), p2.getZ());
        this.point[0] = p1.getX();
        this.point[1] = p1.getY();
        this.point[2] = p1.getZ();
    }
    public double[] getDirectionVector() {
        return this.directionVector;
    }
    public double[] getRange() {
        return this.range;
    }
    public double[] getPoint() {
        return this.point;
    }
}
