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
    public ArrayList<Object> getObjects() {
        return this.objects;
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
        if(Point.checkPointInRectangle(o.getPoints()[0], this.getPoints()[0], this.getPoints()[1], this.getPoints()[3])) {
            return true;
        }
        for(int i = 0; i < this.objects.size(); i++) { 
            ArrayList<Integer> count = new ArrayList<Integer>();
            if(Point.checkPointInRectangle(o.getPoints()[0], this.objects.get(i).getPoints()[4], this.objects.get(i).getPoints()[5],
            this.objects.get(i).getPoints()[7])) {
                count.add(0);
            }
            if(Point.checkPointInRectangle(o.getPoints()[1], this.objects.get(i).getPoints()[4], this.objects.get(i).getPoints()[5],
            this.objects.get(i).getPoints()[7])) {
                count.add(1);
            }
            if(Point.checkPointInRectangle(o.getPoints()[2], this.objects.get(i).getPoints()[4], this.objects.get(i).getPoints()[5],
            this.objects.get(i).getPoints()[7])) {
                count.add(2);
            }
            if(Point.checkPointInRectangle(o.getPoints()[3], this.objects.get(i).getPoints()[4], this.objects.get(i).getPoints()[5],
            this.objects.get(i).getPoints()[6])) {
                count.add(3);
            }
            switch(count.size()) {
                case 0:
                    if(o.getPoints()[3].getY() < this.getPoints()[4].getY() || o.getPoints()[0].getY() > this.getPoints()[5].getY() ||
                    o.getPoints()[1].getX() < this.getPoints()[4].getX() || o.getPoints()[0].getX() > this.getPoints()[5].getX()) {
                        return false;
                    }
                    return true;
                case 1:
                    if(o.getPoints()[count.get(0)].getX() > this.getPoints()[4].getX() && 
                    o.getPoints()[count.get(0)].getX() < this.getPoints()[5].getX() &&
                    o.getPoints()[count.get(0)].getY() > this.getPoints()[4].getY() &&
                    o.getPoints()[count.get(0)].getY() < this.getPoints()[7].getX() &&
                    o.getPoints()[count.get(0)].getZ() == this.getPoints()[4].getZ()) {
                        return true;
                    }
                    return false;
                case 2:
                    if(count.get(0) == 0 &&  count.get(1) == 1) {
                        return o.getPoints()[0].getY() == this.getPoints()[4].getY();
                    } 
                    else if(count.get(0) == 1 && count.get(1) == 2) {
                        return o.getPoints()[1].getX() == this.getPoints()[5].getX();
                    }
                    else if(count.get(0) == 2 && count.get(1) == 3) {
                        return o.getPoints()[2].getY() == this.getPoints()[6].getY();
                    }
                    else if(count.get(0) == 0 && count.get(1) == 3) {
                        return o.getPoints()[0].getX() == this.getPoints()[7].getX();
                    }
                case 4:
                    return true;
            }
        }
        return false;
    }
    public boolean checkCameraIsRoomPoint(Camera camera) {
        for(int i = 0 ; i < 8; i++) {
            Point point = this.getPoints()[i];
            if(camera.getX() == point.getX() && camera.getY() == point.getY() && camera.getZ() == point.getZ()) {
                return true;
            }
        }
        return false;
    }
    public boolean checkCameraExist(Camera camera) {
        for(int i = 0; i < this.cameras.size(); i++) {
            Camera currentCamera = this.cameras.get(i);
            if(camera.getX() == currentCamera.getX() && camera.getY() == currentCamera.getY()
            && camera.getZ() == currentCamera.getZ()) {
                return true;
            }
        }
        return false;
    }
    public boolean checkCameraValid(Camera camera) {
        return camera.getX() > this.getPoints()[0].getX() && camera.getX() < this.getPoints()[1].getX() &&
        camera.getY() > this.getPoints()[0].getY() && camera.getY() < this.getPoints()[3].getY() &&
        camera.getZ() > this.getPoints()[0].getZ() && camera.getZ() < this.getPoints()[4].getZ() 
        || camera.getZ() == this.getPoints()[0].getZ() || checkCameraIsRoomPoint(camera) == true || checkCameraExist(camera)
        ? false : true;
    }
    public static void main(String[] args) {

    }
}
