import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
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
    public ArrayList<Camera> getCameras() {
        return this.cameras;
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
    public boolean checkCameraIsEdge(Camera camera) {
        for(int i = 0; i < 8; i++) {
            int count = 0;
            if(camera.getX() == this.getPoints()[i].getX()) {
                count++;
            }
            if(camera.getY() == this.getPoints()[i].getY()) {
                count++;
            }
            if(camera.getZ() == this.getPoints()[i].getZ()) {
                count++;
            }
            if(count >= 2) {
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
        || camera.getZ() == this.getPoints()[0].getZ() || checkCameraIsRoomPoint(camera) == true || checkCameraExist(camera) ||
        camera.getHighAngle() >= 90 || camera.getWideAngle() >= 90 || checkCameraIsEdge(camera) ? false : true;
    }
    public static void createNewRoom(App app) {
        try {
            File obj = new File("./src/roomInput.txt");
            Scanner reader = new Scanner(obj);
            String roomPoints = reader.nextLine();
            Room room = new Room();
            room.setPoints(App.handleInput(roomPoints));
            if(room.checkPoints() == false) {
                System.out.println("Room is not valid");
                return;
            }
            app.getRooms().add(room);
            int numberObject = reader.nextInt();
            reader.nextLine(); 
            for(int i = 0; i < numberObject; i++) {
                String objectPoints = reader.nextLine();
                Object object = new Object();
                object.setPoints(App.handleInput(objectPoints));
                if(room.checkObjectValid(object) == false || object.checkPoints() == false) { 
                    System.out.println("Object is not valid");
                    return;
                }
                room.addObject(object);
            }
            System.out.println("New room was created");
            reader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public static void setUpNewCameras(App app) {
        if(app.getRooms().size() == 0) {
            System.out.println("No room exist");
            return;
        }
        try {
            File obj = new File("./src/cameraInput.txt");
            Scanner reader = new Scanner(obj);
            int numberCamera = reader.nextInt();
            reader.nextLine();
            for(int i = 0; i < numberCamera; i++) {
                String cameraPoints = reader.nextLine();
                Camera camera = new Camera(App.handleInput(cameraPoints));
                Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                if(currentRoom.checkCameraValid(camera) == false || currentRoom.checkCameraIsRoomPoint(camera)) {
                    System.out.println("Camera is not valid");
                    return;
                }
                currentRoom.addCamera(camera);
                System.out.println("New camera was added");
            }
            reader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        } 
    }
    public static int calculateObscuredArea(App app, double x, double y, double z, int print) {
        if(app.getRooms().size() == 0) {// check có room nào chưa
            return 0;
        }
        Point point = new Point();
        Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
        point.setX(App.round(x));
        point.setY(App.round(y));
        point.setZ(App.round(z));//set tọa độ
        for(int i = 0; i < currentRoom.getObjects().size(); i++) {//check điểm có nằm trong object k
            if(currentRoom.getObjects().get(i).checkPointInRectangular(point)) {
                if(print == 1) {
                    point.printPoint();
                }
                return 1;
            }
        }
        int cameraUnvisible = 0;
        label: for(int i = 0; i < currentRoom.getCameras().size(); i++) {// loop qua all cam
            int count = 0;
            LineSegment lineSegment = new LineSegment(point, currentRoom.getCameras().get(i));
            if(!Point.checkPointInCameraRange(currentRoom.getCameras().get(i), point, currentRoom)) {
                cameraUnvisible++;
                continue;
            }
            for(int j = 0; j < currentRoom.getObjects().size(); j++) {//loop qua all obj
                double[] range = new double[6];
                Object currentObject = currentRoom.getObjects().get(j);
                range[1] = Math.min(lineSegment.getRange()[1], currentObject.getPoints()[1].getX());// có khoảng giao nhau giữa đt và obj
                range[0] = Math.max(lineSegment.getRange()[0], currentObject.getPoints()[0].getX());
                range[3] = Math.min(lineSegment.getRange()[3], currentObject.getPoints()[3].getY());
                range[2] = Math.max(lineSegment.getRange()[2], currentObject.getPoints()[0].getY());
                range[5] = Math.min(lineSegment.getRange()[5], currentObject.getPoints()[4].getZ());
                range[4] = Math.max(lineSegment.getRange()[4], currentObject.getPoints()[0].getZ());
                if(range[1] - range[0] < 0 || range[3] - range[2] < 0 || range[5] - range[4] < 0) {
                    // if(print == 1) {
                    //     point.printPoint();
                    // }
                    // key = 2;
                    // break label;
                    count++;
                    continue;
                }
                ArrayList<Integer> countZero = new ArrayList<Integer>();
                ArrayList<Integer> countNotZero = new ArrayList<Integer>();
                for(int u = 0; u < 3; u++) {
                    if(lineSegment.getDirectionVector()[u] != 0.00d) {
                        countNotZero.add(u);
                    }
                    else {
                        countZero.add(u);
                    }
                }
                switch(countNotZero.size()) {// chia số số trong vto chỉ phương =0
                    case 3:
                        double[] rangeY = new double[2];
                        double[] rangeZ = new double[2];
                        double rangeMin = (range[0] - lineSegment.getPoint()[0]) / lineSegment.getDirectionVector()[0];
                        double rangeMax = (range[1] - lineSegment.getPoint()[0]) / lineSegment.getDirectionVector()[0];
                        if(lineSegment.getDirectionVector()[0] < 0) {
                            double temp = rangeMin;
                            rangeMin = rangeMax;
                            rangeMax = temp;
                        }
                        rangeY[0] = App.round(rangeMin *  lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
                        rangeY[1] = App.round(rangeMax *  lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
                        if(lineSegment.getDirectionVector()[1] < 0) {
                            double temp = rangeY[0];
                            rangeY[0] = rangeY[1];
                            rangeY[1] = temp;
                        }
                        rangeZ[0] = App.round(rangeMin *  lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
                        rangeZ[1] = App.round(rangeMax *  lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
                        if(lineSegment.getDirectionVector()[2] < 0) {
                            double temp = rangeZ[0];
                            rangeZ[0] = rangeZ[1];
                            rangeZ[1] = temp;
                        }
                        rangeY[0] = Math.max(rangeY[0], range[2]);
                        rangeY[1] = Math.min(rangeY[1], range[3]);
                        rangeZ[0] = Math.max(rangeZ[0], range[4]);
                        rangeZ[1] = Math.min(rangeZ[0], range[5]);
                        if(rangeY[1] - rangeY[0] >= 0 && rangeZ[1] - rangeZ[0] >= 0) {
                            // continue;
                            cameraUnvisible++;
                            continue label;
                        }
                        else {
                            // if(print == 1) {
                            //     point.printPoint();
                            // }
                            // key = 2;
                            // break label;
                            count++;
                            continue;
                        }
                    case 1:
                        int zero1 = countZero.get(0);
                        int zero2 = countZero.get(1);
                        switch(zero1 + zero2) {
                            case 1:
                                if(lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1] &&
                                lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3]) {
                                    cameraUnvisible++;
                                    // continue;
                                    continue label;
                                } 
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                            case 2:
                                if(lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1] &&
                                lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
                                    // continue;
                                    cameraUnvisible++;
                                    continue label;
                                } 
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                            case 3:
                                if(lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3] &&
                                lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
                                    // continue;
                                    cameraUnvisible++;
                                    continue label;
                                } 
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                            }
                        break;
                    case 2:
                        int nonZero1 = countNotZero.get(0);
                        int nonZero2 = countNotZero.get(1);
                        int zero = countZero.get(0);
                        switch(zero) {
                            case 0:
                                double miniRangeMin0 = (range[2] - lineSegment.getPoint()[1]) 
                                / lineSegment.getDirectionVector()[1];
                                double miniRangeMax0 = (range[3] - lineSegment.getPoint()[1])
                                / lineSegment.getDirectionVector()[1];
                                if(lineSegment.getDirectionVector()[1] < 0) {
                                    double temp = miniRangeMin0;
                                    miniRangeMin0 = miniRangeMax0;
                                    miniRangeMax0 = temp;
                                }
                                double left0 = Math.max(App.round(miniRangeMin0 * lineSegment.getDirectionVector()[2] 
                                + lineSegment.getPoint()[2]), range[4]);
                                double right0 = Math.min(App.round(miniRangeMax0 * lineSegment.getDirectionVector()[2] 
                                + lineSegment.getPoint()[2]), range[5]);
                                if(lineSegment.getDirectionVector()[2] < 0) {
                                    double temp = left0;
                                    left0 = right0;
                                    right0 = temp;
                                }
                                if(right0 - left0 >= 0) {
                                    // continue;
                                    cameraUnvisible++;
                                    continue label;
                                }
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                            case 1:
                                double miniRangeMin1 = (range[0] - lineSegment.getPoint()[0]) 
                                / lineSegment.getDirectionVector()[0];
                                double miniRangeMax1 = (range[1] - lineSegment.getPoint()[0])
                                / lineSegment.getDirectionVector()[0];
                                if(lineSegment.getDirectionVector()[0] < 0) {
                                    double temp = miniRangeMin1;
                                    miniRangeMin1 = miniRangeMax1;
                                    miniRangeMax1 = temp;
                                }
                                double left1 = Math.max(App.round(miniRangeMin1 * lineSegment.getDirectionVector()[2] 
                                + lineSegment.getPoint()[2]), range[4]);
                                double right1 = Math.min(App.round(miniRangeMax1 * lineSegment.getDirectionVector()[2] 
                                + lineSegment.getPoint()[2]), range[5]);
                                if(lineSegment.getDirectionVector()[2] < 0) {
                                    double temp = left1;
                                    left1 = right1;
                                    right1 = temp;
                                }
                                if(right1 - left1 >= 0) {
                                    // continue;
                                    cameraUnvisible++;
                                    continue label;
                                }
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                            case 2:
                                double miniRangeMin2 = (range[0] - lineSegment.getPoint()[0]) 
                                / lineSegment.getDirectionVector()[0];
                                double miniRangeMax2 = (range[1] - lineSegment.getPoint()[0])
                                / lineSegment.getDirectionVector()[0];
                                if(lineSegment.getDirectionVector()[0] < 0) {
                                    double temp = miniRangeMin2;
                                    miniRangeMin2 = miniRangeMax2;
                                    miniRangeMax2 = temp;
                                }
                                double left2 = Math.max(App.round(miniRangeMin2 * lineSegment.getDirectionVector()[1] 
                                + lineSegment.getPoint()[1]), range[2]);
                                double right2 = Math.min(App.round(miniRangeMax2 * lineSegment.getDirectionVector()[1] 
                                + lineSegment.getPoint()[1]), range[3]);
                                if(lineSegment.getDirectionVector()[1] < 0) {
                                    double temp = left2;
                                    left2 = right2;
                                    right2 = temp;
                                }
                                if(right2 - left2 >= 0) {
                                    // continue;
                                    cameraUnvisible++;
                                    continue label;
                                }
                                else {
                                    // if(print == 1) {
                                    //     point.printPoint();
                                    // }
                                    // key = 2;
                                    // break label;
                                    count++;
                                    continue;
                                }
                        }
                        break;
                    case 0:
                        if(print == 1) {
                            point.printPoint();
                        }
                        return 2;
                }
            }
            if(count == currentRoom.getObjects().size()) {
                if(print == 1) {
                    point.printPoint();
                }
                return 2;
            }
            else {
                if(print == 1) {
                    point.printPoint();
                }
                return 3;
            }
        }
        if(cameraUnvisible == currentRoom.getCameras().size()) {
            if(print == 1) {
                point.printPoint();
            }
            return 3;
        }
        else {
            if(print == 1) {
                point.printPoint();
            }
            return 2;
        }
    }
    public static void percentVisible(App app) {
        double visible = 0.00d;
        int unvisible = 0;
        Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
        for(double i = currentRoom.getPoints()[0].getX(); i <= currentRoom.getPoints()[1].getX(); i += 0.1) {
            for(double j = currentRoom.getPoints()[0].getY(); j <= currentRoom.getPoints()[3].getY(); j += 0.1) {
                for(double u = currentRoom.getPoints()[0].getZ(); u <= currentRoom.getPoints()[4].getZ(); u += 0.1) {
                    switch(Room.calculateObscuredArea(app, App.round(i), App.round(j), App.round(u), 0)) {
                        case 2:
                            visible++;
                            break;
                        case 3:
                            unvisible++;
                            break;
                    }
                }
            }
        }
        System.out.println(App.round(visible / (visible + unvisible) * 100) + "%");
    }
    public static void main(String[] args) {

    }
}