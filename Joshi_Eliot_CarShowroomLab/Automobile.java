import java.awt.Graphics;
import java.awt.Color;

public abstract class Automobile {
	private Color bodyColor;
	private int x = 100;
	private int y = 100;
	private int width;
	private int height;
	
	public Automobile(Color bodyColor, int x, int y){

		this.bodyColor = bodyColor;
		this.x = x;
		this.y = y;
	}

	public void changeX(int x) {
		this.x = x;
	}

	public void changeBodyColor(Color a) {
		this.bodyColor = a;
	}
		
	public void drawMe(Graphics g){
		
		this.drawFrame(g);
		this.drawWheels(g);
	}
	
    //abstract means child is supposed to complete it
	public abstract void drawFrame(Graphics g);
	public abstract void drawWheels(Graphics g);

    public Color getBodyColor() {
        return bodyColor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	public void animate() {
		x-=1;
		if (x <= -100) {
			x = 820;
		}
	}
}