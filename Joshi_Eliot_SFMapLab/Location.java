import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;

public class Location {
    private String name;
    private String abbrev;
    private Image image;
    private int x;
    private int y;
    private Boolean green = false;

    public Location(String name, String b, int x, int y, String url) {
        this.name = name;
        this.abbrev = b;
        this.x = x;
        this.y = y;
        if (!url.equals("")) {
            try {
                URL urlImage = new URL(url);
                image = ImageIO.read(urlImage);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        
    }

    public boolean equals(Object o) {
        if (((Location)o).getAbbrev().equals(abbrev)) {
            return true;
        }
        return false;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String toString() {
        return name + " (" + abbrev + ")";
    }

    public int hashCode() {
        int value1 = (((int)abbrev.charAt(0)) - 97)*26*26;
        int value2 = (((int)abbrev.charAt(1)) - 97)*26;
        int value3 = (((int)abbrev.charAt(2)) -97);
        return value1 + value2 + value3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setGreen(Boolean a) {
        green = a;
    }

    public Boolean isGreen() {
        return green;
    }

    public Boolean hitBox(int xG, int yG) {
        int size = 8;
        if (xG > x-4 && xG < x-4+size && yG > y-4 && yG < y-4+size) {
            return true;
        }
        return false;
    }

    public void drawImage(Graphics g, int xLoc, int yLoc) {
        g.drawImage(image, xLoc, yLoc, 120,120,null);
    }

    public void setLoc(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
