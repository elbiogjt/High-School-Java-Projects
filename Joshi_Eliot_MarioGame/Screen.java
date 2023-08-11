import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Screen extends JPanel implements KeyListener{
    private JTextArea textA = new JTextArea();
    private MyHashTable<Location,String> hashtab = new MyHashTable<>();
    private Player p = new Player(10,10);
    private NPC npc1 = new NPC(40,40,"https://www.clipartmax.com/png/full/120-1202437_15-september-7-2016-mario-green-toad.png");
    private NPC npc2 = new NPC(60,60,"https://freepngimg.com/download/yoshi/21792-9-yoshi-transparent-background.png");
    private NPC goom1 = new NPC(10,70,"https://upload.wikimedia.org/wikipedia/en/c/ce/Goomba.PNG");
    private NPC goom2 = new NPC(11,70,"https://upload.wikimedia.org/wikipedia/en/c/ce/Goomba.PNG");
    private NPC mush = new NPC(80,80,"https://mario.wiki.gallery/images/f/fb/Sticker_Mushroom_-_Mario_Party_Superstars.png");
    private NPC bom = new NPC(75,15,"https://mario.wiki.gallery/images/4/47/PMCS_Sprite_-_Big_Bob-omb.png"); 
    private int qcomplete = 0;
    private int npcs1 = 0;
    private int npcs2 = 0;
    private int goombaStomped = 0;
    private boolean mushroomAte = false;
    private boolean movements = false;
    private String saveFile = "save.serial";
    // private Thread mt;
    private String npcUp = "";
    private String npcDown = "";
    private String npcRight = "";
    private String npcLeft = "";
    
    public Screen() {
        add(textA);
        textA.setVisible(false);
        textA.setEditable(false);
        textA.setBounds(0, 500, 500, 50);

        setLayout(null);
        setFocusable(true);
        addKeyListener(this);
        try {
            Scanner scan = new Scanner(new FileReader("MapExportFile.txt"));
            int row = 0;
            int col = 0;
            while(scan.hasNextLine()) {
                while(scan.hasNext()) {
                    Location t = new Location(row,col);
                    String a = scan.next();
                    hashtab.put(t,a);
                    row++;
                    if (row > 99) {
                        break;
                    }
                }
                col++;
                row = 0;
                if (col > 99) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // mt = new Thread(new Animate(this));
        // mt.start();
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,550);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,1000,1000);
        for (int r=0; r<100;r++) {
            for (int c=0; c<100; c++) {
                Location t = new Location(r,c);
                String cur = hashtab.get(t).get(0);
                if(cur.equals("4")) {
                    g.setColor(Color.BLUE);
                } else if (cur.equals("8")) {
                    g.setColor(Color.BLACK);
                } else if (cur.equals("7")) {
                    g.setColor(Color.GRAY);
                } else if (cur.equals("3")) {
                    g.setColor(new Color(128,72,0));
                } else if (cur.equals("6")) {
                    g.setColor(Color.GREEN);
                } else if (cur.equals("5")) {
                    g.setColor(Color.RED);
                }
                g.fillRect(r*5,c*5,5,5);
            }
        }
        npc1.drawMe(g);
        npc2.drawMe(g);
        goom1.drawMe(g);
        goom2.drawMe(g);
        bom.drawMe(g);
        mush.drawMe(g);
        p.drawMe(g);
        g.setColor(Color.BLACK);
        if(!movements) {
            textA.setText("The game will autosave\tPress L before moving to load game\nUse arrow keys to move\tWalk on things to interact");
            textA.setVisible(true);
        } 
        // NPCMovement(goom1);
        // goom1.move(npcUp,npcDown,npcRight,npcLeft);
        // NPCMovement(goom2);
        // goom2.move(npcUp,npcDown,npcRight,npcLeft);
        // NPCMovement(bom);
        // bom.move(npcUp,npcDown,npcRight,npcLeft);
        g.setColor(Color.WHITE);
        g.drawString("Quests Done: " + qcomplete, 400,500);
        g.setColor(Color.BLACK);
        if (qcomplete >= 2) {
            p.setCoord(6000, 6000);
            g.setColor(Color.BLACK);
            g.fillRect(0,0,600,600);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial",20,40));
            g.drawString("YOU WIN",160,290);
        }
    }

    public void NPCMovement(NPC npc) {
        int npcx = npc.getCoord()[0];
        int npcy = npc.getCoord()[1];
        Location t = new Location(npcx,npcy-1);
        npcUp = hashtab.get(t).get(0);
        t = new Location(npcx,npcy+1);
        npcDown = hashtab.get(t).get(0);
        t = new Location(npcx+1,npcy);
        npcRight = hashtab.get(t).get(0);
        t = new Location(npcx-1,npcy);
        npcLeft = hashtab.get(t).get(0);
    }

    public void load() {
        try {
            FileInputStream fins = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(fins);
            int[] a = (int[])in.readObject();
            qcomplete = a[2];
            p.setCoord(a[0],a[1]);
            if (qcomplete == 1) {
                goom1.unDraw();
                goom2.unDraw();
            }
            fins.close();
            in.close();
            repaint();
        } catch (NullPointerException ex) {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int[] save() {
        int x = p.getCoord()[0];
        int y = p.getCoord()[1];
        return new int[] {x,y,qcomplete};
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (qcomplete < 2) {
            String moveableArea1 = "8";
            String moveableArea2 = "3";
            int px = p.getCoord()[0];
            int py = p.getCoord()[1];
            Location t = new Location(px,py-1);
            String upAble = hashtab.get(t).get(0);
            t = new Location(px,py+1);
            String downAble = hashtab.get(t).get(0);
            t = new Location(px+1,py);
            String rightAble = hashtab.get(t).get(0);
            t = new Location(px-1,py);
            String leftAble = hashtab.get(t).get(0);
            if (e.getKeyCode() == 38) {
                if (upAble.equals(moveableArea1) || upAble.equals(moveableArea2)) {
                    p.up();
                }
            } else if (e.getKeyCode() == 37) {
                if (leftAble.equals(moveableArea1) || leftAble.equals(moveableArea2)) {
                    p.left();
                }
            } else if (e.getKeyCode() == 40) {
                if (downAble.equals(moveableArea1) || downAble.equals(moveableArea2)) {
                    p.down();
                }
            } else if (e.getKeyCode() == 39) {
                if (rightAble.equals(moveableArea1) || rightAble.equals(moveableArea2)) {
                    p.right();
                }
            } else if (e.getKeyCode() == 67) {
                qcomplete++;
                npcs1 = 1;
                goom1.unDraw();
                goom2.unDraw();
                npc1.unDraw();
            } else if (e.getKeyCode() == 76) {
                if (!movements) {
                    load();
                }
            } 
    
            if (px == npc1.getCoord()[0] && py == npc1.getCoord()[1]) {
                String text;
                if (npcs1 == 0) {
                    text = "Hi Mario, can you stomp on them goombas real quick?";
                    npcs1++;
                } else if (npcs1 == 1 && goombaStomped < 2) {
                    text = "Go walk on them goombas right now";
                } else if (npcs1 == 1 && goombaStomped >= 2) {
                    text = "NICE! I dont like goombas";
                    npcs1++;
                    qcomplete++;
                } else {
                    text = "Thats all I wanted, run away unless you tryna scrap";
                    npc1.unDraw();
                }
                textA.setVisible(true);
                textA.setText(text);
                npc1.playSound("mparty8_toad_06.wav");
            } else if (px == npc2.getCoord()[0] && py == npc2.getCoord()[1]) {
                String text = "";
                if (npcs2 == 0) {
                    text = "Yo Im Yoshi\nCan you eat that Mushroom for me";
                    npcs2++;
                } else if (npcs2 == 1 && mushroomAte == false) {
                    text = "Eat that mushroom bro";
                } else if (npcs2 == 1 && mushroomAte == true) {
                    text = "You grew bro";
                    npcs2++;
                    qcomplete++;
                } else {
                    npc2.unDraw();
                }
                textA.setVisible(true);
                textA.setText(text);
                npc2.playSound("smw2_yoshi_sound.wav");
            } else if (px == goom1.getCoord()[0] && py == goom1.getCoord()[1]) {
                goom1.playSound("mparty8_goomba_03.wav");
                goom1.unDraw();
                goombaStomped++;
            } else if (px == goom2.getCoord()[0] && py == goom2.getCoord()[1]) {
                goom2.playSound("mparty8_goomba_03.wav");
                goom2.unDraw();
                goombaStomped++;
            } else if (px == mush.getCoord()[0] && py == mush.getCoord()[1]) {
                mush.unDraw();
                mushroomAte =true;
                p.sizeUp();
            } else if (px == bom.getCoord()[0] && py == bom.getCoord()[1]) {
                bom.unDraw();
                p.setCoord(10,10);;
            } else {
                textA.setVisible(false);
            }
            repaint();
            movements = true;
        }
    }
}