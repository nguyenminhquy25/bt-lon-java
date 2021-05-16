import java.util.Scanner;

public class App {
    public static String[] handleInput(String str) {
        return str.substring(1).replaceAll("[(),]", " ").split("\\s+");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String roomPoints = input.nextLine();
        Room room = new Room();
        room.setPoints(handleInput(roomPoints));
        int numberObject = input.nextInt();
        input.nextLine(); 
        for(int i = 0; i < numberObject; i++) {
            String objectPoints = input.nextLine();
            Object object = new Object();
            object.setPoints(handleInput(objectPoints));
            if(!room.checkObjectValid(object)) {
                System.out.println("Object not valid");
                input.close();
                return;
            }
            room.addObject(object);
        }
        int numberCamera = input.nextInt();
        input.nextLine();
        for(int i = 0; i < numberCamera; i++) {
            String cameraPoints = input.nextLine();
            Camera camera = new Camera(handleInput(cameraPoints));
            room.addCamera(camera);
        }
        input.close();
    }
}
