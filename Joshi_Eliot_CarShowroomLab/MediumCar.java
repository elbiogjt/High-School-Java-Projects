import java.awt.Graphics;
import java.awt.Color;

public class MediumCar extends Automobile {
    
    
    public MediumCar(Color bodyColor, int x, int y) {
        super(bodyColor, x, y);
    }

    public void drawFrame(Graphics g) {
        g.setColor(getBodyColor());
        g.fillRect(getX(), getY(), 53, 23);
    }

    public void drawWheels(Graphics g) {    
        g.setColor(Color.BLACK);
        g.fillOval(getX()+7, getY()+12, 15,15);
        g.fillOval(getX()+30, getY()+12,15,15);
    }
}