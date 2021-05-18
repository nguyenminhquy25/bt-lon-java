public class Camera extends Point {
	private int wideAngle;
	private int highAngle;

	public Camera(String[] cameraPoints) {
		this.setX(Double.parseDouble(cameraPoints[0]));
		this.setY(Double.parseDouble(cameraPoints[1]));
		this.setZ(Double.parseDouble(cameraPoints[2]));
		this.wideAngle = Integer.parseInt(cameraPoints[3]);
		this.highAngle = Integer.parseInt(cameraPoints[4]);
	}

	public void printCamera() {
		System.out.println("Camera X:" + this.getX() + " Y: " + this.getY() + " Z: " + this.getZ() + " Wide: "
				+ this.wideAngle + " High: " + this.highAngle);
	}

	public static void main(String[] args) {

	}
}