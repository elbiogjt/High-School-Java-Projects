import java.awt.Color;
import java.awt.Graphics;

public class Tree{
	private Color brown;
	private Color darkGreen;
	
	public Tree(){	
		brown = new Color(205,133,63);
		darkGreen = new Color(167,139,182);
	}
	

	public void drawMe(Graphics g, int x, int y){
	
		g.setColor(brown);
		g.fillRect(x+30,y+50,40,100); 
		
		g.setColor(darkGreen);
		g.fillOval(x,y,100,100);
	}
}