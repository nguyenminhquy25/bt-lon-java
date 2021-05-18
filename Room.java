import java.util.ArrayList;

public class Room extends Rectangular {
	protected ArrayList<Object> objects = new ArrayList<Object>();
	protected ArrayList<Camera> cameras = new ArrayList<Camera>();

	public void addCamera(Camera camera) {
		this.cameras.add(camera);
	}

	public void addObject(Object object) {
		this.objects.add(object);
	}

	public void printCameras() {
		for (int i = 0; i < cameras.size(); i++) {
			cameras.get(i).printCamera();
		}
	}

	public void printObjects() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).printObject();
		}
	}

	public boolean checkPointInRoom(Point point) {
		return point.getX() >= this.getPoints()[0].getX() && point.getX() <= this.getPoints()[1].getX()
				&& point.getY() >= this.getPoints()[0].getY() && point.getY() <= this.getPoints()[3].getY()
				&& point.getZ() >= this.getPoints()[0].getZ() && point.getZ() <= this.getPoints()[4].getZ();
	}

	public boolean checkObjectInRoom(Object object) {
		for (int i = 0; i < 8; i++) {
			if (!checkPointInRoom(object.getPoints()[i])) {
				return false;
			}
		}
		return true;
	}

	public boolean checkObjectValid(Object o) {
		if (!checkObjectInRoom(o)) {
			return false;
		}
		if (Point.checkPointInRectangle(o.getPoints()[0], this.getPoints()[0], this.getPoints()[1],
				this.getPoints()[3])) {
			return true;
		}
		for (int i = 0; i < this.objects.size(); i++) {
			ArrayList<Integer> count = new ArrayList<Integer>();
			if (Point.checkPointInRectangle(o.getPoints()[0], this.objects.get(i).getPoints()[4],
					this.objects.get(i).getPoints()[5], this.objects.get(i).getPoints()[6])) {
				count.add(0);
			}
			if (Point.checkPointInRectangle(o.getPoints()[1], this.objects.get(i).getPoints()[4],
					this.objects.get(i).getPoints()[5], this.objects.get(i).getPoints()[6])) {
				count.add(1);
			}
			if (Point.checkPointInRectangle(o.getPoints()[2], this.objects.get(i).getPoints()[4],
					this.objects.get(i).getPoints()[5], this.objects.get(i).getPoints()[6])) {
				count.add(2);
			}
			if (Point.checkPointInRectangle(o.getPoints()[3], this.objects.get(i).getPoints()[4],
					this.objects.get(i).getPoints()[5], this.objects.get(i).getPoints()[6])) {
				count.add(3);
			}
			switch (count.size()) {
			case 1:
				if (o.getPoints()[count.get(0)].getX() > this.getPoints()[0].getX()
						&& o.getPoints()[count.get(0)].getX() < this.getPoints()[1].getX()
						&& o.getPoints()[count.get(0)].getY() > this.getPoints()[0].getY()
						&& o.getPoints()[count.get(0)].getY() < this.getPoints()[3].getX()
						&& o.getPoints()[count.get(0)].getZ() == this.getPoints()[0].getZ()) {
					return true;
				}
				return false;
			case 2:

			case 4:
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

	}
}