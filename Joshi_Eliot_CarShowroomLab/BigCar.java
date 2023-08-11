import java.awt.Graphics;
import java.awt.Color;

public class BigCar extends Automobile {
    
    
    public BigCar(Color bodyColor, int x, int y) {
        super(bodyColor, x, y);
    }

    public void drawFrame(Graphics g) {
        g.setColor(getBodyColor());
        g.fillRect(getX(), getY(), 79, 35);
    }

    public void drawWheels(Graphics g) {    
        g.setColor(Color.BLACK);
        g.fillOval(getX()+13, getY()+22, 21,21);
        g.fillOval(getX()+47, getY()+22,21,21);
    }
}