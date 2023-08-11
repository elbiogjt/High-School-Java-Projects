import java.awt.Graphics;
import java.awt.Color;

public class SportsCar extends MediumCar {
    public SportsCar (Color bodyColor, int x, int y) {
        super(bodyColor,x,y);
    }

    public void drawFrame(Graphics g) {
        super.drawFrame(g);
        int[] xArray = new int[3];
        int[] yArray = new int[3];
        xArray[0] = getX();
        yArray[0] = getY();
        xArray[1] = getX();
        yArray[1] = getY()+23;
        xArray[2] = getX()-20;
        yArray[2] = getY()+23;
        g.fillPolygon(xArray,yArray,3);

        g.setColor(Color.YELLOW);
        g.fillOval(getX()-7,getY()+8,8,8);
        g.setColor(Color.BLACK);
        g.drawOval(getX()-7,getY()+8,8,8);

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
    } 
}