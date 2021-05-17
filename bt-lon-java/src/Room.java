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
    public boolean checkPointInRoom(Point point) {
        return point.getX() >= this.getPoints()[0].getX() && point.getX() <= this.getPoints()[1].getX() && 
            point.getY() >= this.getPoints()[0].getY() && point.getY() <= this.getPoints()[3].getY() &&
            point.getZ() >= this.getPoints()[0].getZ() && point.getZ() <= this.getPoints()[4].getZ();
    }
    public boolean checkObjectInRoom(Object object) {
        for(int i = 0; i < 8; i++) {
            if(!checkPointInRoom(object.getPoints()[i])) {
                return false;
            }
        }
        return true;
    }
    public boolean checkObjectValid(Object o) {
        if(!checkObjectInRoom(o)) {
            return false;
        }
        if(o.getPoints()[0].getX() >= this.getPoints()[0].getX() && o.getPoints()[0].getX() <= this.getPoints()[1].getX() &&
        o.getPoints()[0].getY() >= this.getPoints()[0].getY() && o.getPoints()[0].getY() >= this.getPoints()[3].getY() &&
        o.getPoints()[0].getZ() == this.getPoints()[0].getZ()) {
            return true;
        }
        // for(int i = 0; i < this.objects.size(); i++) { nam tren vat khac ?

        // }
        return false;
    }
    public static void main(String[] args) {

    }
}
