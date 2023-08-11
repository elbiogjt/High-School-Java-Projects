import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {
   private int value;
   private int xcoord;
   private int ycoord; 
   private int width = 120;
   private int height = 150;
   private String name;
   private String suit;
   private BufferedImage suitImage; 
   private boolean isSaved = false;

   public Card(int value, String name, String suit){
		this.value = value;
		this.name = name;
		this.suit = suit;
		if( suit.equals("hearts") ){
			try{
				suitImage = ImageIO.read(new File("hearts.png"));
			} catch (IOException e) {}
		} else if(suit.equals("clubs")) {
			try{
				suitImage = ImageIO.read(new File("clubs.png"));
			} catch (IOException e) {}
		} else if(suit.equals("diamonds")) {
			try{
				suitImage = ImageIO.read(new File("diamonds.png"));
			} catch (IOException e) {}
		}  else if(suit.equals("spades")) {
			try{
				suitImage = ImageIO.read(new File("spades.png"));
			} catch (IOException e) {}
		}
	}
	
	public int getValue(){
        return this.value;
    }

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean a) {
		isSaved = a;
	}

	public String getSuit() {
		return this.suit;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return name + ": " + value;
	}

	public void drawMe(Graphics g, int x, int y){
		xcoord = x;
		ycoord = y;
		if (isSaved()) {
			g.setColor(Color.blue);
			g.fillRect(x-1,y-1,124,154);
		}
		g.setColor(Color.white);
		g.fillRect(x,y,120,150);
		g.setColor(Color.black);
		g.drawRect(x,y,120,150);
		//draw suit
		g.drawImage(suitImage, x+2, y, null);
		//Set Font to use with drawString   
		Font font = new Font("Arial", Font.PLAIN, 50);
		g.setFont(font);
		if( this.suit.equals("hearts") ){
			g.setColor(Color.red);
		}
		g.drawString(this.name+"", x+30, y + 100);
    }

    public boolean collision(int x, int y) {
		if (x <= xcoord + width && x>= xcoord) {
			if (y >= ycoord && y<= ycoord + height) {
				setSaved(!isSaved);
				return !isSaved;
			}
		}
		return isSaved;
    }
   
}
