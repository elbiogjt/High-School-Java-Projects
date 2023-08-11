import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Table extends JPanel implements ActionListener, MouseListener {
	private DLList<Card> deck = new DLList<Card>();
	private DLList<Card> player = new DLList<Card>();
	private DLList<Card> straighCheck = new DLList<Card>();
	private JButton reset;
	private JButton draw;
	private Font font;
	private int pvalue;
	private int playtotal = 50;
	private String valueDerived = "";
	private boolean drawsed = false;
	
	public Table() {
		if (true) {
			deck.add(new Card(2, "2", "hearts"));
			deck.add(new Card(3, "3", "hearts"));
			deck.add(new Card(4, "4", "hearts"));
			deck.add(new Card(5, "5", "hearts"));
			deck.add(new Card(6, "6", "hearts"));
			deck.add(new Card(7, "7", "hearts"));
			deck.add(new Card(8, "8", "hearts"));
			deck.add(new Card(9, "9", "hearts"));
			deck.add(new Card(10, "10", "hearts"));
			deck.add(new Card(11, "J", "hearts"));
			deck.add(new Card(12, "Q", "hearts"));
			deck.add(new Card(13, "K", "hearts"));
			deck.add(new Card(14, "A", "hearts"));
			deck.add(new Card(2, "2", "diamonds"));
			deck.add(new Card(3, "3", "diamonds"));
			deck.add(new Card(4, "4", "diamonds"));
			deck.add(new Card(5, "5", "diamonds"));
			deck.add(new Card(6, "6", "diamonds"));
			deck.add(new Card(7, "7", "diamonds"));
			deck.add(new Card(8, "8", "diamonds"));
			deck.add(new Card(9, "9", "diamonds"));
			deck.add(new Card(10, "10", "diamonds"));
			deck.add(new Card(11, "J", "diamonds"));
			deck.add(new Card(12, "Q", "diamonds"));
			deck.add(new Card(13, "K", "diamonds"));
			deck.add(new Card(14, "A", "diamonds"));
			deck.add(new Card(2, "2", "clubs"));
			deck.add(new Card(3, "3", "clubs"));
			deck.add(new Card(4, "4", "clubs"));
			deck.add(new Card(5, "5", "clubs"));
			deck.add(new Card(6, "6", "clubs"));
			deck.add(new Card(7, "7", "clubs"));
			deck.add(new Card(8, "8", "clubs"));
			deck.add(new Card(9, "9", "clubs"));
			deck.add(new Card(10, "10", "clubs"));
			deck.add(new Card(11, "J", "clubs"));
			deck.add(new Card(12, "Q", "clubs"));
			deck.add(new Card(13, "K", "clubs"));
			deck.add(new Card(14, "A", "clubs"));
			deck.add(new Card(2, "2", "spades"));
			deck.add(new Card(3, "3", "spades"));
			deck.add(new Card(4, "4", "spades"));
			deck.add(new Card(5, "5", "spades"));
			deck.add(new Card(6, "6", "spades"));
			deck.add(new Card(7, "7", "spades"));
			deck.add(new Card(8, "8", "spades"));
			deck.add(new Card(9, "9", "spades"));
			deck.add(new Card(10, "10", "spades"));
			deck.add(new Card(11, "J", "spades"));
			deck.add(new Card(12, "Q", "spades"));
			deck.add(new Card(13, "K", "spades"));
			deck.add(new Card(14, "A", "spades"));
		}
		shuffle();
		font = new Font("Arial", Font.PLAIN, 50);
		
		player.add(deck.get(0));
		player.add(deck.get(1));
		player.add(deck.get(2));
		player.add(deck.get(3));
		player.add(deck.get(4));
		deck.remove(0);
		deck.remove(0);
		deck.remove(0);
		deck.remove(0);
		deck.remove(0);
		
		reset = new JButton("Next Round");
		reset.setBounds(0,20,150,30);
		add(reset);
		reset.addActionListener(this);

		draw = new JButton("Draw");
		draw.setBounds(0,20,150,30);
		add(draw);
		draw.addActionListener(this);

		addMouseListener(this);
	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}
	
	public int getPlayerTotal() {
		return pvalue;
	}
	
	public void paintComponent(Graphics g){
		g.setFont(font);
		g.setColor(Color.white);
		g.fillRect(0,0,800,600);
		Font fon  = new Font("Ariel", Font.PLAIN, 14);
		g.setFont(fon);
		g.setColor(Color.black);
		g.drawString("Royal Flush - 250pts",168,40);
		g.drawString("Straight Flush - 50pts",168,52);
		g.drawString("4 of a Kind - 25pts",168,64);
		g.drawString("Full House - 9pts",168,76);
		g.drawString("Flush - 6pts",168,88);
		g.drawString("Straight - 4pts",340,40);
		g.drawString("3 of a Kind - 3pts",340,52);
		g.drawString("2 Pairs - 2pts",340,64);
		g.drawString("Pairs of J or Higher - 1pts",340,76);
		g.drawString("Other - 0pts",340,88);
		
		g.setFont(font);
		//player deck
		int x = 60;
		int y = 375;
		for (int i=0; i<player.size(); i++) {
			player.get(i).drawMe(g,x,y);
			x+=80;
			y+=15;
		}
		g.setColor(Color.red);
		
		//player value 
		g.drawString("+ " + getPlayerTotal() + "pts", 25, 585);
		g.drawString(valueDerived,25,200);
		
		g.drawString("Points: " + playtotal, 425, 585);
		
		g.setFont(fon);
		g.drawString("Click on the card you want to hold/unhold,",425,300);
		g.drawString("clicking overlapping cards holds both",433,312);
	}

	public void shuffle(){
		//write code to shuffle your deck
		for (int i=0; i<deck.size(); i++) {
			int rand = (int)(Math.random()*deck.size());
			Card temp = deck.get(i);
			deck.set(i,deck.get(rand));
			deck.set(rand, temp);
		}
	}

	public void shuffleStraight() {
		if (straighCheck.size() == 0) {
			for (int i=0; i<player.size(); i++) {
				straighCheck.add(player.get(i));
			}
		}
		Card temp;
		for (int i = 0; i < player.size(); i++) {
            for (int j = 0; j < player.size(); j++) {
                if (straighCheck.get(i).getValue() < straighCheck.get(j).getValue()) {
                    temp = straighCheck.get(i);
                    straighCheck.set(i, straighCheck.get(j));
                    straighCheck.set(j, temp);
                }
            }
        }
		String suit = straighCheck.get(4).getSuit();
		if (straighCheck.get(4).getValue() == 14 && straighCheck.get(3).getValue() != 13 && straighCheck.get(3).getValue() != 14) {
			straighCheck.set(4,new Card(1,"A",suit));
		}
	}

	public boolean hold() {
		//royal flush check
		int count=0;
		String suit = "";
		//royal flush check
		for (int i=0; i<player.size(); i++) {
			if (player.get(i).getValue() >= 10 && count==0) {
				suit = player.get(i).getSuit();
				count++;
			} else if (player.get(i).getValue() >= 10 && player.get(i).getSuit() == suit) {
				count++;
			}
		}
		if (count >= 5) {
			pvalue+=250;
			valueDerived = ("Royal Flush");
			return true;
		}
		//straight flush check
		count = 0;
		shuffleStraight();
		int min = straighCheck.get(0).getValue()-1;
		String suitstrin = straighCheck.get(0).getSuit();
		int previntous = straighCheck.get(0).getValue();
		for (int i=0; i<straighCheck.size(); i++) {
			if (straighCheck.get(i).getSuit().equals(suitstrin)) {
				if (straighCheck.get(i).getValue() == previntous++) {
					count++;
				}
			}
		}
		if (count == 5) {
			pvalue+=50;
			valueDerived = ("Straight Flush");
			return true;
		}
		//4 of a kind
		count = 0;
		String stringof0,stringof1 = "";
		stringof0 = straighCheck.get(0).getName();
		for (int i=0; i<straighCheck.size(); i++) {
			if (straighCheck.get(i).getName().equals(stringof0)) {
				count++;
			} else if (count == 0) {
				count=0;
				stringof0 = straighCheck.get(i).getName();
			}
		}
		if (count == 4) {
			pvalue+=25;
			valueDerived = ("4 of a Kind");
			return true;
		}
		//full house
        int s1 = 0;
		stringof0 = straighCheck.get(0).getName();
        stringof1 = straighCheck.get(straighCheck.size()-1).getName();
        int s2 = 0;
        for (int i=0; i<straighCheck.size(); i++) {
            if (straighCheck.get(i).getName().equals(stringof0)) {
				s1++;
			}
            if (straighCheck.get(i).getName().equals(stringof1)) {
				s2++;
			}
        }
        if (s1 == 3 && s2 == 2) {
			pvalue+=9;
			valueDerived = ("Full house");
			return true;
		}
        if (s1 == 2 && s2 == 3) {
			pvalue+=9;
			valueDerived = ("Full house");
			return true;
		}
		//flush
		count = 0;
		stringof0 = player.get(0).getSuit();
		for (int i=0; i<player.size(); i++) {
			if (player.get(i).getSuit().equals(stringof0)) {
				count++;
			}
		}
		if (count == 5) {
			pvalue+=6;
			valueDerived = ("Flush");
			return true;
		}
		//straight
		count = 0;
		shuffleStraight();
		min = straighCheck.get(0).getValue();
		for (int i=0; i<straighCheck.size(); i++) {
			if (straighCheck.get(i).getValue() == min) {
				count++;
			} else {
				break;
			}
			min++;
		}
		if (count == 5) {
			pvalue+=4;
			valueDerived = ("Straight");
			return true;
		}
		//3 of a kind
		count = 0; 
		for (int i=0; i<straighCheck.size(); i++) {
			for (int j=i+1; j<straighCheck.size(); j++) {
				if (straighCheck.get(i).getValue() == straighCheck.get(j).getValue()) {
                    count++;
                }
			}
		}
		if (count == 3) {
			pvalue+=3;
			valueDerived = ("3 of a Kind");
			return true;
		}
		//2 pairs 
		//int s1,s2,s3
		count = 0;
		s1 = 0;
		s2 = 0;
		int s3 = 0;
		stringof0 = straighCheck.get(0).getName();
		stringof1 = straighCheck.get(2).getName();
		String stringof2 = straighCheck.get(4).getName();
		for (int i=0; i<straighCheck.size(); i++) {
			if (straighCheck.get(i).getName().equals(stringof0)) {
				s1++;
			} else if (straighCheck.get(i).getName().equals(stringof1)) {
				s2++;
			} else if (straighCheck.get(i).getName().equals(stringof2)) {
				s3++;
			}
		}
		if ((s1 == 2 && s2 == 2) || (s2 == 2 && s3 == 2) || (s1 == 2 && s3 == 2)) {
			pvalue+=2;
			valueDerived = ("2 Pairs");
			return true;
		}
		//pairs of J or higher
		suit = straighCheck.get(0).getSuit();
		if (straighCheck.get(0).getValue() == 1) {
            straighCheck.set(0,new Card(14,"A",suit));
        }
		shuffleStraight();
		count = 0;
		for (int i=0; i<straighCheck.size() - 1; i++) {
            for (int j = 0; j < straighCheck.size(); j++) {
				if (i != j) {
					if (straighCheck.get(i).getName() == straighCheck.get(j).getName() && straighCheck.get(i).getValue() >= 11) {
						count++;
					}
				}
                
            }
        }
		if (count >= 1) {
			pvalue+=1;
			valueDerived = ("Pairs of J or higher");
			return true;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == reset) {
			playtotal--;
			playtotal+=pvalue;
			pvalue = 0;
			int size = player.size();
			for (int i=0; i<size; i++) {
				deck.add(player.get(0));
				player.remove(0);
				straighCheck.remove(0);
			}
			shuffle();
			player.add(deck.get(0));
			deck.remove(0);
			player.add(deck.get(0));
			deck.remove(0);
			player.add(deck.get(0));
			deck.remove(0);
			player.add(deck.get(0));
			deck.remove(0);
			player.add(deck.get(0));
			deck.remove(0);
			valueDerived = "";
			drawsed=false;
			repaint();
		} else if (e.getSource() == draw && !drawsed) {
			for (int i=0; i<player.size(); i++) {
				if (!player.get(i).isSaved()) {
					deck.add(player.get(i));
					player.set(i,deck.get(0));
					deck.remove(0);
				} 
			}
			hold();
			for (int i=0; i<player.size(); i++) {
				player.get(i).setSaved(false);
			}
			drawsed=true;
			repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (!drawsed) {
			for (int i=player.size()-1; i>= 0; i--) {
				player.get(i).collision(e.getX(), e.getY());
			}
		}
        repaint();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
