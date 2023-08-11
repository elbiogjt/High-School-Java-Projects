import java.awt.Graphics;
import java.awt.Color;

public class SUV extends BigCar {
    public SUV(Color bodyColor, int x, int y) {
        super(bodyColor,x,y);
    }

    public void drawFrame(Graphics g) {
        super.drawFrame(g);
        g.setColor(Color.YELLOW);
        g.fillOval(getX()+1,getY()+15,8,8);
        g.setColor(Color.BLACK);
        g.drawOval(getX()+1,getY()+15,8,8);

        int[] xWindow = new int[4];
        int[] yWindow = new int[4];   
        xWindow[0] = getX();
        yWindow[0] = getY();
        xWindow[1] = getX()+7;
        yWindow[1] = getY()-12;
        xWindow[2] = getX()+75;
        yWindow[2] = getY()-12;
        xWindow[3] = getX()+78;
        yWindow[3] = getY();
        g.setColor(new Color(211,225,241));
        g.fillPolygon(xWindow,yWindow,4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xWindow,yWindow,4);
        g.fillRect(getX()+38,getY()-12,1,47);
        
        g.drawLine(getX(), getY(), getX()+28, getY()+35);
        g.drawLine(getX()+79, getY(), getX()+58, getY()+35); 
    } 
}