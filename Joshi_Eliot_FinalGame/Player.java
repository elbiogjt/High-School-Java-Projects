import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player
{
    private BufferedImage shipRight;
    private BufferedImage shipLeft;
    private BufferedImage shipUp;
    private BufferedImage shipDown;
	private int x;
	private int y;
    private int tilex;
    private int tiley;
	private int width;
	private int height;
    private int mostRecent = 1; // 1 right, 2 left, 3 up, 4 down 
    private int shipRecent = 1;
    private int shipx;//ship x and ship y, saves location from where it was last left
    private int shipy;
    private int shipWidth;
    private int shipHeight;
	private boolean inShip;
	
	public Player(int x, int y)
	{	
		this.x = x;
		this.y = y;
		
		this.width=40;	 //total width of the player
		this.height=80;  //total height of the player
        shipWidth = 150;
        shipHeight = 150;

        inShip = false;
        tilex = 0;
        tiley = 0;
	}
	

	public void drawMe(Graphics g) {
        try{
            shipRight = ImageIO.read(new File("boatRight.png"));
        } catch (IOException e) {}
        try{
            shipLeft = ImageIO.read(new File("boatLeft.png"));
        } catch (IOException e) {}
        try{
            shipUp = ImageIO.read(new File("boatUp.png"));
        } catch (IOException e) {}
        try{
            shipDown = ImageIO.read(new File("boatDown.png"));
        } catch (IOException e) {}

        if (x + width >= shipx && x <= shipx + shipWidth && y + height >= shipy && y <= shipy + shipHeight) {
            if (inShip == false) {
                IsInShip(true);
            }
        }

        if (inShip == false) {
            g.setColor(Color.BLUE);
            g.fillOval(x,y,width,width);
            g.setColor(Color.WHITE);
            g.fillRect(x+15,y,10,height);

            if (shipRecent == 1) {
                g.drawImage(shipRight,shipx,shipy, null);
            } else if (shipRecent == 2) {
                g.drawImage(shipLeft,shipx,shipy,null);
            } else if (shipRecent == 3) {
                g.drawImage(shipUp,shipx,shipy,null);
            } else {
                g.drawImage(shipDown,shipx,shipy,null);
            }
            
        } else if (inShip == true) {
            //ship design in relation to x and y
            
            if (mostRecent == 1) {
                g.drawImage(shipRight, x, y, null);
            } else if (mostRecent == 2) {
                g.drawImage(shipLeft, x, y, null);
            } else if (mostRecent == 3) {
                g.drawImage(shipUp, x, y, null);
            } else if (mostRecent == 4) {
                g.drawImage(shipDown, x, y, null);
            }
        }
	}
	
	public void changeX(int xmin) {
        this.x+=xmin;
        if (xmin > 0) {
            mostRecent = 1;
        } else {
            mostRecent = 2;
        }
    }

    public void changeShipXY(int xmin, int ymin) {
        this.shipx+=xmin;
        this.shipy+=ymin;
    }

    public void StartingShip(int gX, int gY) {
        shipx = gX;
        shipy = gY;
    }

    public void IsInShip(boolean a) {
        shipx = x;
        shipy = y;
        shipRecent = mostRecent;
        inShip = a;
        if (true) {
            if (mostRecent == 1) {
                if (inShip) {
                    x+=0;
                } else {
                    x+=160;//origin
                }
            } else if (mostRecent == 2) {
                if (inShip) {
                    x-=45;//origin
                } else {
                    x-=160;
                }
            } else if (mostRecent == 3) {
                if (inShip) {
                    y-=45;//origin
                } else {
                    y-=160;
                }
            } else if (mostRecent == 4) {
                if (inShip) {
                    y+=45;
                } else {
                    y+=160; // origin
                }
            }
        } 
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getInShip() {
        return inShip;
    }

    public void changeY(int ymin) {
        this.y+=ymin;
        if (ymin < 0) {
            mostRecent = 3;
        } else {
            mostRecent = 4;
        }
    }

    public int shipXWidth() {
        //if (ship)
        return -0;
    }
}