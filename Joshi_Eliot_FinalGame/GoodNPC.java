import java.awt.Graphics;
import java.awt.Color;

//Eugene is good NPC, needs to be helped or saved
public class GoodNPC extends NPC{
    public GoodNPC(String name, int xlocation, int ylocation, Color color) {
        super(name,xlocation,ylocation,color);
    }  

    public void drawMe(Graphics g) {
        super.drawMe(g);
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public int height() {
        return super.height();
    }
    
    public int width() {
        return super.width();
    }

    public void changeXY(int a, int b) {
        super.changeXY(a,b);
    }
}