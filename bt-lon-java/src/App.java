import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    private ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }
    public static double randomInRange(Double start, Double end) {
        double temp = Math.random() * (end - start) + start;
        return (double)Math.round(temp * 100) / 100;
    }
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    public boolean checkRange(double range) {
        return true;
    }
    public static double round(Double number) {
        double temp = Math.round(number * 100.0) / 100.0;
        return temp;
    }
    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Create a new room");
        System.out.println("2. Set up a new camera");
        System.out.println("3. Calculate obscured area");
        System.out.println("4. Show upobscured area");
        System.out.println("5. Determine minimum cameras are need to added");
        System.out.println("6. Determine cameras potition");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) {
        App app = new App();
        while(true) {
            printMenu();
            Scanner input = new Scanner(System.in);
            System.out.println("Choose option: ");
            String option = input.nextLine();
            switch(option) {
                case "0":
                    System.out.println("Exit");
                    input.close();
                    return;
                case "1":
                    try {
                        File obj = new File("./src/roomInput.txt");
                        Scanner reader = new Scanner(obj);
                        String roomPoints = reader.nextLine();
                        Room room = new Room();
                        room.setPoints(handleInput(roomPoints));
                        if(room.checkPoints() == false) {
                            System.out.println("Room is not valid");
                            input.close();
                            return;
                        }
                        app.getRooms().add(room);
                        int numberObject = reader.nextInt();
                        reader.nextLine(); 
                        for(int i = 0; i < numberObject; i++) {
                            String objectPoints = reader.nextLine();
                            Object object = new Object();
                            object.setPoints(handleInput(objectPoints));
                            if(room.checkObjectValid(object) == false || object.checkPoints() == false) { 
                                System.out.println("Object is not valid");
                                input.close();
                                return;
                            }
                            room.addObject(object);
                        }
                        System.out.println("New room was created");
                        reader.close();
                        break;
                    } 
                    catch (FileNotFoundException e) {
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    }
                case "2":
                    if(app.getRooms().size() == 0) {
                        System.out.println("No room exist");
                        input.close();
                        return;
                    }
                    try {
                        File obj = new File("./src/cameraInput.txt");
                        Scanner reader = new Scanner(obj);
                        int numberCamera = reader.nextInt();
                        reader.nextLine();
                        for(int i = 0; i < numberCamera; i++) {
                            String cameraPoints = reader.nextLine();
                            Camera camera = new Camera(handleInput(cameraPoints));
                            Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                            if(currentRoom.checkCameraValid(camera) == false || currentRoom.checkCameraIsRoomPoint(camera)) {
                                System.out.println("Camera is not valid");
                                input.close();
                                return;
                            }
                            currentRoom.addCamera(camera);
                            System.out.println("New camera was added");
                        }
                        reader.close();
                        break;  
                    } 
                    catch (FileNotFoundException e) {
                        System.out.println("An error occurred");
                        e.printStackTrace();
                    } 
                case "3":
                    if(app.getRooms().size() == 0) {
                        System.out.println("No room exist");
                        input.close();
                        return;
                    }
                    int key = 0;
                    Point point = new Point();
                    Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                    point.setX(2.00d);
                    point.setY(1.00d);
                    point.setZ(0.00d);
                    for(int i = 0; i < currentRoom.getObjects().size(); i++) {
                        if(currentRoom.getObjects().get(i).checkPointInRectangular(point)) {
                            point.printPoint();
                            System.out.println("Point is in object");
                            key = 1;
                            break;
                        }
                    }
                    if(key == 1) {
                        break;
                    }
                    label: for(int i = 0; i < currentRoom.getCameras().size(); i++) {
                        LineSegment lineSegment = new LineSegment(point, currentRoom.getCameras().get(i));
                        for(int j = 0; j < currentRoom.getObjects().size(); j++) {
                            double[] range = new double[6];
                            Object currentObject = currentRoom.getObjects().get(j);
                            range[1] = Math.min(lineSegment.getRange()[1], currentObject.getPoints()[1].getX());
                            range[0] = Math.max(lineSegment.getRange()[0], currentObject.getPoints()[0].getX());
                            range[3] = Math.min(lineSegment.getRange()[3], currentObject.getPoints()[3].getY());
                            range[2] = Math.max(lineSegment.getRange()[2], currentObject.getPoints()[0].getY());
                            range[5] = Math.min(lineSegment.getRange()[5], currentObject.getPoints()[4].getZ());
                            range[4] = Math.max(lineSegment.getRange()[4], currentObject.getPoints()[0].getZ());
                            if(range[1] - range[0] < 0 || range[3] - range[2] < 0 || range[5] - range[4] < 0) {
                                point.printPoint();
                                key = 2;
                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                break label;
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
                            switch(countNotZero.size()) {
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
                                    rangeY[0] = round(rangeMin *  lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
                                    rangeY[1] = round(rangeMax *  lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
                                    if(lineSegment.getDirectionVector()[1] < 0) {
                                        double temp = rangeY[0];
                                        rangeY[0] = rangeY[1];
                                        rangeY[1] = temp;
                                    }
                                    rangeZ[0] = round(rangeMin *  lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
                                    rangeZ[1] = round(rangeMax *  lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
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
                                        continue;
                                    }
                                    else {
                                        point.printPoint();
                                        key = 2;
                                        System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                        break label;
                                    }
                                case 1:
                                    int zero1 = countZero.get(0);
                                    int zero2 = countZero.get(1);
                                    switch(zero1 + zero2) {
                                        case 1:
                                            if(lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1] &&
                                            lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3]) {
                                                continue;
                                            } 
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
                                            }
                                        case 2:
                                            if(lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1] &&
                                            lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
                                                continue;
                                            } 
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
                                            }
                                        case 3:
                                            if(lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3] &&
                                            lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
                                                continue;
                                            } 
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
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
                                            double left0 = Math.max(round(miniRangeMin0 * lineSegment.getDirectionVector()[2] 
                                            + lineSegment.getPoint()[2]), range[4]);
                                            double right0 = Math.min(round(miniRangeMax0 * lineSegment.getDirectionVector()[2] 
                                            + lineSegment.getPoint()[2]), range[5]);
                                            if(lineSegment.getDirectionVector()[2] < 0) {
                                                double temp = left0;
                                                left0 = right0;
                                                right0 = temp;
                                            }
                                            if(right0 - left0 >= 0) {
                                                continue;
                                            }
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
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
                                            double left1 = Math.max(round(miniRangeMin1 * lineSegment.getDirectionVector()[2] 
                                            + lineSegment.getPoint()[2]), range[4]);
                                            double right1 = Math.min(round(miniRangeMax1 * lineSegment.getDirectionVector()[2] 
                                            + lineSegment.getPoint()[2]), range[5]);
                                            if(lineSegment.getDirectionVector()[2] < 0) {
                                                double temp = left1;
                                                left1 = right1;
                                                right1 = temp;
                                            }
                                            if(right1 - left1 >= 0) {
                                                continue;
                                            }
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
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
                                            double left2 = Math.max(round(miniRangeMin2 * lineSegment.getDirectionVector()[1] 
                                            + lineSegment.getPoint()[1]), range[2]);
                                            double right2 = Math.min(round(miniRangeMax2 * lineSegment.getDirectionVector()[1] 
                                            + lineSegment.getPoint()[1]), range[3]);
                                            if(lineSegment.getDirectionVector()[1] < 0) {
                                                double temp = left2;
                                                left2 = right2;
                                                right2 = temp;
                                            }
                                            if(right2 - left2 >= 0) {
                                                continue;
                                            }
                                            else {
                                                point.printPoint();
                                                key = 2;
                                                System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                                break label;
                                            }
                                    }
                                    break;
                                case 0:
                                    point.printPoint();
                                    System.out.println("Point is not on the object and it is in the viewable area of ​​a cameras");
                                    key = 2;
                                    break label;
                            }
                        }
                    }
                    if(key != 2) {
                        point.printPoint();
                        System.out.println("Point is not on the object and it is not in the viewable area of ​​a cameras");
                    }
                    break;
                case "7":
                    currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                    currentRoom.printCameras();
                    currentRoom.printObjects();
                    break;
            }   
        }
    }
}
