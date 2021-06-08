import java.awt.Color;
import java.util.ArrayList;

public class Object extends Rectangular {
	protected int ID = 0;

	public Object(int id) {
		this.ID = id;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
