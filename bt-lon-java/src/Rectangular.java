public class Rectangular {
    private Point[] points = new Point[8];
    public Rectangular() {
        for(int i = 0; i < 8; i++) {
            this.points[i] = new Point();
        }
    }
    public Point[] getPoints() {
        return this.points;
    }
    public void setPoints(String[] pointsInput) {
        for(int i = 0; i < pointsInput.length; i++) {
            if(i % 3 == 0) {
                this.points[i / 3].setX(Double.parseDouble(pointsInput[i]));
                continue;
            }
            else if(i % 3 == 1) {
                this.points[i / 3].setY(Double.parseDouble(pointsInput[i]));
                continue;
            }
            this.points[i / 3].setZ(Double.parseDouble(pointsInput[i]));
        }
    }
    public boolean checkPoints() {
        for(int i = 0; i < 7; i++) {
            for(int j = i + 1; j < 8; j++) {
                if(points[i].getX() == points[j].getX() && points[i].getY() == points[j].getY()
                    && points[i].getZ() == points[j].getZ()) {
                        return false;
                }
            }
        }
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
    public boolean checkPointInRectangular(Point point) {
        return point.getX() >= this.getPoints()[0].getX() && point.getX() <= this.getPoints()[1].getX() && 
            point.getY() >= this.getPoints()[0].getY() && point.getY() <= this.getPoints()[3].getY() &&
            point.getZ() >= this.getPoints()[0].getZ() && point.getZ() <= this.getPoints()[4].getZ();
    }
    public double getMaxX() {
		double x = 0;
		for (int i = 0; i < 7; i++) {

			if (x < points[i].getX()) {
				x = points[i].getX();

			}
		}

		return x;
	}

	public double getMaxY() {
		double y = 0;
		for (int i = 0; i < 7; i++) {

			if (y < points[i].getY()) {
				y = points[i].getY();

			}
		}

		return y;
	}

	public double getMaxZ() {
		double z = 0;
		for (int i = 0; i < 7; i++) {

			if (z < points[i].getZ()) {
				z = points[i].getZ();

			}
		}
		return z;
	}
    public static void main(String[] args) {

    }
}
