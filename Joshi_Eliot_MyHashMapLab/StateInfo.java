import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class StateInfo {
    private String capital;
    private int population;
    private int size;
    private DLList<String> landmarkImages = new DLList<String>();

    public StateInfo(String capital, int population, int size) {
        this.capital=capital;
        this.population=population;
        this.size=size;
    }

    public DLList<String> getLandmarkImages() {
        return landmarkImages;
    }

    public void addLandmarkImages(String url) {
        landmarkImages.add(url);
    }

    public void drawAllImages(Graphics g, int xLoc, int yLoc) {
        int x=xLoc;
        int y=yLoc;
        for (int i=0; i<landmarkImages.size(); i++) {
            try {
                URL url = new URL(landmarkImages.get(i));
                Image image = ImageIO.read(url);
                g.drawImage(image, x, y, 80,80,null);
            } catch (IOException e){
                e.printStackTrace();
            }
            y+=85;
        }
    }

    public String toString() {
        String line;
        line = "Capital: "  + capital;
        line += "\nPopulation: " + population;
        line += "\nSize: " + size + " square miles";
        return line;
    }
}
