import java.util.Scanner;

import javax.swing.JFrame;

public class drawRoom extends JFrame {
	App app = new App();

	public drawRoom(App app1) {
		app = app1;
		this.setSize(420, 420);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(app);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		App app = new App();

		while (true) {
			app.printMenu();
			Scanner input = new Scanner(System.in);
			System.out.println("Choose option: ");
			String option = input.nextLine();
			switch (option) {
			case "0":
				System.out.println("Exit");
				input.close();
				return;
			case "1":
				String roomPoints = input.nextLine();
				Room room = new Room();
				room.setPoints(app.handleInput(roomPoints));
				if (!room.checkPoints()) {
					System.out.println("Room not valid");
					input.close();
					return;
				}
				app.getRooms().add(room);
				app.i++;

				int numberObject = input.nextInt();
				input.nextLine();
				System.out.println("numbers of object:" + numberObject);
				for (int i = 0; i < numberObject; i++) {
					String objectPoints = input.nextLine();
					Object object = new Object();
					object.setPoints(app.handleInput(objectPoints));
					if (room.checkObjectValid(object) == false || object.checkPoints() == false) {
						System.out.println(room.checkObjectValid(object));
						System.out.println(object.checkPoints());
						System.out.println("Object not valid");
						input.close();
						return;
					}
					room.addObject(object);
				}
				System.out.println("New room was created");
				break;
			case "2":
				if (app.getRooms().size() == 0) {
					System.out.println("No room exist");
					input.close();
					return;
				}
				int numberCamera = input.nextInt();
				input.nextLine();
				for (int i = 0; i < numberCamera; i++) {
					String cameraPoints = input.nextLine();
					Camera camera = new Camera(app.handleInput(cameraPoints));
					app.getRooms().get(app.getRooms().size() - 1).addCamera(camera);
					System.out.println("New camera was added");
				}
				break;
			case "7":
				System.out.println("pick number from 0 to 2");
				app.choice= input.nextInt();
				drawRoom draw = new drawRoom(app);
			}
		}
	}

}
