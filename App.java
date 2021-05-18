import java.util.Scanner;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class App extends JPanel {

	protected ArrayList<Room> rooms = new ArrayList<>();
	protected ArrayList<Color> colors = new ArrayList<>();
	protected int i = -1;

	public App() {
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.GRAY);
		colors.add(Color.YELLOW);
		colors.add(Color.GREEN);
	}

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

	public void paintComponent(Graphics g) {

		super.paintComponents(g);

		int room_x = (int) this.rooms.get(i).getMaxX();
		int room_y = (int) this.rooms.get(i).getMaxY();
		int room_z = (int) this.rooms.get(i).getMaxZ();
		Scanner input = new Scanner(System.in);
		int n = 2;
		int m = 0;

		switch (n) {
		case 0:
			Graphics2D room = (Graphics2D) g;
			room.setColor(Color.black);
			room.fillRect(0, 0, room_x * 100, room_y * 100);
			for (Object obj : this.rooms.get(i).objects) {

				int obj_x = (int) (obj.getMaxX() * 100);
				int obj_y = (int) (obj.getMaxY() * 100);
				int a = (int) (obj.getMinX() * 100);
				int b = (int) (obj.getMinY() * 100);
				int length = obj_x - a;
				int w = obj_y - b;
				Graphics2D drawObj = (Graphics2D) g;
				drawObj.setColor(this.colors.get(m));
				drawObj.fillRect(a, b, length, w);
				m++;
			}

			break;
		case 1:

			Graphics2D room1 = (Graphics2D) g;
			room1.setColor(Color.black);
			room1.fillRect(0, 0, room_x * 100, room_z * 100);
			for (Object obj : this.rooms.get(i).objects) {
				int obj_x = (int) (obj.getMaxX() * 100);
				int obj_z = (int) (obj.getMaxZ() * 100);
				int a = (int) (obj.getMinX() * 100);
				int b = room_z * 100 - obj_z;
				int length = obj_x - a;
				int w = obj_z;
				Graphics2D drawObj = (Graphics2D) g;
				drawObj.setColor(this.colors.get(m));
				drawObj.fillRect(a, b, length, w);
				m++;

			}
			break;
		case 2:
			Graphics2D room2 = (Graphics2D) g;
			room2.setColor(Color.black);
			room2.fillRect(0, 0, room_y * 100, room_z * 100);
			for (Object obj : this.rooms.get(i).objects) {
				int obj_y = (int) (obj.getMaxY() * 100);
				int obj_z = (int) (obj.getMaxZ() * 100);
				int a = (int) (obj.getMinY() * 100);
				int b = room_z * 100 - obj_z;
				int length = obj_y - a;
				int w = obj_z;
				Graphics2D drawObj = (Graphics2D) g;
				drawObj.setColor(this.colors.get(m));
				drawObj.fillRect(a, b, length, w);
				m++;

			}

			break;

		}

	}

}