package test;

import java.util.Scanner;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class App extends JPanel {
	private ArrayList<Room> rooms = new ArrayList<>();
	private int i;
	private int choice;

	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public static double randomInRange(Double start, Double end) {
		double temp = Math.random() * (end - start) + start;
		return (double) Math.round(temp * 100) / 100;
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

	public void Picture1(int colorId, Graphics g) {

		for (int point_x_sang = 0; point_x_sang <= (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = 0; point_y_sang <= (this.rooms.get(i).getMaxY() * 100); point_y_sang++) {
				for (int point_z_sang = 0; point_z_sang <= (this.rooms.get(i).getMaxZ() * 100); point_z_sang++) {

					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						double a = point_x_sang;
						double b = point_y_sang;
						double c = point_z_sang;
						a = a / 100;
						b = b / 100;
						c = c / 100;

						if (this.rooms.get(i).calculateObscuredArea(this, a, b, c, 0) == 2) {
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_x_sang, point_y_sang, 1, 1);

						}

					}
				}
			}
		}

	}

	public void Picture2(int colorId, Graphics g) {

		for (int point_x_sang = 0; point_x_sang < (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = 0; point_y_sang < (this.rooms.get(i).getMaxY() * 100); point_y_sang++) {
				for (int point_z_sang = 0; point_z_sang < (this.rooms.get(i).getMaxZ() * 100); point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						double a = point_x_sang;
						double b = point_y_sang;
						double c = point_z_sang;
						a = a / 100;
						b = b / 100;
						c = c / 100;

						if (this.rooms.get(i).calculateObscuredArea(this, a, b, c, 0) == 2) {
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_y_sang, (int) (this.rooms.get(i).getMaxZ() * 100) - point_z_sang, 1,
									1);

						}
					}
				}
			}
		}

	}

	public void Picture3(int colorId, Graphics g) {

		for (int point_x_sang = (int) (this.rooms.get(i).getMaxX() * 100); point_x_sang >= 0; --point_x_sang) {
			for (int point_y_sang = 0; point_y_sang < (this.rooms.get(i).getMaxY() * 100); point_y_sang++) {
				for (int point_z_sang = 0; point_z_sang < (this.rooms.get(i).getMaxZ() * 100); point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						double a = point_x_sang;
						double b = point_y_sang;
						double c = point_z_sang;
						a = a / 100;
						b = b / 100;
						c = c / 100;

						if (this.rooms.get(i).calculateObscuredArea(this, a, b, c, 0) == 2) {
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_y_sang, (int) (this.rooms.get(i).getMaxZ() * 100) - point_z_sang, 1,
									1);

						}
					}
				}
			}
		}

	}

	public void Picture4(int colorId, Graphics g) {

		for (int point_x_sang = 0; point_x_sang < (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = (int) (this.rooms.get(i).getMaxY() * 100); point_y_sang >= 0; --point_y_sang) {
				for (int point_z_sang = 0; point_z_sang < (this.rooms.get(i).getMaxZ() * 100); point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						double a = point_x_sang;
						double b = point_y_sang;
						double c = point_z_sang;
						a = a / 100;
						b = b / 100;
						c = c / 100;

						if (this.rooms.get(i).calculateObscuredArea(this, a, b, c, 0) == 2) {
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_x_sang, (int) (this.rooms.get(i).getMaxZ() * 100) - point_z_sang, 1,
									1);

						}
					}
				}
			}
		}

	}

	public void Picture5(int colorId, Graphics g) {

		for (int point_x_sang = 0; point_x_sang < (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = (int) (this.rooms.get(i).getMaxY() * 100); point_y_sang >= 0; --point_y_sang) {
				for (int point_z_sang = 0; point_z_sang < (this.rooms.get(i).getMaxZ() * 100); point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						double a = point_x_sang;
						double b = point_y_sang;
						double c = point_z_sang;
						a = a / 100;
						b = b / 100;
						c = c / 100;

						if (this.rooms.get(i).calculateObscuredArea(this, a, b, c, 0) == 2) {
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_x_sang, (int) (this.rooms.get(i).getMaxZ() * 100) - point_z_sang, 1,
									1);

						}
					}
				}
			}
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponents(g);
		this.setBackground(Color.black);
		this.setI(rooms.size() - 1);
		int room_x = (int) this.rooms.get(i).getMaxX();
		int room_y = (int) this.rooms.get(i).getMaxY();
		int room_z = (int) this.rooms.get(i).getMaxZ();
		Graphics2D room = (Graphics2D) g;
		room.setColor(Color.black);
		int m = 0;

		switch (this.choice) {
		case 1:

			room.fillRect(0, 0, room_x * 100, room_y * 100);
			this.Picture1(m, g);

			break;
		case 2:

			room.fillRect(0, 0, room_y * 100, room_z * 100);
			this.Picture2(m, g);
			break;
		case 3:

			room.fillRect(0, 0, room_y * 100, room_z * 100);

			this.Picture3(m, g);
			break;
		case 4:
			room.fillRect(0, 0, room_x * 100, room_z * 100);

			this.Picture4(m, g);
			break;
		case 5:
			room.fillRect(0, 0, room_x * 100, room_z * 100);

			this.Picture5(m, g);
			break;

		}

	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public static void main(String[] args) {

	}
}
