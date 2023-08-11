import java.awt.Graphics;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

public class MyImage implements Serializable{
    private String url;
    private String landmarkName;

    public MyImage(String url, String landmarkname) {
        this.url = url;
        this.landmarkName = landmarkname;
    }

    public void drawImage(Graphics g, int xLoc, int yLoc) {
        int x=xLoc;
        int y=yLoc;
        try {
            URL urls = new URL(url);
            BufferedImage image = ImageIO.read(urls);
            g.drawImage(image, x, y, 260,260,null);
        } catch (IOException e){
            e.printStackTrace();
        }
        y+=85;
    }

    public String toString() {
        return landmarkName + ": " + url;
    }

    public String getLandmark() {
        return landmarkName;
    }
}
