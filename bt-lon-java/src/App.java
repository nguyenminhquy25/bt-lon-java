import java.util.Scanner;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

import java.awt.geom.Area;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App extends JPanel {

	protected ArrayList<Room> rooms = new ArrayList<>();
	protected ArrayList<Color> colors = new ArrayList<>();
	protected ArrayList<Rectangle2d> rec = new ArrayList<>();
	protected ArrayList<Camera2D> cam2d = new ArrayList<>();
	protected ArrayList<Area> Visible = new ArrayList<>();
	protected int i = 0;
	protected int choice;

	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public App() {
		colors.add(Color.blue);
		colors.add(Color.green);
		colors.add(Color.GRAY);
		colors.add(Color.ORANGE);
		colors.add(Color.cyan);
	}

	public static double randomInRange(Double start, Double end) {
		double temp = Math.random() * (end - start) + start;
		return (double) Math.round(temp * 100) / 100;
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
		System.out.println("7. show graphic");
		System.out.println("0. Exit");
	}

	public void Picture1_easy(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxZ() > obj2.getMaxZ()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {

			int obj_x = (int) (obj.getMaxX() * 100);
			int obj_y = (int) (obj.getMaxY() * 100);
			int obj_z = (int) (obj.getMaxZ());
			int a = (int) (obj.getMinX() * 100);
			int b = (int) (obj.getMinY() * 100);
			int width = obj_x - a;
			int height = obj_y - b;

			Graphics2D drawObj = (Graphics2D) g;
			drawObj.setColor(this.colors.get(obj.ID));
			drawObj.fillRect(a, b, width, height);
		}

	}

	public void Picture2_easy(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxY() > obj2.getMaxY()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_x = (int) (obj.getMaxX() * 100);
			int obj_z = (int) (obj.getMaxZ() * 100);
			int a = (int) (obj.getMinX() * 100);
			int b = (int) (this.rooms.get(i).getMaxZ() * 100) - obj_z;
			int width = obj_x - a;
			int height = (int) ((obj.getMaxZ() - obj.getMinZ()) * 100);
			Graphics2D drawObj = (Graphics2D) g;
			drawObj.setColor(this.colors.get(obj.ID));
			drawObj.fillRect(a, b, width, height);

		}

	}

	public void Picture3_easy(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxY() < obj2.getMaxY()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_x = (int) (obj.getMaxX() * 100);
			int obj_z = (int) (obj.getMaxZ() * 100);
			int a = (int) (obj.getMinX() * 100);
			int b = (int) (this.rooms.get(i).getMaxZ() * 100) - obj_z;
			int width = obj_x - a;
			int height = (int) ((obj.getMaxZ() - obj.getMinZ()) * 100);
			Graphics2D drawObj = (Graphics2D) g;

			drawObj.setColor(this.colors.get(obj.ID));
			drawObj.fillRect(a, b, width, height);

		}

	}

	public void Picture4_easy(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxX() > obj2.getMaxX()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_y = (int) (obj.getMaxY() * 100);
			int obj_z = (int) (obj.getMaxZ() * 100);
			int a = (int) (obj.getMinY() * 100);
			int b = (int) (this.rooms.get(i).getMaxZ() * 100) - obj_z;
			int width = obj_y - a;
			int height = (int) ((obj.getMaxZ() - obj.getMinZ()) * 100);
			Graphics2D drawObj = (Graphics2D) g;
			drawObj.setColor(this.colors.get(obj.ID));
			drawObj.fillRect(a, b, width, height);

		}

	}

	public void Picture5_easy(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxX() < obj2.getMaxX()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_y = (int) (obj.getMaxY() * 100);
			int obj_z = (int) (obj.getMaxZ() * 100);
			int a = (int) (obj.getMinY() * 100);
			int b = (int) (this.rooms.get(i).getMaxZ() * 100) - obj_z;
			int width = obj_y - a;
			int height = (int) ((obj.getMaxZ() - obj.getMinZ()) * 100);
			Graphics2D drawObj = (Graphics2D) g;
			drawObj.setColor(this.colors.get(obj.ID));
			drawObj.fillRect(a, b, width, height);

		}

	}

	public void Picture6(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxZ() > obj2.getMaxZ()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_x = (int) (obj.getMaxX() * 100);
			int obj_y = (int) (obj.getMaxY() * 100);
			int obj_z = (int) (obj.getMaxZ());
			int a = (int) (obj.getMinX() * 100);
			int b = (int) (obj.getMinY() * 100);
			int width = obj_x - a;
			int height = obj_y - b;
			rec.add(new Rectangle2d(a, b, width, height, this.colors.get(obj.ID)));
			// Rectangle2d obj_rec = new
			// Rectangle2d(a,b,width,height,this.colors.get(obj.ID));
			// Graphics2D drawObj = (Graphics2D) g;
			// drawObj.setColor(obj_rec.getCl());
			// drawObj.fill(obj_rec.getArea());
		}

		for (Camera camera : this.rooms.get(i).cameras) {
			int width = (int) (camera.getWidth() * 100);
			int height = (int) (camera.getLength() * 100);
			int x = (int) ((camera.getX() * 100) - (width / 2));
			int y = (int) ((camera.getY() * 100) - (width / 2));
			cam2d.add(new Camera2D(x, y, width, height, Color.WHITE));
		}
		for (Camera2D cam : cam2d) {
			cam2d.get(0).getArea().add(cam.getArea());
		}
		Area visibleZone = (Area) cam2d.get(0).getArea().clone();

		for (Rectangle2d vat : rec) {
			cam2d.get(0).getArea().subtract(vat.getArea());
		}
		Graphics2D drawObj = (Graphics2D) g;
		drawObj.setColor(Color.WHITE);
		drawObj.fill(cam2d.get(0).getArea());
		for (Rectangle2d vat : rec) {
			Area tmp = (Area) vat.getArea().clone();
			drawObj.setColor(vat.getCl());
			tmp.subtract(visibleZone);
			vat.getArea().subtract(tmp);
			drawObj.fill(vat.getArea());

		}
	}

	public void Picture7(int colorId, Graphics g) {
		Collections.sort(this.rooms.get(i).objects, new Comparator<Object>() {
			@Override
			public int compare(Object obj1, Object obj2) {
				if (obj1.getMaxZ() > obj2.getMaxZ()) {
					return 1;
				} else {
					if (obj1.getMaxZ() == obj2.getMaxZ()) {
						return 0;
					} else {
						return -1;
					}
				}
			}

		});
		for (Object obj : this.rooms.get(i).objects) {
			int obj_x = (int) (obj.getMaxX() * 100);
			int obj_y = (int) (obj.getMaxY() * 100);
			int obj_z = (int) (obj.getMaxZ());
			int a = (int) (obj.getMinX() * 100);
			int b = (int) (obj.getMinY() * 100);
			int width = obj_x - a;
			int height = obj_y - b;
			rec.add(new Rectangle2d(a, b, width, height, this.colors.get(obj.ID)));
		}

		/*
		 * for (Camera camera : this.rooms.get(i).cameras) { int width = (int)
		 * (camera.getWidth()*100); int height = (int) (camera.getLength()*100);
		 * System.out.println(width +" "+ height); int x = (int) ((camera.getX() * 100)
		 * - (width / 2)); int y = (int) ((camera.getY() * 100) - (height / 2));
		 * cam2d.add(new Camera2D(x, y, width, height, Color.WHITE)); }
		 */

		for (int objNum = 0; objNum < this.rooms.get(i).objects.size(); objNum++) {
			double ratio = (this.rooms.get(i).getMaxZ() - this.rooms.get(i).objects.get(objNum).getMaxZ())
					/ this.rooms.get(i).getMaxZ();
			for (Camera camera : this.rooms.get(i).cameras) {
				int width = (int) (camera.getWidth() * 100 * ratio);
				int height = (int) (camera.getLength() * 100 * ratio);
				System.out.println(width + " " + height);
				int x = (int) ((camera.getX() * 100) - (width / 2));
				int y = (int) ((camera.getY() * 100) - (height / 2));
				cam2d.add(new Camera2D(x, y, width, height, Color.WHITE));
			}
			for (Camera2D cam : cam2d) {
				cam2d.get(0).getArea().add(cam.getArea());

			}
			Area visibleZone = (Area) cam2d.get(0).getArea().clone();
			cam2d.get(0).getArea().subtract(rec.get(objNum).getArea());
			Graphics2D drawObj = (Graphics2D) g;
			drawObj.setColor(Color.WHITE);
			drawObj.fill(cam2d.get(0).getArea());
			Area tmp = (Area) rec.get(objNum).getArea().clone();
			tmp.subtract(visibleZone);
			drawObj.setColor(rec.get(objNum).getCl());
			tmp.subtract(visibleZone);
			rec.get(objNum).getArea().subtract(tmp);
			drawObj.fill(rec.get(objNum).getArea());
		}

		/*
		 * for (Camera2D cam : cam2d) { cam2d.get(0).getArea().add(cam.getArea()); }
		 * Area visibleZone = (Area) cam2d.get(0).getArea().clone();
		 * 
		 * for (Rectangle2d vat : rec) { cam2d.get(0).getArea().subtract(vat.getArea());
		 * } Graphics2D drawObj = (Graphics2D) g; drawObj.setColor(Color.WHITE);
		 * drawObj.fill(cam2d.get(0).getArea()); for (Rectangle2d vat : rec) { Area tmp
		 * = (Area) vat.getArea().clone(); drawObj.setColor(vat.getCl());
		 * tmp.subtract(visibleZone); vat.getArea().subtract(tmp);
		 * drawObj.fill(vat.getArea());
		 * 
		 * point_sang.checkPointInCameraRange(this.rooms.get(i).getCameras().get(k), point_sang,this.rooms.get(i)}
		 */

	}

	public void Picture1(int colorId, Graphics g) {
		
		
		/*for (int point_x = 0; point_x <= (this.rooms.get(i).getMaxX() * 100); point_x++) {
			for (int point_y = 0; point_y <= (this.rooms.get(i).getMaxY() * 100); point_y++) {
				for (int point_z = 0; point_z <= (this.rooms.get(i).getMaxZ() * 100); point_z++) {
					for (int j = 0; j < this.rooms.get(i).getObjects().size(); j++) {
						Point p = new Point(point_x, point_y, point_z);
						if (this.rooms.get(i).getObjects().get(j).checkPointInRectangular(p)) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(this.colors.get(this.rooms.get(i).getObjects().get(j).ID));
							drawObj.fillRect(point_x, point_y, 1, 1);

						}
					}

				}

			}

		}*/
		
		
		
	
		
		for (int point_x_sang = 0; point_x_sang < (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = 0; point_y_sang < (this.rooms.get(i).getMaxY() * 100); point_y_sang++) {
				for (int point_z_sang = 0;  point_z_sang < (this.rooms.get(i).getMaxZ() * 100);  point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						Point point_sang = new Point(point_x_sang, point_y_sang,  point_z_sang); 
			
			
						if (this.rooms.get(i).calculateObscuredArea(this,point_x_sang, point_y_sang,  point_z_sang,0)==2) {
							//point_sang.printPoint();
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_x_sang, point_y_sang, 1, 1);

						}
						
					}
				}
			}
		}
		
		
		
		/*for (int point_x_den = 0; point_x_den <= (this.rooms.get(i).getMaxX() * 100); point_x_den++) {
			for (int point_y_den = 0; point_y_den <= (this.rooms.get(i).getMaxY() * 100); point_y_den++) {
				for (int point_z_den = 0; point_z_den <= (this.rooms.get(i).getMaxZ() * 100); point_z_den++) {
					for (int k = 0; k < this.rooms.get(i).getObjects().size(); k++) {
						Point point_den = new Point(point_x_den, point_y_den, point_z_den);
						if (this.rooms.get(i).getObjects().get(k).checkPointInRectangular(point_den) == false) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.BLACK);
							drawObj.fillRect(point_x_den, point_y_den, 1, 1);

						}
					}
				}
			}
		}*/

		

	}

	public void Picture2(int colorId, Graphics g) {
		/*for (int point_x_den = 0; point_x_den <= (this.rooms.get(i).getMaxX() * 100); point_x_den++) {
			for (int point_y_den = 0; point_y_den <= (this.rooms.get(i).getMaxY() * 100); point_y_den++) {
				for (int point_z_den = 0; point_z_den <= (this.rooms.get(i).getMaxZ() * 100); point_z_den++) {
					for (int k = 0; k < this.rooms.get(i).getObjects().size(); k++) {
						Point point_den = new Point(point_x_den, point_y_den, point_z_den);
						if (this.rooms.get(i).getObjects().get(k).checkPointInRectangular(point_den) == false) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.BLACK);
							drawObj.fillRect(point_y_den, (int) (this.rooms.get(i).getMaxZ() * 100) -point_z_den, 1, 1);

						}
					}
				}
			}
		}
		
		

		for (int point_x = 0; point_x <= (this.rooms.get(i).getMaxX() * 100); point_x++) {
			for (int point_y = 0; point_y <= (this.rooms.get(i).getMaxY() * 100); point_y++) {
				for (int point_z = 0; point_z <= (this.rooms.get(i).getMaxZ() * 100); point_z++) {
					for (int j = 0; j < this.rooms.get(i).getObjects().size(); j++) {
						Point p = new Point(point_x, point_y, point_z);
						if (this.rooms.get(i).getObjects().get(j).checkPointInRectangular(p)) {
							
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(this.colors.get(this.rooms.get(i).getObjects().get(j).ID));
							drawObj.fillRect(point_y,(int) (this.rooms.get(i).getMaxZ() * 100) -point_z, 1, 1);

						}
					}

				}

			}

		}*/
		
		for (int point_x_sang = 0; point_x_sang < (this.rooms.get(i).getMaxX() * 100); point_x_sang++) {
			for (int point_y_sang = 0; point_y_sang < (this.rooms.get(i).getMaxY() * 100); point_y_sang++) {
				for (int point_z_sang = 0;  point_z_sang < (this.rooms.get(i).getMaxZ() * 100);  point_z_sang++) {
					for (int k = 0; k < this.rooms.get(i).getCameras().size(); k++) {
						Point point_sang = new Point(point_x_sang, point_y_sang,  point_z_sang);
						if (this.rooms.get(i).calculateObscuredArea(this,point_x_sang, point_y_sang,  point_z_sang,0)==2) {
							//point_sang.printPoint();
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.YELLOW);
							drawObj.fillRect(point_y_sang, (int) (this.rooms.get(i).getMaxZ() * 100) -point_z_sang, 1, 1);

						}
					}
				}
			}
		}
		

	}
	
	public void Picture3(int colorId, Graphics g) {
		
		
		
		for (int point_x_den =(int) (this.rooms.get(i).getMaxX() * 100); point_x_den >= 0; --point_x_den) {
			for (int point_y_den = 0; point_y_den <= (this.rooms.get(i).getMaxY() * 100); point_y_den++) {
				for (int point_z_den = 0; point_z_den <= (this.rooms.get(i).getMaxZ() * 100); point_z_den++) {
					for (int k = 0; k < this.rooms.get(i).getObjects().size(); k++) {
						Point point_den = new Point(point_x_den, point_y_den, point_z_den);
						if (this.rooms.get(i).getObjects().get(k).checkPointInRectangular(point_den) == false) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.BLACK);
							drawObj.fillRect(point_y_den, (int) (this.rooms.get(i).getMaxZ() * 100) -point_z_den, 1, 1);

						}
					}
				}
			}
		}
		
		

		for (int point_x =(int) (this.rooms.get(i).getMaxX() * 100); point_x >= 0; --point_x) {
			for (int point_y = 0; point_y <= (this.rooms.get(i).getMaxY() * 100); point_y++) {
				for (int point_z = 0; point_z <= (this.rooms.get(i).getMaxZ() * 100); point_z++) {
					for (int j = 0; j < this.rooms.get(i).getObjects().size(); j++) {
						Point p = new Point(point_x, point_y, point_z);
						if (this.rooms.get(i).getObjects().get(j).checkPointInRectangular(p)) {
							// p.printPoint();
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(this.colors.get(this.rooms.get(i).getObjects().get(j).ID));
							drawObj.fillRect(point_y,(int) (this.rooms.get(i).getMaxZ() * 100) -point_z, 1, 1);

						}
					}

				}

			}

		}


	}
	
	public void Picture4(int colorId, Graphics g) {
		
		
		for (int point_x_den = 0; point_x_den <= (this.rooms.get(i).getMaxX() * 100); point_x_den++) {
			for (int point_y_den =(int) (this.rooms.get(i).getMaxY() * 100); point_y_den >=0; --point_y_den)  {
				for (int point_z_den = 0; point_z_den <= (this.rooms.get(i).getMaxZ() * 100); point_z_den++) {
					for (int k = 0; k < this.rooms.get(i).getObjects().size(); k++) {
						Point point_den = new Point(point_x_den, point_y_den, point_z_den);
						if (this.rooms.get(i).getObjects().get(k).checkPointInRectangular(point_den) == false) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.BLACK);
							drawObj.fillRect(point_x_den,(int) (this.rooms.get(i).getMaxZ() * 100) - point_z_den, 1, 1);

						}
					}
				}
			}
		}
		
		

		for (int point_x = 0; point_x <= (this.rooms.get(i).getMaxX() * 100); point_x++) {
			for (int point_y =(int) (this.rooms.get(i).getMaxY() * 100); point_y >=0; --point_y) {
				for (int point_z = 0; point_z <= (this.rooms.get(i).getMaxZ() * 100); point_z++) {
					for (int j = 0; j < this.rooms.get(i).getObjects().size(); j++) {
						Point p = new Point(point_x, point_y, point_z);
						if (this.rooms.get(i).getObjects().get(j).checkPointInRectangular(p)) {
							// p.printPoint();
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(this.colors.get(this.rooms.get(i).getObjects().get(j).ID));
							drawObj.fillRect(point_x,(int) (this.rooms.get(i).getMaxZ() * 100) -point_z, 1, 1);

						}
					}

				}

			}

		}

	}
	
	public void Picture5(int colorId, Graphics g) {
		
		
		for (int point_x_den = 0; point_x_den <= (this.rooms.get(i).getMaxX() * 100); point_x_den++) {
			for (int point_y_den = 0; point_y_den <= (this.rooms.get(i).getMaxY() * 100); point_y_den++) {
				for (int point_z_den = 0; point_z_den <= (this.rooms.get(i).getMaxZ() * 100); point_z_den++) {
					for (int k = 0; k < this.rooms.get(i).getObjects().size(); k++) {
						Point point_den = new Point(point_x_den, point_y_den, point_z_den);
						if (this.rooms.get(i).getObjects().get(k).checkPointInRectangular(point_den) == false) {

							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(Color.BLACK);
							drawObj.fillRect(point_x_den,(int) (this.rooms.get(i).getMaxZ() * 100) - point_z_den, 1, 1);

						}
					}
				}
			}
		}
		
		

		for (int point_x = 0; point_x <= (this.rooms.get(i).getMaxX() * 100); point_x++)  {
			for (int point_y = 0; point_y <= (this.rooms.get(i).getMaxY() * 100); point_y++) {
				for (int point_z = 0; point_z <= (this.rooms.get(i).getMaxZ() * 100); point_z++) {
					for (int j = 0; j < this.rooms.get(i).getObjects().size(); j++) {
						Point p = new Point(point_x, point_y, point_z);
						if (this.rooms.get(i).getObjects().get(j).checkPointInRectangular(p)) {
							// p.printPoint();
							Graphics2D drawObj = (Graphics2D) g;
							drawObj.setColor(this.colors.get(this.rooms.get(i).getObjects().get(j).ID));
							drawObj.fillRect(point_x,(int) (this.rooms.get(i).getMaxZ() * 100) -point_z, 1, 1);

						}
					}

				}

			}

		}

	}

	/*int findpont(int x, int y, int z) {
		int key = 0;
		Point point = new Point();
		Room currentRoom = this.rooms.get(i);
		point.setX(x);
		point.setY(y);
		point.setZ(z);
		label: for (int i = 0; i < currentRoom.getCameras().size(); i++) {
			LineSegment lineSegment = new LineSegment(point, currentRoom.getCameras().get(i));
			if (!checkPointInCameraRange(currentRoom.getCameras().get(i), point)) {
				continue;
			}
			for (int j = 0; j < currentRoom.getObjects().size(); j++) {
				double[] range = new double[6];
				Object currentObject = currentRoom.getObjects().get(j);
				range[1] = Math.min(lineSegment.getRange()[1], currentObject.getPoints()[1].getX());
				range[0] = Math.max(lineSegment.getRange()[0], currentObject.getPoints()[0].getX());
				range[3] = Math.min(lineSegment.getRange()[3], currentObject.getPoints()[3].getY());
				range[2] = Math.max(lineSegment.getRange()[2], currentObject.getPoints()[0].getY());
				range[5] = Math.min(lineSegment.getRange()[5], currentObject.getPoints()[4].getZ());
				range[4] = Math.max(lineSegment.getRange()[4], currentObject.getPoints()[0].getZ());
				if (range[1] - range[0] < 0 || range[3] - range[2] < 0 || range[5] - range[4] < 0) {
					return 1;
				}
				ArrayList<Integer> countZero = new ArrayList<Integer>();
				ArrayList<Integer> countNotZero = new ArrayList<Integer>();
				for (int u = 0; u < 3; u++) {
					if (lineSegment.getDirectionVector()[u] != 0.00d) {
						countNotZero.add(u);
					} else {
						countZero.add(u);
					}
				}
				switch (countNotZero.size()) {
				case 3:
					double[] rangeY = new double[2];
					double[] rangeZ = new double[2];
					double rangeMin = (range[0] - lineSegment.getPoint()[0]) / lineSegment.getDirectionVector()[0];
					double rangeMax = (range[1] - lineSegment.getPoint()[0]) / lineSegment.getDirectionVector()[0];
					if (lineSegment.getDirectionVector()[0] < 0) {
						double temp = rangeMin;
						rangeMin = rangeMax;
						rangeMax = temp;
					}
					rangeY[0] = round(rangeMin * lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
					rangeY[1] = round(rangeMax * lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]);
					if (lineSegment.getDirectionVector()[1] < 0) {
						double temp = rangeY[0];
						rangeY[0] = rangeY[1];
						rangeY[1] = temp;
					}
					rangeZ[0] = round(rangeMin * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
					rangeZ[1] = round(rangeMax * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]);
					if (lineSegment.getDirectionVector()[2] < 0) {
						double temp = rangeZ[0];
						rangeZ[0] = rangeZ[1];
						rangeZ[1] = temp;
					}
					rangeY[0] = Math.max(rangeY[0], range[2]);
					rangeY[1] = Math.min(rangeY[1], range[3]);
					rangeZ[0] = Math.max(rangeZ[0], range[4]);
					rangeZ[1] = Math.min(rangeZ[0], range[5]);
					if (rangeY[1] - rangeY[0] >= 0 && rangeZ[1] - rangeZ[0] >= 0) {
						continue;
					} else {
						return 1;
					}
				case 1:
					int zero1 = countZero.get(0);
					int zero2 = countZero.get(1);
					switch (zero1 + zero2) {
					case 1:
						if (lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1]
								&& lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3]) {
							continue;
						} else {
							return 1;
						}
					case 2:
						if (lineSegment.getPoint()[0] >= range[0] && lineSegment.getPoint()[0] <= range[1]
								&& lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
							continue;
						} else {
							return 1;
						}
					case 3:
						if (lineSegment.getPoint()[1] >= range[2] && lineSegment.getPoint()[1] <= range[3]
								&& lineSegment.getPoint()[2] >= range[4] && lineSegment.getPoint()[2] <= range[5]) {
							continue;
						} else {
							return 1;
						}
					}
					break;
				case 2:
					int nonZero1 = countNotZero.get(0);
					int nonZero2 = countNotZero.get(1);
					int zero = countZero.get(0);
					switch (zero) {
					case 0:
						double miniRangeMin0 = (range[2] - lineSegment.getPoint()[1])
								/ lineSegment.getDirectionVector()[1];
						double miniRangeMax0 = (range[3] - lineSegment.getPoint()[1])
								/ lineSegment.getDirectionVector()[1];
						if (lineSegment.getDirectionVector()[1] < 0) {
							double temp = miniRangeMin0;
							miniRangeMin0 = miniRangeMax0;
							miniRangeMax0 = temp;
						}
						double left0 = Math.max(
								round(miniRangeMin0 * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]),
								range[4]);
						double right0 = Math.min(
								round(miniRangeMax0 * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]),
								range[5]);
						if (lineSegment.getDirectionVector()[2] < 0) {
							double temp = left0;
							left0 = right0;
							right0 = temp;
						}
						if (right0 - left0 >= 0) {
							continue;
						} else {
							return 1;
						}
					case 1:
						double miniRangeMin1 = (range[0] - lineSegment.getPoint()[0])
								/ lineSegment.getDirectionVector()[0];
						double miniRangeMax1 = (range[1] - lineSegment.getPoint()[0])
								/ lineSegment.getDirectionVector()[0];
						if (lineSegment.getDirectionVector()[0] < 0) {
							double temp = miniRangeMin1;
							miniRangeMin1 = miniRangeMax1;
							miniRangeMax1 = temp;
						}
						double left1 = Math.max(
								round(miniRangeMin1 * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]),
								range[4]);
						double right1 = Math.min(
								round(miniRangeMax1 * lineSegment.getDirectionVector()[2] + lineSegment.getPoint()[2]),
								range[5]);
						if (lineSegment.getDirectionVector()[2] < 0) {
							double temp = left1;
							left1 = right1;
							right1 = temp;
						}
						if (right1 - left1 >= 0) {
							continue;
						} else {
							return 1;
						}
					case 2:
						double miniRangeMin2 = (range[0] - lineSegment.getPoint()[0])
								/ lineSegment.getDirectionVector()[0];
						double miniRangeMax2 = (range[1] - lineSegment.getPoint()[0])
								/ lineSegment.getDirectionVector()[0];
						if (lineSegment.getDirectionVector()[0] < 0) {
							double temp = miniRangeMin2;
							miniRangeMin2 = miniRangeMax2;
							miniRangeMax2 = temp;
						}
						double left2 = Math.max(
								round(miniRangeMin2 * lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]),
								range[2]);
						double right2 = Math.min(
								round(miniRangeMax2 * lineSegment.getDirectionVector()[1] + lineSegment.getPoint()[1]),
								range[3]);
						if (lineSegment.getDirectionVector()[1] < 0) {
							double temp = left2;
							left2 = right2;
							right2 = temp;
						}
						if (right2 - left2 >= 0) {
							continue;
						} else {
							return 1;
						}
					}
					break;
				case 0:
					return 1;
				}
			}
		}
		if (key != 2) {
			return 0;
		}
		break;
	}*/

	public void paintComponent(Graphics g) {

		super.paintComponents(g);
		this.setBackground(Color.black);

		int room_x = (int) this.rooms.get(i).getMaxX();
		int room_y = (int) this.rooms.get(i).getMaxY();
		int room_z = (int) this.rooms.get(i).getMaxZ();
		Graphics2D room = (Graphics2D) g;
		room.setColor(Color.black);

		int m = 0;

		switch (this.choice) {
		case 0:
			this.Picture1(m, g);

			break;
		case 1:

			
			this.Picture2(m, g);
			break;
		case 2:
		
			this.Picture3(m, g);
			break;
		case 3:

			
			this.Picture4(m, g);
			break;
		case 4:

			this.Picture5(m, g);
			break;

		}

	}

}