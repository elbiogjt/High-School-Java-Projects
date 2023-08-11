import java.awt.Graphics;
import java.awt.Color;

public class Sedan extends MediumCar {
    public Sedan(Color bodyColor, int x, int y) {
        super(bodyColor,x,y);
    }

    public void drawFrame(Graphics g) {
        super.drawFrame(g);
        g.setColor(Color.YELLOW);
        g.fillOval(getX()-1,getY()+7,8,8);
        g.setColor(Color.BLACK);
        g.drawOval(getX()-1,getY()+7,8,8);

        int[] xWindow = new int[4];
        int[] yWindow = new int[4];   
        xWindow[0] = getX();
        yWindow[0] = getY();
        xWindow[1] = getX()+7;
        yWindow[1] = getY()-7;
        xWindow[2] = getX()+48;
        yWindow[2] = getY()-7;
        xWindow[3] = getX()+52;
        yWindow[3] = getY();
        g.setColor(new Color(211,225,241));
        g.fillPolygon(xWindow,yWindow,4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xWindow,yWindow,4);
        g.fillRect(getX()+24,getY()-6,1,30);
        
        g.drawLine(getX(), getY(), getX()+8, getY()+15);
        g.drawLine(getX()+52, getY(), getX()+45, getY()+15); 
    } 
}