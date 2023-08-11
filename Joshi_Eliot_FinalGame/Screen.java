import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Screen extends JPanel implements KeyListener{
    private Player p1;
    private Tree t1;
    private Tree t2;
    private Tree t3;
    private Font endFont;
    private Font regFont;
    private String textSpoke;
    private int tilex;
    private int tiley;
    private int tilex2;
    private int tiley2;
    private int tilex3;
    private int tiley3;
    private int widthIsland1;
    private int heightIsland1;
    private int widthIsland2;
    private int heightIsland2;
    private int widthIsland3;
    private int heightIsland3;
    private int roadXStart;
    private int roadX2Start;
    private int roadX3Start;
    private int randomTX1;
    private int randomTX2;
    private int randomTX3;
    private int randomTY1;
    private int randomTY2;
    private int randomTY3;
    private int islandNum;
    private int dialogueI;
    private int dialogueI2;
    private int dialogueI3;
    private int dialogueI4;
    private int foodEaten;
    private ArrayList<NPC> npcs;
    private ArrayList<String> quest1;
    private ArrayList<String> quest2;
    private ArrayList<String> quest3;
    private ArrayList<String> quest4;
    private boolean moveRightPos;
    private boolean moveLeftPos;
    private boolean moveUpPos;
    private boolean moveDownPos;
    private boolean gameWon;
    private boolean talkedto1;
    private boolean talkedto2;
    private boolean talkedto3;
    private boolean talkedto4;
    private boolean food2ret;
    private boolean lineUsed3;
    private boolean lineUsed2;
    private boolean textTalked;
    private boolean oneKeyPressed;
    private boolean tildaUsed;
    
    public Screen(){
        
        //sets up the instance variables		
        p1 = new Player(120,120);
        t1 = new Tree();
        t2 = new Tree();
        t3 = new Tree();
        endFont = new Font("serif", Font.BOLD, 60);
        regFont = new Font("serif", Font.BOLD, 20);
        gameWon = false;
        foodEaten = 0;
        food2ret = false;
        talkedto1 = false;
        talkedto2 = false;
        talkedto3 = false;
        talkedto4 = false;
        lineUsed3 = false;
        lineUsed2 = false;
        textTalked = false;
        oneKeyPressed = false;
        tildaUsed = false;
        textSpoke = "";

        islandNum = 1;
        tilex = 0;
        tiley = 0;
        dialogueI = 0;
        dialogueI2 = 0;
        dialogueI3 = 0;
        dialogueI4 = 0;
        widthIsland1 = (int)(Math.random()*900+200);
        heightIsland1 = (int)(Math.random()*900+200);
        tilex2 = (int)(Math.random()*10+widthIsland1+600);
        tiley2 = (int)(Math.random()*600);
        widthIsland2 = (int)(Math.random()*900+200);
        heightIsland2 = (int)(Math.random()*900+200);
        tilex3 = (int)(Math.random()*500+100);
        int bigHeight;
        if (heightIsland1 >= heightIsland2) {
            bigHeight = heightIsland1;
        } else {
            bigHeight = heightIsland2;
        }
        tiley3 = (int)(Math.random()*1000+bigHeight+20);
        widthIsland3 = (int)(Math.random()*1300+200);
        heightIsland3 = (int)(Math.random()*1300+200);
        roadXStart = (int)(Math.random()*widthIsland1-70);
        roadX2Start = (int)(Math.random()*widthIsland2+tilex2-70);
        roadX3Start = (int)(Math.random()*widthIsland3+tilex3-70);
        randomTX1 = (int)(Math.random()*widthIsland1-100);
        randomTX3 = (int)(Math.random()*widthIsland3+tilex3-100);
        randomTX2 = (int)(Math.random()*widthIsland2+tilex2-100);
        randomTY1 = (int)(Math.random()*heightIsland1+tiley-100);
        randomTY2 = (int)(Math.random()*heightIsland2+tiley2-100);
        randomTY3 = (int)(Math.random()*heightIsland3+tiley3-100);

        int randBoatY = (int)(Math.random()*(heightIsland1-60)+20);
        p1.StartingShip(widthIsland1,randBoatY);

        quest1 = new ArrayList<String>();
        quest1.add("");
        quest1.add("Welcome to the island Paleta! (press space to talk when close to me)"); //(redboy)
        quest1.add("HELP! A Candy Bar took my dinner!");
        quest1.add("He went to the island south of here, press arrow keys to move");
        quest1.add("YOU ATE MY FOOD, I MUST FIGHT YOU NOW PALETA"); //if go back to first island
        quest1.add("With food I can be a god, Fight me and die"); //if food is returned, harder fight; if food isnt returned, easier fight

        quest2 = new ArrayList<String>();
        quest2.add("I'll give you the food back if you steal my food back"); // when talk to gastricnamious (blue boy)
        quest2.add("the pink lolipop took it from the Island North East of here"); //second talk to gastricnamious)
        quest2.add("eat food by 'e', return food by 'r'"); //when gastricnamious gives back

        quest3 = new ArrayList<String>();
        quest3.add("I'll give you the food if you listen to my grandma's story"); // when talk to pink boy
        quest3.add("She's the gray lolipop"); //second pink boy (write a long stupid story for grandma)
        quest3.add("here's your food back");

        quest4 = new ArrayList<String>();
        if (true) {
            quest4.add("I have a friend steven");
            quest4.add("Who has a girlfriend Stoven");
            quest4.add("Who has a dog who this story's about");
            quest4.add("So the dog was going on a walk with Stoven");
            quest4.add("and theres this random man that walks up to them");
            quest4.add("he talks to Stoven for a bit");
            quest4.add("then threatens to eat the dog");
            quest4.add("the dog, being scared, barks");
            quest4.add("the man gets startled and starts walking away");
            quest4.add("OH by the way the dog was leashless");
            quest4.add("so the man starts walking away but the dog wasn't done");
            quest4.add("so the dog starts chasing the man, runnin and barkin and that");
            quest4.add("the man gets scared too");
            quest4.add("he runs for about 6 blocks and half a mile with the dog");
            quest4.add("eventually gives up gets bitten by the dog");
            quest4.add("the dog keeps eating him");
            quest4.add("OH by the way we're all people are lolipops- except dogs");
            quest4.add("dogs are dogs");
            quest4.add("so the man must have been tasty for the dog");
            quest4.add("anyway the man was getting eaten and licked for about hours");
            quest4.add("then Stoven finally catches up");
            quest4.add("that women is a gem but she is slow");
            quest4.add("and then she gets mad at the dog");
            quest4.add("you're probably asking");
            quest4.add("'how can she get mad at the dog, it did nothing wrong");
            quest4.add("but thats where we learn, the dog misheard it all");
            quest4.add("The man didn't threaten eat it");
            quest4.add("the man threatened to eat duck");
            quest4.add("that dumb dog");
            quest4.add("anyway the moral of the story is to listen correctly");
            quest4.add("oh actually theres another reason Stoven was mad at the dog");
            quest4.add("the man in that story");
            quest4.add("was actually Steven");
            quest4.add("If you remember Steven was the boyfriend of Stoven");
            quest4.add("Steven is the one I'm friends with!");
            quest4.add("Steven has severe injuries that can never recover from that instant");
            quest4.add("anyway youre free to go now, unless you want to hear another story");
        }

        Color npcc = new Color(255,0,0);
        Color npcc2 = new Color(0,255,0);
        Color npcc3 = new Color(225,108,169);
        Color npcc4 = new Color(122,111,126);

        npcs = new ArrayList<NPC>();
        npcs.add(new GoodNPC("Eugene", 80,80, npcc));
        int locnpc1x = (int)(Math.random()*(widthIsland3-40)+20+tilex3);
        int locnpc1y = (int)(Math.random()*(heightIsland3-40)+20+tiley3);
        npcs.add(new GoodNPC("Gastricnamious", locnpc1x,locnpc1y,npcc2));
        int locnpc3x = (int)(Math.random()*(widthIsland2-40)+20+tilex2);
        int locnpc3y = (int)(Math.random()*(heightIsland2-40)+20+tiley2);
        npcs.add(new GoodNPC("Stewie", locnpc3x,locnpc3y,npcc3));
        int locnpc4x = (int)(Math.random()*(widthIsland2-40)+20+tilex2);
        int locnpc4y = (int)(Math.random()*(heightIsland2-40)+20+tiley2);
        npcs.add(new GoodNPC("Gram",locnpc4x,locnpc4y,npcc4));

        //sets keylistener
        setFocusable(true);
        addKeyListener(this);
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(regFont);

        //draws background
        g.setColor(new Color(68,150,185));
        g.fillRect(0, 0, 3000, 3000);
    
        g.setColor(new Color(0,100,0));
        g.fillRect(tilex,tiley,widthIsland1,heightIsland1);
        g.fillRect(tilex2,tiley2,widthIsland2,heightIsland2);
        g.fillRect(tilex3,tiley3,widthIsland3,heightIsland3);

        drawRoad(g);
        drawTree(g);

        //Draw the game objects
        //t1.drawMe(g);
        p1.drawMe(g);

        for (int i=0; i<npcs.size(); i++) {
            npcs.get(i).drawMe(g);
        }

        g.setFont(regFont);
        if (foodEaten == 1) { // returned
            dialogueI++;
            textSpoke = "With food I can be a god, DIE";
            gameWon = true;
            textTalked = true;
        } else if (foodEaten == 2) {
            dialogueI+=2;
            textSpoke = "YOU ATE MY FOOD, I MUST FIGHT YOU NOW PALETA";
            gameWon = true;
            textTalked = true;
        }

        if (gameWon) {
            g.setColor(Color.BLACK);
            g.setFont(endFont);
            g.fillRect(-10,-10,2000,2000);
            g.setColor(Color.RED);
            g.drawString("Game Over",275,350);
        }
        g.setFont(regFont);

        if (textTalked) {
            drawText(g,textSpoke);
        }

        textTalked = false;
    }

    public void drawTree(Graphics g) {
        t1.drawMe(g,randomTX1,randomTY1);
        t2.drawMe(g,randomTX2,randomTY2);
        t3.drawMe(g,randomTX3,randomTY3);
    }

    public void drawRoad(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(roadXStart,tiley,50,heightIsland1);
        g.fillRect(roadX2Start,tiley2,50,heightIsland2);
        g.fillRect(roadX3Start,tiley3,50,heightIsland3);
        g.setColor(new Color(253,255,81));
        for (int i=tiley; i<heightIsland1-8; i++) {
            if (i%20== 0) {
                g.fillRect(roadXStart+21,i,3,8);
            }
        }
        for (int i=tiley2; i<heightIsland2+tiley2-8; i++) {
            if (i%20== 0) {
                g.fillRect(roadX2Start+21,i,3,8);
            }
        }
        for (int i=tiley3; i<heightIsland3+tiley3-8; i++) {
            if (i%20== 0) {
                g.fillRect(roadX3Start+21,i,3,8);
            }
        }
    }

    public void drawText(Graphics g, String text) {
        g.setColor(Color.WHITE);
        g.fillRect(30,30,740,60);
        g.setColor(Color.BLACK);
        g.drawString(text,40,50);
    }

    //implement methods of the KeyListener
    public void keyPressed(KeyEvent e) {
        //System.out.println( "key code: " + e.getKeyCode()  );  //collision detection on island //find out which directional way doesn't work so other directions dont freeze too.
        if (p1.getX() + p1.getWidth() >= tilex && p1.getX() <= tilex + widthIsland1 && p1.getY() + p1.getHeight() >= tiley && p1.getY() <= tiley + heightIsland1) {
            islandNum = 1;
            if (p1.getInShip()) {
                p1.IsInShip(false);
            }  
        } else if (p1.getX() + p1.getWidth() >= tilex2 && p1.getX() <= tilex2 + widthIsland2 && p1.getY() + p1.getHeight() >= tiley2 && p1.getY() <= tiley2 + heightIsland2) {
            islandNum = 2;
            if (p1.getInShip()) {
                p1.IsInShip(false);
            }
        } else if (p1.getX() + p1.getWidth() >= tilex3 && p1.getX() <= tilex3 + widthIsland3 && p1.getY() + p1.getHeight() >= tiley3 && p1.getY() <= tiley3 + heightIsland3) {
            islandNum = 3;
            if (p1.getInShip()) {
                p1.IsInShip(false);
            }
        } else {
            islandNum = 4;
            if (!p1.getInShip()) {
                p1.IsInShip(true);
            }
        }
        
        if (islandNum == 1) {
            if (p1.getX() + p1.getWidth() >= widthIsland1 + tilex) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() + p1.getWidth() >= 800)) {
                        moveRightPos = false;
                    }
                }
            } 
            if (p1.getX() <= tilex ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() <= 0)) {
                        moveLeftPos = false;
                    }
                }
            }
            if (p1.getY() + p1.getHeight() >= tiley + heightIsland1 ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() + p1.getHeight() >= 800)) {
                        moveDownPos = false;
                    }
                }
            } 
            if (p1.getY() <= tiley) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() <= 0)) {
                        moveUpPos = false;
                    }
                }
            } 
        } else if (islandNum == 2) {
            if (p1.getX() + p1.getWidth() >= widthIsland2 + tilex2) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() + p1.getWidth() >= 800)) {
                        moveRightPos = false;
                    }
                }
            } 
            if (p1.getX() <= tilex2 ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() <= 0)) {
                        moveLeftPos = false;
                    }
                }
            }
            if (p1.getY() + p1.getHeight() >= tiley2 + heightIsland2 ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() + p1.getHeight() >= 800)) {
                        moveDownPos = false;
                    }
                }
            } 
            if (p1.getY() <= tiley2) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() <= 0)) {
                        moveUpPos = false;
                    }
                }
            } 
        } else if (islandNum == 3) {
            if (p1.getX() + p1.getWidth() >= widthIsland3 + tilex3) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() + p1.getWidth() >= 800)) {
                        moveRightPos = false;
                    }
                }
            } 
            if (p1.getX() <= tilex3 ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getX() <= 0)) {
                        moveLeftPos = false;
                    }
                }
            }
            if (p1.getY() + p1.getHeight() >= tiley3 + heightIsland3 ) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() + p1.getHeight() >= 800)) {
                        moveDownPos = false;
                    }
                }
            } 
            if (p1.getY() <= tiley3) {
                if (p1.getInShip() == false) {
                    if (!(p1.getY() <= 0)) {
                        moveUpPos = false;
                    }
                }
            } 
        }

        //if no problems
        if (e.getKeyCode() == 38 && moveUpPos) {
            // up, left, down, right
            p1.changeY(-5);
        }
        if (e.getKeyCode() == 37 && moveLeftPos) {
            p1.changeX(-5);
        }
        if (e.getKeyCode() == 40 && moveDownPos) {
            p1.changeY(5);
        }
        if (e.getKeyCode() == 39 && moveRightPos) {
            p1.changeX(5);
        }

        if (p1.getX() >= 800) {
            p1.changeX(-800-p1.getWidth());
            widthIsland1-=800;
            tilex2-=800;
            tilex3-=800;
            for (int i=0; i<npcs.size(); i++) 
                npcs.get(i).changeXY(-800,0);
            p1.changeShipXY(-800,0);
            roadXStart-=800;
            roadX2Start-=800;
            roadX3Start-=800;
            randomTX1-=800;
            randomTX2-=800;
            randomTX3-=800;
        } else if (p1.getX()+p1.getWidth() <= 0) {
            p1.changeX(800);
            widthIsland1+=800;
            tilex2+=800;
            tilex3+=800;
            for (int i=0; i<npcs.size(); i++) 
                npcs.get(i).changeXY(800,0);
            p1.changeShipXY(800,0);
            roadXStart+=800;
            roadX2Start+=800;
            roadX3Start+=800;
            randomTX1+=800;
            randomTX2+=800;
            randomTX3+=800;
        } else if (p1.getY() >= 800) {
            p1.changeY(-800+p1.getHeight());
            heightIsland1-=800;
            tiley2-=800;
            tiley3-=800;
            for (int i=0; i<npcs.size(); i++) 
                npcs.get(i).changeXY(0,-800);
            p1.changeShipXY(0,-800);
            randomTY1-=800;
            randomTY2-=800;
            randomTY3-=800;
        } else if (p1.getY() <= 0) {
            p1.changeY(800);
            heightIsland1+=800; 
            tiley2+=800;
            tiley3+=800;
            randomTY1+=800;
            randomTY2+=800;
            randomTY3+=800;
            for (int i=0; i<npcs.size(); i++) 
                npcs.get(i).changeXY(0,800);
            p1.changeShipXY(0,800);
        }

        moveUpPos = true;
        moveDownPos = true;
        moveLeftPos = true;
        moveRightPos = true;

        if (p1.getX() + p1.getWidth() >= npcs.get(0).getX() && p1.getX() <= npcs.get(0).getX() + npcs.get(0).width() && p1.getY() + p1.getHeight() >= npcs.get(0).getY() && p1.getY() <= npcs.get(0).getY() + npcs.get(0).height()) {
            if (dialogueI == 0) {
                textTalked = true;
                dialogueI++;
                System.out.println(quest1.get(dialogueI));
                textSpoke = quest1.get(dialogueI);
            }
            if (quest1.size() > dialogueI+1) {
                if (e.getKeyCode() == 32) {
                    if (foodEaten == 0) {
                        if (dialogueI<3) {
                            textTalked = true;
                            dialogueI++;
                            textSpoke = quest1.get(dialogueI);
                            talkedto1 = true;
                        }
                    }
                }
            }
            textTalked = true;
        } else if (p1.getX() + p1.getWidth() >= npcs.get(1).getX() && p1.getX() <= npcs.get(1).getX() + npcs.get(1).width() && p1.getY() + p1.getHeight() >= npcs.get(1).getY() && p1.getY() <= npcs.get(1).getY() + npcs.get(1).height()) {
            if (talkedto1) {
                if (dialogueI2 == 0) {
                    textTalked = true;
                    textSpoke = quest2.get(dialogueI2);
                    dialogueI2++;
                }
                if (quest2.size() > dialogueI2+1) {
                    if (e.getKeyCode() == 32) {
                        textTalked = true;
                        textSpoke = quest2.get(dialogueI2);
                        dialogueI2++;
                    }
                } else {
                    talkedto2 = true;
                }
                if (food2ret && !lineUsed2) {
                    textTalked = true;
                    textSpoke = "alright, heres the food back, you can eat with e or return with r";
                    lineUsed2 = true;
                }
            }
            textTalked = true;
        } else if (p1.getX() + p1.getWidth() >= npcs.get(2).getX() && p1.getX() <= npcs.get(2).getX() + npcs.get(2).width() && p1.getY() + p1.getHeight() >= npcs.get(2).getY() && p1.getY() <= npcs.get(2).getY() + npcs.get(2).height()) {
            if (talkedto2 && talkedto1) {
                if (dialogueI3 == 0) {
                    textTalked = true;
                    textSpoke = quest3.get(dialogueI3);
                    dialogueI3++;
                }
                if (quest3.size() > dialogueI3+1) {
                    if (e.getKeyCode() == 32) {
                        textTalked = true;
                        textSpoke = quest3.get(dialogueI3);
                        dialogueI3++;
                    }
                } else {
                    talkedto3 = true;
                }
            }
            if (talkedto4 && !lineUsed3) {
                textTalked = true;
                food2ret = true;
                textSpoke = quest3.get(quest3.size()-1);
                lineUsed3 = true;
            }
            textTalked = true;
        } else if (p1.getX() + p1.getWidth() >= npcs.get(3).getX() && p1.getX() <= npcs.get(3).getX() + npcs.get(3).width() && p1.getY() + p1.getHeight() >= npcs.get(3).getY() && p1.getY() <= npcs.get(3).getY() + npcs.get(3).height()) {
            if (talkedto2 && talkedto1) {
                if (dialogueI4 == 0) {
                    textTalked = true;
                    textSpoke = quest4.get(dialogueI4);
                    dialogueI4++;
                }
                if (quest4.size() > dialogueI4) {
                    if (e.getKeyCode() == 32) {
                        textTalked = true;
                        textSpoke = quest4.get(dialogueI4);
                        dialogueI4++;
                    }
                } else {
                    talkedto4 = true;
                }
            }
            textTalked = true;
        }

        if ((lineUsed2 && !oneKeyPressed) || tildaUsed) {
            if (e.getKeyCode() == 69) {
                foodEaten = 2;
                oneKeyPressed = true;
            } else if (e.getKeyCode() == 82) {
                foodEaten = 1;
                oneKeyPressed = true;
            }
        }

        if (e.getKeyCode() == 192) { //tilda and cheat code
            lineUsed2 = true;
            food2ret = true;
            textTalked = true;
            textSpoke = "press e to eat food or r to return";
            tildaUsed = true;
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}