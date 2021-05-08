import java.util.Arrays;

public class Rectangular {
    private Point[] points = new Point[8];
    public Rectangular() {
        Arrays.fill(points, new Point());
    }
    public void setPoints(String[] pointsInput) {
        for(int i = 0; i < pointsInput.length; i++) {
            if(i % 3 == 0) {
                this.points[i / 3].setX(Integer.parseInt(pointsInput[i]));
                continue;
            }
            else if(i % 3 == 1) {
                this.points[i / 3].setY(Integer.parseInt(pointsInput[i]));
                continue;
            }
            this.points[i / 3].setZ(Integer.parseInt(pointsInput[i]));
        }
    }
    public boolean checkPoints() {
        if(this.points[0].getX() != this.points[4].getX() || this.points[0].getY() != this.points[4].getY() ||
            this.points[1].getX() != this.points[5].getX() || this.points[1].getY() != this.points[5].getY()) {
            return false;
        }
        if(this.points[3].getX() != this.points[7].getX() || this.points[3].getY() != this.points[7].getY()) {
            return false;
        }
        if(this.points[1].getX() != this.points[0].getX() || this.points[1].getZ() != this.points[0].getZ() ||
            this.points[2].getX() != this.points[3].getX() || this.points[2].getZ() != this.points[3].getZ()) {
            return false;
        }
        if(this.points[2].getX() != this.points[6].getX() || this.points[2].getY() != this.points[6].getY()) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
