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
    public static double round(Double number) {
        double temp = Math.round(number * 100.0) / 100.0;
        return temp;
    }
    public static void printMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Create a new room");
        System.out.println("2. Set up new cameras");
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
                    Room.createNewRoom(app);
                    break;
                case "2":
                    Room.setUpNewCameras(app);
                    break;
                case "3":
                    Room.calculateObscuredArea(app);
                    break;
                case "7":
                    Room currentRoom = app.getRooms().get(app.getRooms().size() - 1);
                    currentRoom.printCameras();
                    currentRoom.printObjects();
                    break;
            }   
        }
    }
}
