import java.util.Scanner;
import java.util.ArrayList;

public class App {
    private ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Create new room");
        System.out.println("2. Set up new camera");
        System.out.println("3. Calculate obscured area");
        System.out.println("4. Show obscured area");
        System.out.println("5. Minimum cameras need added");
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
                    String roomPoints = input.nextLine();
                    Room room = new Room();
                    room.setPoints(handleInput(roomPoints));
                    if(!room.checkPoints()) {
                        System.out.println("Room not valid");
                        input.close();
                        return;
                    }
                    app.getRooms().add(room);
                    int numberObject = input.nextInt();
                    input.nextLine(); 
                    for(int i = 0; i < numberObject; i++) {
                        String objectPoints = input.nextLine();
                        Object object = new Object();
                        object.setPoints(handleInput(objectPoints));
                        if(room.checkObjectValid(object) == false || object.checkPoints() == false) { //
                            System.out.println("Object not valid");
                            input.close();
                            return;
                        }
                        room.addObject(object);
                    }
                    System.out.println("New room was created");
                    break;
                case "2":
                    if(app.getRooms().size() == 0) {
                        System.out.println("No room exist");
                        input.close();
                        return;
                    }
                    int numberCamera = input.nextInt();
                    input.nextLine();
                    for(int i = 0; i < numberCamera; i++) {
                        String cameraPoints = input.nextLine();
                        Camera camera = new Camera(handleInput(cameraPoints));
                        app.getRooms().get(app.getRooms().size() - 1).addCamera(camera);
                        System.out.println("New camera was added");
                    }
                    break;
                case "3":
                    System.out.println(app.getRooms().size());
                    for(int i = 0; i < app.getRooms().size(); i++) {
                        app.getRooms().get(i).printCameras();
                        app.getRooms().get(i).printObjects();
                    }
                    break;
            }   
        }
    }
}
