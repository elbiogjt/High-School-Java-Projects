import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
 
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener{
    private MyHashMap<State,StateInfo> states = new MyHashMap<>();
    private JTextArea typeArea = new JTextArea();
    private JButton viewAll = new JButton("View All");
    private JButton viewState = new JButton("View State Info");
    private JButton addB = new JButton("Add");
    private JButton removeB = new JButton("Remove");
    private JButton addUrl = new JButton("Add Url");
    private JTextField abrev = new JTextField(); 
    private JTextField urlIn = new JTextField();
    private JTextField nameText = new JTextField();
    private JTextField capitals = new JTextField();
    private JTextField pop = new JTextField();
    private JTextField siz = new JTextField();
    private String drawUrl = null;

    public Screen() {
        states.put(new State("California", "CA"), new StateInfo("Sacramento", 39240000, 163696));
        states.put(new State("New York", "NY"), new StateInfo("Albany", 19840000, 54556));
        states.put(new State("Maine", "ME"), new StateInfo("Augusta", 1372000, 35385));
        states.get(new State("", "CA")).addLandmarkImages("http://cdn.cnn.com/cnnnext/dam/assets/181002113456-01-golden-gate-bridge-restricted.jpg");
        states.get(new State("", "CA")).addLandmarkImages("https://www.eatthis.com/wp-content/uploads/sites/4/2021/11/in-n-out-exterior.jpg");
        states.get(new State("", "NY")).addLandmarkImages("https://www.history.com/.image/ar_4:3%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTY1MTc1MTk3ODI0MDAxNjA5/topic-statue-of-liberty-gettyimages-960610006-promo.jpg");
        states.get(new State("", "NY")).addLandmarkImages("https://cdn.britannica.com/73/114973-050-2DC46083/Midtown-Manhattan-Empire-State-Building-New-York.jpg");
        states.get(new State("", "ME")).addLandmarkImages("https://travel.mqcdn.com/mapquest/travel/wp-content/uploads/2020/07/GettyImages-1220196496-scaled.jpg");
        states.get(new State("", "ME")).addLandmarkImages("http://portlandheadlight.com/wp-content/uploads/rich-morin-phl-2.jpg");

        abrev = new JTextField("Abbrev Input");
        abrev.setBounds(10,420,150,30);
        add(abrev);

        urlIn = new JTextField("URL Input");
        urlIn.setBounds(10,455,150,30);
        add(urlIn);

        viewState.setBounds(10,490,150,30);
        add(viewState);
        viewState.addActionListener(this);

        viewAll.setBounds(10,525,150,30);
        add(viewAll);
        viewAll.addActionListener(this);

        addB.setBounds(10,560,150,30);
        add(addB);
        addB.addActionListener(this);

        addUrl.setBounds(10,595,150,30);
        add(addUrl);
        addUrl.addActionListener(this);

        removeB.setBounds(10,630,150,30);
        add(removeB);
        removeB.addActionListener(this);

        nameText = new JTextField("Name Input");
        nameText.setBounds(165,420,150,30);
        add(nameText);

        capitals = new JTextField("Capitals Input");
        capitals.setBounds(165,455,150,30);
        add(capitals);

        pop = new JTextField("Population Input");
        pop.setBounds(165,490,150,30);
        add(pop);

        siz = new JTextField("Size Input");
        siz.setBounds(165,525,150,30);
        add(siz);

        String text ="";
        for (State stat : states.keySet()) {
            text += stat + "\n";
        }
        typeArea = new JTextArea(300, 200);
        typeArea.setText(text);
        add(typeArea);
        typeArea.setVisible(true);
        typeArea.setEditable(false);
        typeArea.setBounds(10, 10, 390, 400);

        setLayout(null);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,700);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(30,30,30));
        g.fillRect(0,0,900,900);
        if (drawUrl != null) {
            states.get(new State("", drawUrl)).drawAllImages(g,600,20);
        }
    }

    public void viewAll() {
        typeArea.setText("");
        String text ="";
        for (State stat : states.keySet()) {
            if (stat == null) {
                continue;
            }
            text += stat + "\n";
        }
        typeArea.setText(text);
        drawUrl = null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewAll) {
            viewAll();
        } else if (e.getSource() == viewState) {
            String a = abrev.getText();
            String result = states.keySet().get(new State("",a)).toString();
            result += "\n\n" + states.get(new State("", a)).toString();
            typeArea.setText(result);
            if (states.get(new State("",a)).getLandmarkImages().size() > 0) {
                drawUrl = a;
            }
        } else if (e.getSource() == removeB) {                    //WHHHHHHHHY DONT THIS WORK???
            String abre = abrev.getText();
            State stat = new State("", abre);
            states.remove(stat);
            states.keySet().remove(stat);
            viewAll();  
        } else if (e.getSource() == addUrl) {
            String urls = urlIn.getText();
            String a = abrev.getText();
            states.get(new State("",a)).addLandmarkImages(urls);
        } else if (e.getSource() == addB) {
            String ab = abrev.getText();
            String na = nameText.getText();
            String ca = capitals.getText();
            int po = Integer.parseInt(pop.getText());
            int si = Integer.parseInt(siz.getText());
            states.put(new State(na,ab),new StateInfo(ca,po,si));
            viewAll();
        }
        repaint();
    }

}
