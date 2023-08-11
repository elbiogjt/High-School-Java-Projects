import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Screen extends JPanel implements MouseListener, ActionListener{
	private Square[][] grid;
    private Square redPicked;
    private Square blackPicked;
    private Square bluePicked;
    private Square greenPicked;
    private Square whitePicked;
    private Square yellowPicked;
    private Square pinkPicked;
    private Square brownPicked;
    private Square orangePicked;
    private JButton clear;
    private JButton undo;
    private int redG;
    private int greenG;
    private int blueG;  
    private int numUndo;
    private int undoRedG;
    private int undoGreenG;
    private int undoBlueG;
    private int undoX;
    private int undoY;
    private int undoRedG2;
    private int undoGreenG2;
    private int undoBlueG2;
    private int undoX2;
    private int undoY2;
    private int undoRedG3;
    private int undoGreenG3;
    private int undoBlueG3;
    private int undoX3;
    private int undoY3;

    public Screen() {
        addMouseListener(this);

        grid = new Square[16][16];
        redG = 255;
        greenG = 255;
        blueG = 255;
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[r].length; c++) {
                grid[r][c] = new Square(redG,blueG,greenG);
            }
        }

        redPicked = new Square(255,0,0);
        blackPicked = new Square(0,0,0);
        bluePicked = new Square(0,0,255);
        greenPicked = new Square(0,255, 0);
        whitePicked = new Square(255,255,255);
        yellowPicked = new Square(248,255,80);
        pinkPicked = new Square(238,168,182);
        brownPicked = new Square(84,62,50);
        orangePicked = new Square(224,100,42);

        clear = new JButton("Clear");
		clear.setBounds(620,20,90,60);
		add(clear);
		clear.addActionListener(this);

        undo = new JButton("Undo");
        undo.setBounds(620,80,90,30);
        add(undo);
        undo.addActionListener(this);

        setLayout(null);
    }

	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.setColor(new Color(110, 117, 121));
        g.fillRect(0,0,800,600);

        int x = 20;
        int y = 20;
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[r].length; c++) {
                grid[r][c].drawMe(g,x,y);
                x+=30;
            }
            y+=30; 
            x=20;
        }

        x = 530;
        redPicked.drawMe(g, x, 20);
        x+=30;
        blackPicked.drawMe(g, x, 20);
        x+=30;
        bluePicked.drawMe(g,x,20);
        x=530;
        greenPicked.drawMe(g,x,50);
        x+=30;
        whitePicked.drawMe(g,x,50);
        x+=30;
        yellowPicked.drawMe(g,x,50);
        x=530;
        pinkPicked.drawMe(g,x,80);
        x+=30;
        brownPicked.drawMe(g,x,80);
        x+=30;
        orangePicked.drawMe(g,x,80);
	}

    //apply mouse listener
    //when user clicks on the color to the right then they becmoe the colour
    //when click on grid, change square to color selected
    public void mousePressed(MouseEvent e) {
        if (redPicked.inBox(e.getX(), e.getY())) {
            redG = 255;
            greenG = 0;
            blueG = 0;
        }

        if (blackPicked.inBox(e.getX(), e.getY())) {
            redG = 0;
            greenG = 0;
            blueG = 0;
        }

        if (bluePicked.inBox(e.getX(), e.getY())) {
            redG = 0;
            greenG = 0;
            blueG = 255;
        }

        if (greenPicked.inBox(e.getX(), e.getY())) {
            redG = 0;
            greenG = 255;
            blueG = 0;
        }

        if (whitePicked.inBox(e.getX(), e.getY())) {
            redG = 255;
            greenG = 255;
            blueG = 255;
        }

        if (yellowPicked.inBox(e.getX(), e.getY())) {
            redG = 248; 
            greenG = 255;
            blueG = 80;
        }

        if (pinkPicked.inBox(e.getX(), e.getY())) {
            redG = 238;
            greenG = 168;
            blueG = 182;
        }

        if (brownPicked.inBox(e.getX(), e.getY())) {
            redG = 84;
            greenG = 62;
            blueG = 50;
        }

        if (orangePicked.inBox(e.getX(), e.getY())) {
            redG = 224;
            greenG = 100;
            blueG = 42;
        }
        
        for (int r=0; r<grid.length; r++) {
            for (int c=0; c<grid[r].length; c++) {
                if (grid[r][c].inBox(e.getX(), e.getY())) {
                    if (numUndo == 0) {
                        undoRedG = grid[r][c].returnRed();
                        undoGreenG = grid[r][c].returnGreen();
                        undoBlueG = grid[r][c].returnBlue();
                        undoX = r;
                        undoY = c;
                        numUndo++;
                    } else if (numUndo == 1) {
                        undoRedG2 = undoRedG;
                        undoGreenG2 = undoGreenG;
                        undoBlueG2 = undoBlueG;
                        undoX2 = undoX;
                        undoY2 = undoY;

                        undoRedG = grid[r][c].returnRed();
                        undoGreenG = grid[r][c].returnGreen();
                        undoBlueG = grid[r][c].returnBlue();
                        undoX = r;
                        undoY = c;
                        numUndo++; 
                    } else if (numUndo == 2) {
                        undoRedG3 = undoRedG2;
                        undoGreenG3 = undoGreenG2;
                        undoBlueG3 = undoBlueG2;
                        undoX3 = undoX2;
                        undoY3 = undoY2;

                        undoRedG2 = undoRedG;
                        undoGreenG2 = undoGreenG;
                        undoBlueG2 = undoBlueG;
                        undoX2 = undoX;
                        undoY2 = undoY;

                        undoRedG = grid[r][c].returnRed();
                        undoGreenG = grid[r][c].returnGreen();
                        undoBlueG = grid[r][c].returnBlue();
                        undoX = r;
                        undoY = c;
                        numUndo++; 
                    } else if (numUndo >= 3) {
                        undoRedG3 = undoRedG2;
                        undoGreenG3 = undoGreenG2;
                        undoBlueG3 = undoBlueG2;
                        undoX3 = undoX2;
                        undoY3 = undoY2;

                        undoRedG2 = undoRedG;
                        undoGreenG2 = undoGreenG;
                        undoBlueG2 = undoBlueG;
                        undoX2 = undoX;
                        undoY2 = undoY;

                        undoRedG = grid[r][c].returnRed();
                        undoGreenG = grid[r][c].returnGreen();
                        undoBlueG = grid[r][c].returnBlue();
                        undoX = r;
                        undoY = c;                                                
                    }
                    
                    grid[r][c] = new Square(redG, greenG, blueG); 
                }
            }
        }

        repaint();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            for (int r=0; r<grid.length; r++) {
                for (int c=0; c<grid[r].length; c++) {
                    grid[r][c] = new Square(255,255,255);
                }
            }
            repaint();
        } else if (e.getSource() == undo) {
            grid[undoX][undoY] = new Square(undoRedG, undoGreenG, undoBlueG);

            if (numUndo > 0) {
                undoRedG = undoRedG2;
                undoGreenG = undoGreenG2;
                undoBlueG = undoBlueG2;
                undoX = undoX2;
                undoY = undoY2;

                undoRedG2 = undoRedG3;
                undoGreenG2 = undoGreenG3;
                undoBlueG2 = undoBlueG3;
                undoX2 = undoX3;
                undoY2 = undoY3;

                numUndo--;
            }
            
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}