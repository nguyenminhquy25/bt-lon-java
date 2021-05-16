import java.util.ArrayList;

public class Room extends Rectangular {
    private ArrayList<Object> objects = new ArrayList<Object>();
    private ArrayList<Camera> cameras = new ArrayList<Camera>();
    public void addCamera(Camera camera) {
        this.cameras.add(camera);
    }
    public void addObject(Object object) {
        this.objects.add(object);
    }
    public void printCameras() {
        for(int i = 0; i < cameras.size(); i++) {
            cameras.get(i).printCamera();
        }
    }
    public void printObjects() {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).printObject();
        }
    }
    public boolean checkObjectValid(Object object) {
        for(int i = 0; i < 8; i++) {
            if(this.getPoints()[i].getX() >= object.getPoints()[i].getX() || this.getPoints()[i].getY() >= object.getPoints()[i].getY()
                || this.getPoints()[i].getZ() >= object.getPoints()[i].getZ()) {
                    return false;
                }
        }
        return true;
    }
    public static void main(String[] args) {

    }
}
