import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Image;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
 
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Screen extends JPanel implements ActionListener, MouseListener {
    private Graph<Location> graph = new Graph<Location>();
    private Location current;
    private JTextField locStartField;
    private JTextField locEndField;
    private JLabel locStartLabel;
    private JLabel locEndLabel;
    private Font font;
    private Font boldFont;
    private JButton directions;
    private JButton reset;
    private JTextArea textA = new JTextArea();
    private JScrollPane scrollPlane = new JScrollPane(textA);
    private boolean showImage = false;
    private Image map;

    public Screen() {
        try {
            map = ImageIO.read(new File("Map.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        Location ggb = new Location("Golden Gate Bridge", "ggb", 100, 20, "https://iso.500px.com/wp-content/uploads/2016/05/stock-photo-93725859.jpg"); //1.1219512195
        Location ais = new Location("Alcatraz Island", "ais", 612, 233, "https://www.gannett-cdn.com/-mm-/1abbac059a7e6f21ff3aa7e38760a41a48819119/c=0-217-2118-1414/local/-/media/2018/08/17/USATODAY/USATODAY/636701422865855661-GettyImages-632216604.jpg");
        Location fwh = new Location("Fisherman's Wharf", "fwh", 248, 301, "https://assets.simpleviewinc.com/simpleview/image/upload/c_limit,h_1200,q_75,w_1200/v1/clients/fishermanswharfca/shutterstock_730404997_9bdf34ca-fdb5-4cfc-bc8c-5bc4c2d995c2.jpg");
        Location mma = new Location("MoMA", "mma", 255, 380, "https://media.cntraveler.com/photos/5a846a5352e7b4436ff64e18/16:9/w_2560%2Cc_limit/SFMOMA_BRIAN-FLAHERTY_2018_170324_Flaherty_SFBlackBook_31-copy.jpg");
        Location pat = new Location("Fine Arts Palace", "pat", 152, 322, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Palace_of_Fine_Arts_%2816794p%29.jpg/1200px-Palace_of_Fine_Arts_%2816794p%29.jpg");
        Location car = new Location("Cable Cars", "car", 24, 301, "https://www.travelinusa.us/wp-content/uploads/sites/3/2017/05/Cable-Car-San-Francisco-01.jpg");
        Location cot = new Location("Coit Tower", "cot", 172, 353, "https://i0.wp.com/crawlsf.com/wp-content/uploads/2020/12/coit-tower.jpg?fit=1200%2C800&ssl=1");
        Location cwn = new Location("Chinatown", "cwn", 165, 380, "https://www.travelinusa.us/wp-content/uploads/sites/3/2018/02/Chinatown-San-Francisco-Cosa-Vedere.jpg");
        Location ggp = new Location("Golden Gate Park", "ggp", 492, 399, "https://extras.sfgate.com/img/travel/goldengatepark.jpg");
        Location cph = new Location("Palace of Legion of Honor", "cph", 556, 349, "https://c8.alamy.com/comp/MM3T8F/legion-of-honor-san-francisco-1965-MM3T8F.jpg");
        Location exp = new Location("Exploratorium", "exp", 198, 432, "https://media.istockphoto.com/id/160583646/photo/exploratorium-and-palace-of-fine-art-in-san-francisco.jpg?s=612x612&w=0&k=20&c=LlwQGo7snisXaUKayNCzPeYxO8aUCGG6w7nvmLPQOlY=");
        Location gsq = new Location("Ghirardelli Square", "gsq", 301, 360, "https://thumbs.dreamstime.com/b/ghirardelli-square-sam-francisco-california-famous-san-48798083.jpg");
        Location hab = new Location("Haight-Ashbury", "hab", 303, 121, "https://bloximages.chicago2.vip.townnews.com/times-news.com/content/tncms/assets/v3/editorial/e/c0/ec0048e0-b9c5-5e10-b588-622e3a5ec7f8/5e895ed54ada0.image.jpg?resize=750%2C500");
        Location lst = new Location("Lombard Street", "lst", 49, 403, "https://img.theculturetrip.com/wp-content/uploads/2016/10/1195px-lombardsf2.jpeg");
        Location fbd = new Location("Ferry Building", "fbd", 263, 469, "https://upload.wikimedia.org/wikipedia/commons/0/0c/San_Francisco_Ferry_Building_%28cropped%29.jpg");
        Location dym = new Location("de Young Museum", "dym", 373, 307, "https://upload.wikimedia.org/wikipedia/commons/c/cc/M._H._de_Young_Memorial_Museum.jpg");
        Location pal = new Location("Painted Ladies", "pal", 264, 321, "https://www.travelinusa.us/wp-content/uploads/sites/3/2017/11/Painted-Ladies-San-Francisco-01.jpg");
        Location att = new Location("AT&T Park", "att", 331, 484, "https://i.etsystatic.com/15978085/r/il/7f3bfa/3973958791/il_fullxfull.3973958791_ljde.jpg");
        Location cas = new Location("Castro District", "cas", 431, 121, "https://media.gettyimages.com/id/529540728/photo/iconic-castro-san-francisco-california-united-states-of-america-north-america.jpg?s=612x612&w=gi&k=20&c=tGP5Fjbv822m6TKIZ3Bu8dkC_PF5ZV3ku1iQvOi8T00=");
        Location sch = new Location("SF City Hall", "sch", 200, 480, "https://sf.gov/sites/default/files/styles/default/public/2021-06/pink%20over%20city%20hall_sergio%20ruiz.jpg?itok=9VkWJOBR");

        graph.add(ggb);
        graph.add(ais);
        graph.add(fwh);
        graph.add(mma);
        graph.add(pat);
        graph.add(car);
        graph.add(cot);
        graph.add(cwn);
        graph.add(ggp);
        graph.add(cph);
        graph.add(exp);
        graph.add(gsq);
        graph.add(hab);
        graph.add(lst);
        graph.add(fbd);
        graph.add(dym);
        graph.add(pal);
        graph.add(att);
        graph.add(cas);
        graph.add(sch);
        graph.addEdge(fwh,ais, 3);
        graph.addEdge(fwh, ggb, 6);
        graph.addEdge(car, fwh,1);
        graph.addEdge(car, mma, 1);
        graph.addEdge(mma, pat, 4);
        graph.addEdge(pat, car, 3);
        graph.addEdge(pat, cot, 3);
        graph.addEdge(cot, car, 1);
        graph.addEdge(cot, cwn, 1);
        graph.addEdge(ggp, ggb, 4);
        graph.addEdge(ggp, cph, 1);
        graph.addEdge(cwn, exp, 1);
        graph.addEdge(exp, cot, 1);
        graph.addEdge(gsq, exp, 2);
        graph.addEdge(hab, gsq, 4);
        graph.addEdge(hab, fwh, 5);
        graph.addEdge(hab, lst, 4);
        graph.addEdge(fbd, dym, 7);
        graph.addEdge(fbd, car, 2);
        graph.addEdge(dym, fbd, 7);
        graph.addEdge(dym, pal, 3);
        graph.addEdge(pal, fbd, 3);
        graph.addEdge(att, pal, 4);
        graph.addEdge(att, cwn, 2);
        graph.addEdge(cas, pat, 3);
        graph.addEdge(cas, dym, 3);
        graph.addEdge(cas, hab, 1);
        graph.addEdge(sch, cas, 2);
        graph.addEdge(ggp, sch, 2);
        graph.addEdge(cas, fbd, 5);
        graph.addEdge(sch, cph, 3);
        
        font = new Font("Arial", Font.PLAIN, 20);
        boldFont = new Font("Arial",Font.BOLD, 10);
        locStartField = new JTextField();
        locStartField.setFont(font);
        locStartField.setHorizontalAlignment(SwingConstants.CENTER);
        locStartField.setBounds(590, 375, 150, 30);
        locStartField.setText("");
        this.add(locStartField);

        locStartLabel = new JLabel();
        locStartLabel.setFont(font);
        locStartLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locStartLabel.setBounds(590, 410, 150, 30);
        locStartLabel.setText("Start Location");
        this.add(locStartLabel);

        locEndField = new JTextField();
        locEndField.setFont(font);
        locEndField.setHorizontalAlignment(SwingConstants.CENTER);
        locEndField.setBounds(590, 445, 150, 30);
        locEndField.setText("");
        this.add(locEndField);

        locEndLabel = new JLabel();
        locEndLabel.setFont(font);
        locEndLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locEndLabel.setBounds(590, 480, 150, 30);
        locEndLabel.setText("End Location");
        this.add(locEndLabel);

        directions = new JButton();
        directions.setFont(font);
        directions.setHorizontalAlignment(SwingConstants.CENTER);
        directions.setBounds(590, 515, 150, 30);
        directions.setText("Directions");
        this.add(directions);
        directions.addActionListener(this);

        reset = new JButton();
        reset.setFont(font);
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        reset.setBounds(590, 550, 150, 30);
        reset.setText("Reset");
        this.add(reset);
        reset.addActionListener(this);
        
        String text = graph.getKeySet().toDLList().toString();
        textA = new JTextArea();
        textA.setEditable(false);
        textA.setText(text);
        scrollPlane = new JScrollPane(textA);
		scrollPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPlane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPlane.setBounds(20,500,550,80);
        add(scrollPlane); 
        setLayout(null);
        setFocusable(true);

        setLayout(null);
        addMouseListener(this);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(30,30,30));
        g.fillRect(0,0,900,900);
        g.drawImage(map, 0, 0, 590,590,null);
        drawMap(g);
        if (showImage) {
            current.drawImage(g, 660, 20);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(boldFont);
            g.drawString("Click on Location for Image",630,20);
        }
    }

    public void drawMap(Graphics g) {
        g.setColor(Color.WHITE);
        DLList<Location> graphset = graph.getKeySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            int x1 = graphset.get(i).getX();
            int y1 = graphset.get(i).getY();
            if (graphset.get(i).isGreen()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.fillOval(x1-4, y1-4, 8, 8);
            g.setFont(boldFont);
            g.drawString(graphset.get(i).toString(), x1-20, y1-10);
            DLList<Location> set = graph.getHashMap().get(graphset.get(i)).keySet().toDLList();
            for (int j=0; j<set.size(); j++) {
                int x2 = set.get(j).getX();
                int y2 = set.get(j).getY();
                if (graphset.get(i).isGreen() && set.get(j).isGreen()) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.gray);
                }
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == directions) {
            String locS1 = locStartField.getText();
            String locS2 = locEndField.getText();
            Location loc1 = new Location("",locS1,0,0,"");
            Location loc2 = new Location("",locS2,0,0,"");
            DLList<Location> list = graph.distance(loc1, loc2);
            DLList<Location> graphset = graph.getKeySet().toDLList();
            for (int i=0; i<graphset.size(); i++) {
                graphset.get(i).setGreen(false);
            }
            DLList<Location> actualList = new DLList<Location>();
            for (int i=0; i<list.size(); i++) {
                for (int j=0; j<graphset.size(); j++) {
                    if (list.get(i).equals(graphset.get(j))) {
                        actualList.add(graphset.get(j));
                        actualList.get(i).setGreen(true);
                    } 
                }
            }
            String text = "";
            int totalDist = 0;
            System.out.println(actualList);
            for (int i=0; i<actualList.size(); i++) {
                if (i!=0) {
                    text += "Take " + actualList.get(i-1) + " to " + actualList.get(i) + "\tDist: " + graph.getWeight(actualList.get(i), actualList.get(i-1)) + "\n";
                    totalDist += graph.getWeight(actualList.get(i), actualList.get(i-1));
                }
            }
            textA.setText(text + "Total Distance: " + totalDist);
        } else if (e.getSource() == reset) {
            textA.setText(graph.getKeySet().toDLList().toString());
            DLList<Location> graphset = graph.getKeySet().toDLList();
            for (int i=0; i<graphset.size(); i++) {
                graphset.get(i).setGreen(false);
            }
            showImage = false;
            current = null;
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DLList<Location> graphset = graph.getKeySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            if (graphset.get(i).hitBox(e.getX(), e.getY())) {
                showImage = true;
                current = graphset.get(i);
                break;
            }
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}