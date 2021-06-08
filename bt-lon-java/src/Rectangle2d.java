import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Rectangle2d {
	private int x;
    private int y;
    private int width;
    private int height;
    private Color cl;
    private Area area;
    
    public Rectangle2d(int x, int y, int w, int h,Color color)
    {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.cl = color;
        this.area = new Area(new Rectangle2D.Double(x,y,w,h));
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getCl() {
		return cl;
	}

	public void setCl(Color cl) {
		this.cl = cl;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
    

}
