import java.util.Arrays;

public class Rectangular {
    private Point[] points = new Point[8];
    public Rectangular() {
        for(int i = 0; i < 8; i++) {
            this.points[i] = new Point();
        }
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
        if(this.points[3].getX() != this.points[0].getX() || this.points[3].getZ() != this.points[0].getZ() ||
            this.points[7].getX() != this.points[4].getX() || this.points[7].getZ() != this.points[4].getZ()) {
                return false;
            }
        if(this.points[4].getX() != this.points[0].getX() || this.points[4].getY() != this.points[0].getY() ||
            this.points[7].getX() != this.points[3].getX() || this.points[7].getY() != this.points[3].getY()) {
                return false;
            }
        if(this.points[5].getX() != this.points[1].getX() || this.points[5].getY() != this.points[1].getY()) {
            return false;
        }
        if(this.points[1].getY() != this.points[0].getY() || this.points[1].getZ() != this.points[0].getZ() ||
            this.points[5].getY() != this.points[4].getY() || this.points[5].getZ() != this.points[4].getZ()) {
                return false;
            }
        if(this.points[2].getX() != this.points[1].getX() || this.points[2].getZ() != this.points[1].getZ()) {
            return false;
        }
        if(this.points[2].getY() != this.points[3].getY() || this.points[2].getZ() != this.points[3].getZ()) {
            return false;
        }
        if(this.points[6].getY() != this.points[7].getY() || this.points[6].getZ() != this.points[7].getZ()) {
            return false;
        }
        if(this.points[6].getX() != this.points[2].getX() || this.points[6].getY() != this.points[2].getY()) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
