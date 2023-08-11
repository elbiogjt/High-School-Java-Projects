import java.awt.Graphics;
import java.awt.Color;

public class NPC {
    private String name;
    private Color colour;
    private int xlocation;
    private int ylocation;
    private int height = 80;
    private int width = 40;

    public NPC(String name, int xlocation, int ylocation, Color a) {
        this.name = name;
        this.xlocation = xlocation;
        this.ylocation = ylocation;
        this.colour = a;
    }  

    public void drawMe(Graphics g) {
        g.setColor(colour);
        g.fillOval(xlocation,ylocation,width,width);
        g.setColor(Color.WHITE);
        g.fillRect(xlocation+15,ylocation,10,height);
    }

    public int getX() {
        return xlocation;
    }

    public int getY() {
        return ylocation;
    }

    public int height() {
        return height;
    }
    
    public int width() {
        return width;
    }

    public void changeXY(int xc, int yc) {
        xlocation+=xc;
        ylocation+=yc;
    }
}