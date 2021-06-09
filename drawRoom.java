package test;

import java.util.Scanner;

import javax.swing.JFrame;

public class drawRoom extends JFrame {
	App app = new App();

	public drawRoom(App app1) {
		app = app1;
		this.setSize(420, 420);
		this.setTitle("Camera");
		this.setLocationRelativeTo(null);
		this.add(app);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
    public static void main(String[] args) {
        App app = new App();
        while(true) {
            app.printMenu();
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
                    switch(Room.calculateObscuredArea(app,0.2, 
                        1.6,0,0)) {
                        case 0:
                            System.out.println("No room exist");
                            break;
                        case 1:
                            System.out.println("Point is in object");
                            break;
                        case 2:
                            System.out.println("Point is not on the object and it is in the viewable area of camera");
                            break;
                        case 3: 
                            System.out.println("Point is not on the object and it is not in the viewable area of camera");
                            break;
                    }
                    break;
                case "4":
                    Room.percentVisible(app);
                    break;
                    
                case "5":
                	int choice;
    				System.out.println("pick number from 1 to 5");
    				choice = input.nextInt();
    				app.setChoice(choice);

    				drawRoom draw = new drawRoom(app);
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
