import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;

public class Song {
    private String name;
    private String artist;
    private String album;
    private Image image;
    private URL url;

    public Song(String name, String artist, String album, String urllink) {
        this.name=name;
        this.artist=artist;
        this.album=album;
        try {
            url = new URL(urllink);
            image = ImageIO.read(url);
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    public void drawAlbumArt(Graphics g,int x, int y) {
        g.drawImage(image,x,y,40,40,null);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String toString() {
        return name + "\t" + artist + "\t" + album ;
    }
}