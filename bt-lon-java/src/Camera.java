import java.awt.geom.Area;

public class Camera extends Point {
	private int wideAngle;
	private int highAngle;
	private double width;
	private double length;
	private double height;
	public Camera(double x,double y,double z,int wide,int high) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.wideAngle = wide;
		this.highAngle = high;
		
		
	}

	public Camera(String[] cameraPoints, Room room) {
		this.setX(Double.parseDouble(cameraPoints[0]));
		this.setY(Double.parseDouble(cameraPoints[1]));
		this.setZ(Double.parseDouble(cameraPoints[2]));
		this.wideAngle = Integer.parseInt(cameraPoints[3]);
		this.highAngle = Integer.parseInt(cameraPoints[4]);
	
		if (this.getY() == 0 || this.getY() == room.getMaxY()) {
			this.setHeight(room.getMaxY()*100);
			this.setWidth((Math.tan(Math.PI * (this.wideAngle/2) / 180)*room.getMaxY()*2)*100);
			this.setLength((Math.tan(Math.PI * (this.highAngle/2) / 180)*room.getMaxY()*2)*100);
		}
		if (this.getX() == 0 || this.getX() == room.getMaxX()) {
			this.setHeight(room.getMaxY()*100);
			this.setWidth((Math.tan(Math.PI * (this.wideAngle/2) / 180)*room.getMaxX()*2)*100);
			this.setLength((Math.tan(Math.PI * (this.highAngle/2) / 180)*room.getMaxX()*2)*100);
			

		}
		if (this.getZ() == room.getMaxZ()) {
			this.setHeight(room.getMaxZ()*100);
			this.setWidth((Math.tan(Math.PI * (this.wideAngle/2) / 180)*room.getMaxZ()*2)*100);
			this.setLength((Math.tan(Math.PI * (this.highAngle/2) / 180)*room.getMaxZ()*2)*100);		
		}

	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double d) {
		this.height = d;
	}

	public int getWideAngle() {
		return wideAngle;
	}

	public void setWideAngle(int wideAngle) {
		this.wideAngle = wideAngle;
	}

	public int getHighAngle() {
		return highAngle;
	}

	public void setHighAngle(int highAngle) {
		this.highAngle = highAngle;
	}

	public void printCamera() {
		System.out.println("Camera X:" + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ() + " Wide: "
				+ this.width + " length: " + this.length + " high: "+this.height);
	}

	public static void main(String[] args) {

	}
}