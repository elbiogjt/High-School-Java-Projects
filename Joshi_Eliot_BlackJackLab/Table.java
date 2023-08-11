import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Table extends JPanel implements ActionListener {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> player = new ArrayList<Card>();
	private ArrayList<Card> dealer = new ArrayList<Card>();
	private JButton hit;
	private JButton stay;
	private JButton reset;
	private JButton advise;
	private Font font;
	private Font smol;
	private int hintOn;
	private int pvalue;
	private int dvalue;
	private int goodAd;
	private int playtotal = 0;
	private int dealtotal = 0;
	private int whoVict = 0;
	private boolean stayUsed = false;
	
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
			deck.add(new Card(10, "J", "hearts"));
			deck.add(new Card(10, "Q", "hearts"));
			deck.add(new Card(10, "K", "hearts"));
			deck.add(new Card(11, "A", "hearts"));
			deck.add(new Card(2, "2", "diamonds"));
			deck.add(new Card(3, "3", "diamonds"));
			deck.add(new Card(4, "4", "diamonds"));
			deck.add(new Card(5, "5", "diamonds"));
			deck.add(new Card(6, "6", "diamonds"));
			deck.add(new Card(7, "7", "diamonds"));
			deck.add(new Card(8, "8", "diamonds"));
			deck.add(new Card(9, "9", "diamonds"));
			deck.add(new Card(10, "10", "diamonds"));
			deck.add(new Card(10, "J", "diamonds"));
			deck.add(new Card(10, "Q", "diamonds"));
			deck.add(new Card(10, "K", "diamonds"));
			deck.add(new Card(11, "A", "diamonds"));
			deck.add(new Card(2, "2", "clubs"));
			deck.add(new Card(3, "3", "clubs"));
			deck.add(new Card(4, "4", "clubs"));
			deck.add(new Card(5, "5", "clubs"));
			deck.add(new Card(6, "6", "clubs"));
			deck.add(new Card(7, "7", "clubs"));
			deck.add(new Card(8, "8", "clubs"));
			deck.add(new Card(9, "9", "clubs"));
			deck.add(new Card(10, "10", "clubs"));
			deck.add(new Card(10, "J", "clubs"));
			deck.add(new Card(10, "Q", "clubs"));
			deck.add(new Card(10, "K", "clubs"));
			deck.add(new Card(11, "A", "clubs"));
			deck.add(new Card(2, "2", "spades"));
			deck.add(new Card(3, "3", "spades"));
			deck.add(new Card(4, "4", "spades"));
			deck.add(new Card(5, "5", "spades"));
			deck.add(new Card(6, "6", "spades"));
			deck.add(new Card(7, "7", "spades"));
			deck.add(new Card(8, "8", "spades"));
			deck.add(new Card(9, "9", "spades"));
			deck.add(new Card(10, "10", "spades"));
			deck.add(new Card(10, "J", "spades"));
			deck.add(new Card(10, "Q", "spades"));
			deck.add(new Card(10, "K", "spades"));
			deck.add(new Card(11, "A", "spades"));
		}
		
		
		shuffle();
		
		font = new Font("Arial", Font.PLAIN, 50);
		smol = new Font("Arial", Font.PLAIN, 25);
		
		player.add(deck.get(0));
		player.add(deck.get(1));
		deck.remove(0);
		deck.remove(0);
		
		dealer.add(deck.get(0));
		dealer.add(deck.get(1));
		deck.remove(0);
		deck.remove(0);
		
		hit = new JButton("Hit");
		hit.setBounds(0,20,150,30);
		add(hit);
		hit.addActionListener(this);
		
		stay = new JButton("Stay");
		stay.setBounds(0,20,150,30);
		add(stay);
		stay.addActionListener(this);
		
		reset = new JButton("Next Round");
		reset.setBounds(0,20,150,30);
		add(reset);
		reset.addActionListener(this);
		
		advise = new JButton("Hint");
		advise.setBounds(0,20,150,30);
		add(advise);
		advise.addActionListener(this);
		
		//create hit button, action performed to add card from deck to player
	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}
	
	public int getPlayerTotal() {
		pvalue = 0;
		for (int i=0;i<player.size();i++) {
			pvalue = pvalue + player.get(i).getValue();
		}
		return pvalue;
	}

	public int getDealerTotal() {
		dvalue = 0;
		for (int i=0;i<dealer.size();i++) {
			dvalue = dvalue + dealer.get(i).getValue();
		}
		return dvalue;
	}
	
	public void paintComponent(Graphics g){
		g.setFont(font);
		g.setColor(Color.white);
		g.fillRect(0,0,800,600);
		
		//player deck
		int x = 60;
		int y = 375;
		for (int i=0; i<player.size(); i++) {
			player.get(i).drawMe(g,x,y);
			x+=80;
			y+=15;
		}
		
		//dealer deck
		x=60;
		y=125;
		dealer.get(0).drawMe(g,x,y);
		x+=80;
		y+=15;
		g.fillRect(x,y,120,150);
		x=60;
		y=125;
		if (stayUsed == true) {
			for (int i=0;i<dealer.size();i++) {
				dealer.get(i).drawMe(g,x,y);
				x+=80;
				y+=15;
			}
		}
		g.setColor(Color.red);
		
		//player value 
		g.drawString("Value: " + getPlayerTotal(), 25, 585);
		
		//dealer value
		if (stayUsed == false) {
			g.drawString("Dealer Value: ?", 25,85);
		} else {
			g.drawString("Dealer Value: " + dvalue, 25, 85);
		}
		
		countWins();
		if (stayUsed == true && whoVict == 0) {
			g.drawString("You win", 425, 400);
		} else if (stayUsed == true && whoVict == 1) {
			g.drawString("You lose", 425, 400);
		} else if (stayUsed == true && whoVict == 2) {
			g.drawString("You tie", 425, 400);
		}
		g.drawString("Points: " + playtotal, 425, 585);
		g.drawString("Dealer Points: " + dealtotal, 285, 135);
		
		if (stayUsed == true) {
			goodAd=0;
		}
		
		if (hintOn == 1) {
			g.setFont(smol);
			if (goodAd == 1) {
				g.drawString("It could be smart to hit", 425, 400);
			} else if (goodAd == 2) {
				g.drawString("Better to play safe", 425, 400);
			} 
		}
		g.setFont(font);
	}
	
	public void countWins() {
		//player wins
		if (getPlayerTotal() > 21 && stayUsed == true) {
			dealtotal++;
			whoVict = 1;
		} else if (getDealerTotal() > 21 && stayUsed == true) {
			playtotal++;
			whoVict = 0;
		} else if ((getPlayerTotal() > getDealerTotal()) && (stayUsed == true)) {
			playtotal++;
			whoVict = 0;
		} else if ((getPlayerTotal() < getDealerTotal()) && (stayUsed == true)) {
			dealtotal++;
			whoVict = 1;
		} else if ((getPlayerTotal() == getDealerTotal()) && (stayUsed == true)) {
			whoVict = 2;
		}
	}
	
	public void hold() {
		for (int i=0; i<dealer.size(); i++) {
			if (getDealerTotal() <= 16) {
				dealer.add(deck.get(0));
				deck.remove(0);
			}
		}
		stayUsed = true;
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == hit) {
			if (stayUsed == false) {
				hintOn = 0;
				if (getPlayerTotal() < 21) {
					player.add(deck.get(0));
					deck.remove(0);
					if (getPlayerTotal() > 20) {
						hold();
					}
					repaint();
				}
			}
		} else if (e.getSource() == stay) {
			if (stayUsed == false) {
				hold();
				repaint();
			}
		} else if (e.getSource() == reset) {
			stayUsed = false;
			int size = player.size();
			for (int i=0; i<size; i++) {
				deck.add(player.get(0));
				player.remove(0);
			}
			size = dealer.size();
			for (int i=0; i<size; i++) {
				deck.add(dealer.get(0));
				dealer.remove(0);
			}
			shuffle();
			player.add(deck.get(0));
			deck.remove(0);
			player.add(deck.get(0));
			deck.remove(0);
			dealer.add(deck.get(0));
			deck.remove(0);
			dealer.add(deck.get(0));
			deck.remove(0);
			if (getPlayerTotal() >= 21) {
				hold();
			} 
			goodAd = 0;
			repaint();
		} else if (e.getSource() == advise) {
			hintOn = 1;
			if (getPlayerTotal() <=15) {
				goodAd=1;
			} else if (getPlayerTotal() > 15) {
				goodAd=2;
			} else {
				goodAd=0;
			}
			if(stayUsed == false) {
				repaint();
			}
		}
	}
}
