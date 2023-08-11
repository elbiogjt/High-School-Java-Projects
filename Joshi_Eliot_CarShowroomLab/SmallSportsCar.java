import java.awt.Graphics;
import java.awt.Color;

public class SmallSportsCar extends SmallCar{
	public SmallSportsCar(Color bodyColor, int x, int y) {
        super(bodyColor, x, y);
    }

    public void drawFrame(Graphics g) {
        super.drawFrame(g);
        int[] xArray = new int[3];
        int[] yArray = new int[3];
        xArray[0] = getX();
        yArray[0] = getY();
        xArray[1] = getX();
        yArray[1] = getY()+15;
        xArray[2] = getX()-15;
        yArray[2] = getY()+15;
        g.fillPolygon(xArray,yArray,3);
        g.setColor(Color.YELLOW);
        g.fillOval(getX()-7,getY()+5,5,5);
        g.setColor(Color.BLACK);
        g.drawOval(getX()-7,getY()+5,5,5);

        g.setColor(Color.BLACK);
        g.fillRect(getX()+15,getY(),2,15);
        g.drawLine(getX(), getY(), getX()+8, getY()+15);

        int[] xWindow = new int[4];
        int[] yWindow = new int[4];
        xWindow[0] = getX();
        yWindow[0] = getY();
        xWindow[1] = getX()+7;
        yWindow[1] = getY()-6;
        xWindow[2] = getX()+31;
        yWindow[2] = getY()-6;
        xWindow[3] = getX()+34;
        yWindow[3] = getY();
        g.setColor(new Color(211,225,241));
        g.fillPolygon(xWindow,yWindow,4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xWindow,yWindow,4);
        g.fillRect(getX()+15,getY()-6,1,21);
    }
}