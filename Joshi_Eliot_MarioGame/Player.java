import java.awt.Graphics;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Player {
    private boolean sizeup =false;
    private int x; 
    private int y;
    private int pace = 1;
    private BufferedImage image;

    public Player(int startx, int starty) {
        x=startx;
        y=starty;
        try {
            URL urls = new URL("https://freepngimg.com/thumb/mario/20698-7-mario-transparent-background.png");
            image = ImageIO.read(urls);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawMe(Graphics g) {
        if (sizeup == false) {
            g.drawImage(image, x*5-5, y*5-15, 30,30,null);
        } else { 
            g.drawImage(image, x*5-5, y*5-20, 40,40,null);
        }
    }

    public void sizeUp() {
        sizeup = true;
    }

    public void up() {
        y-=pace;
    }

    public void down() {
        y+=pace;
    }

    public void right() {
        x+=pace;
    }

    public void left() {
        x-=pace;
    }

    public void setCoord(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int[] getCoord() {
        int[] xy = new int[] {x,y};
        return xy;
    }
}
