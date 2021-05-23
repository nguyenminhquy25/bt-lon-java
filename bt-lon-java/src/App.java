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
        return Math.random() * (end - start) + start;
    }
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Create a new room");
        System.out.println("2. Set up a new camera");
        System.out.println("3. Calculate obscured area");
        System.out.println("4. Show upobscured area");
        System.out.println("5. Determin minimum cameras are need to added");
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
                    Point point = new Point();
                    Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                    point.setX(randomInRange(currentRoom.getPoints()[0].getX(), currentRoom.getPoints()[1].getX()));
                    point.setY(randomInRange(currentRoom.getPoints()[0].getY(), currentRoom.getPoints()[3].getY()));
                    point.setZ(randomInRange(currentRoom.getPoints()[0].getZ(), currentRoom.getPoints()[4].getZ()));
                    for(int i = 0; i < currentRoom.getObjects().size(); i++) {
                        if(currentRoom.getObjects().get(i).checkPointInRectangular(point)) {
                            point.printPoint();
                            System.out.println("Point is in object");
                            break;
                        }
                    }
                case "7":
                    currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                    currentRoom.printCameras();
                    currentRoom.printObjects();
            }   
        }
    }
}
