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
    public static void main(String[] args) {

    }
}
