import java.awt.Graphics;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;

public class NPC {
    private boolean drawable = true;
    private int x; 
    private int y;
    private int pace = 1;
    private BufferedImage image;

    public NPC(int startx, int starty,String url) {
        x=startx;
        y=starty;
        try {
            URL urls = new URL(url);
            image = ImageIO.read(urls);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean drawAble() {
        return drawable;
    }
    
    public void unDraw() {
        drawable = false; 
    }

    public void drawMe(Graphics g) {
        if (drawable) {
            g.drawImage(image, x*5-5, y*5-15, 30,30,null);
        }
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

    public int[] getCoord() {
        int[] xy = new int[] {x,y};
        return xy;
    } 

    public void move(String upable, String downable, String rightable, String leftable) {
        String moveableArea = "3";
        if (drawAble()) {
            boolean fact = false;
            while (fact != true) {
                int rand = (int)(Math.random()*4);
                if (rand == 0) {   
                    if (upable.equals(moveableArea)) {
                        up();
                        fact = true;
                    }
                } else if (rand == 1) {
                    if (leftable.equals(moveableArea)) {
                        left();
                        fact = true;
                    }
                } else if (rand == 2) {
                    if (rightable.equals(moveableArea)) {
                        right();
                        fact = true;
                    }
                } else if (rand == 3) { 
                    if (downable.equals(moveableArea)) {
                        down();
                        fact = true;
                    }
                }
            }
        }
        return;
    }

    public void playSound(String audioFileLoc) {
        if (drawable) {
            try {
                URL url = this.getClass().getClassLoader().getResource(audioFileLoc);
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
        }
    }
}