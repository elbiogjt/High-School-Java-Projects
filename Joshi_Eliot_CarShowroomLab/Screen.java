import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Screen extends JPanel implements ActionListener, MouseListener { 
	private ColorBox redColorBox;
	private ColorBox blueColorBox;
	private ColorBox greenColorBox;
	private ColorBox blackColorBox;
	private ColorBox orangeColorBox;
	private ColorBox pinkColorBox;

	private JButton sscButton;
	private JButton ccButton;
	private JButton sButton;
	private JButton scButton;
	private JButton suvButton;
	private JButton tButton;
	private JButton testDrive;

    private SmallSportsCar ssc;
    private CompactCar cc;
	private Sedan s;
	private SportsCar sc;
	private SUV suv;
	private Truck t;

	private Color col;

	private int carNum;
	private int driveTrue;
	private int x;
	private int y;
	private int redG;
	private int greenG;
	private int blueG;
    private ArrayList<Automobile> carList = new ArrayList<Automobile>();

	public Screen(){
		addMouseListener(this);
		
		col = new Color(255,0,0);
		redColorBox = new ColorBox(255,0,0);
		blueColorBox = new ColorBox(0,0,255);
		greenColorBox = new ColorBox(55,124,69);
		blackColorBox = new ColorBox(0,0,0);
		orangeColorBox = new ColorBox(226,123,56);
		pinkColorBox = new ColorBox(225,108,169);
		
		sscButton = new JButton("Small Sports Car");
		add(sscButton);
		sscButton.addActionListener(this);

		ccButton = new JButton("Compact Car");
		add(ccButton);
		ccButton.addActionListener(this);

		sButton = new JButton("Sedan");
		add(sButton);
		sButton.addActionListener(this);

		scButton = new JButton("Sports Car");
		add(scButton);
		scButton.addActionListener(this);

		suvButton = new JButton("SUV");
		add(suvButton);
		suvButton.addActionListener(this);

		tButton = new JButton("Truck");
		add(tButton);
		tButton.addActionListener(this);

		testDrive = new JButton("Test Drive");
		add(testDrive);
		testDrive.addActionListener(this);

		x = 150;
		y = 250;

		ssc = new SmallSportsCar(col, x,y);
        cc = new CompactCar(col, x, y);
		s = new Sedan(col,x,y);
		sc = new SportsCar(col, x,y);
		suv = new SUV(col, x,y);
		t = new Truck(col,x,y);
        carList.add(ssc);
        carList.add(cc);
		carList.add(s);
		carList.add(sc);
		carList.add(suv);
		carList.add(t);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		x = carList.get(carNum).getX();

		col = new Color(redG,greenG, blueG);
		
		//draw background
		g.setColor(new Color(120,158,115));
		g.fillRect(0,0,810,610);
		g.setColor(Color.BLACK);
		g.fillRect(0,200,800,10);
		g.fillRect(0,400,800,10);
		g.setColor(new Color(204,204,204));
		g.fillRect(0,210,800,190);
		g.setColor(new Color(241,240,86));
		int xline = -5;
		for (int i=0; i<15; i++) {
			xline+=65;
			g.fillRect(xline,290,25,10);
		}
		g.setColor(Color.black);
		g.drawString("Pick Colour: ", 27,490);

		redColorBox.drawMe(g,27,500);
		blueColorBox.drawMe(g,57,500);
		greenColorBox.drawMe(g,87,500);
		blackColorBox.drawMe(g,117,500);
		orangeColorBox.drawMe(g,147,500);
		pinkColorBox.drawMe(g,177,500);
		
		//draw car
		g.setColor(col);
		carList.get(carNum).drawMe(g);

		if (driveTrue == 1) {
			animate(g);
		}
	}

	public void animate(Graphics g) {
		carList.get(carNum).animate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sscButton) {
			carNum = 0;
		} else if (e.getSource() == ccButton) {
			carNum = 1;
		} else if (e.getSource() == sButton) {
			carNum = 2;
		} else if (e.getSource() == scButton) {
			carNum = 3;
		} else if (e.getSource() == suvButton) {
			carNum = 4;
		} else if (e.getSource() == tButton) {
			carNum = 5;
		} else if (e.getSource() == testDrive) {
			driveTrue++;
			if (driveTrue > 1) {
				driveTrue = 0;
			}
		}
		carList.get(carNum).changeX(x);
		repaint();
	} 
	
	@Override
	public void mousePressed(MouseEvent e) {
        if (redColorBox.inBox(e.getX(), e.getY())) {
            redG = 255;
            greenG = 0;
            blueG = 0;
        } else if (blueColorBox.inBox(e.getX(), e.getY())) {
			redG = 0;
			greenG = 0;
			blueG = 255;
		} else if (greenColorBox.inBox(e.getX(), e.getY())) {
			redG = 55;
			greenG = 124;
			blueG = 69;
		} else if (blackColorBox.inBox(e.getX(), e.getY())) {
			redG = 0;
			greenG = 0;
			blueG = 0;
		} else if (orangeColorBox.inBox(e.getX(), e.getY())) {
			redG = 226;
			greenG = 123;
			blueG = 56;
		} else if (pinkColorBox.inBox(e.getX(), e.getY())) {
			redG = 225;
			greenG = 108;
			blueG = 169;
		}

		col = new Color(redG, greenG, blueG);

		carList.get(carNum).changeBodyColor(col);

		repaint();
    }

	public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}