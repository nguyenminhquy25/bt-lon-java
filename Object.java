import java.util.ArrayList;

public class Object extends Rectangular {

	public Object() {

	}

	public void printObject() {
		Point[] points = this.getPoints();
		for (int i = 0; i < points.length; i++) {
			points[i].printPoint();
		}
	}

	public static void main(String[] args) {

	}
}
