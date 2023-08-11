import java.awt.Graphics;
import java.awt.Color;

public class ColorBox {
    private int x;
    private int y;
    private int red;
    private int green;
    private int blue;
    private int size;

    public ColorBox (int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
        size = 30;
    }

    public void drawMe(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;
        g.setColor(new Color(red, green, blue));
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }

    public boolean inBox(int xMouse,int yMouse) {
        if ((xMouse >= x) && (xMouse < x+size)) {
            if ((yMouse >= y) && (yMouse < y+size)) {
                return true;
            }
        }
        return false;
    }

    public int returnRed() {
        return red;
    }

    public int returnGreen() {
        return green;
    }

    public int returnBlue() {
        return blue;
    }
}