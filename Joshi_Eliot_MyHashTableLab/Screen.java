import java.util.Scanner;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Screen extends JPanel implements ActionListener{
    public static final String saveFile = "save.serial";
    private MyHashTable<Country,MyImage> hashtab = new MyHashTable<>();
    private MyHashSet<Country> hashset = new MyHashSet<>();
    private Country currentCountry = null;
    private JTextArea textA = new JTextArea();
    private JScrollPane scrollPlane = new JScrollPane(textA);
    private JTextField abbreField = new JTextField("Abbreviation");
    private JTextField urlIn = new JTextField("Url Input");
    private JTextField landmark = new JTextField("Landmark Input");
    private JButton addImage = new JButton("Add Image");
    private JButton abbreButton = new JButton("Select Country");
    private JButton nextIm = new JButton("->");
    private JButton prevIm = new JButton("<-");
    private JButton deleteCur = new JButton("Delete Current Image");
    private JButton showAll = new JButton("Whole View");
    private int imageOn = -1;
    private boolean remove = false;

    public Screen() {
        
        try {
            FileInputStream fins = new FileInputStream(saveFile);
            ObjectInputStream in = new ObjectInputStream(fins);
            @SuppressWarnings("unchecked") 
            MyHashTable<Country,MyImage> loadTo = (MyHashTable<Country,MyImage>) in.readObject();
            MyHashSet<Country> mhs = loadTo.keySet();
            DLList<Country> mhsList = mhs.toDLList();
            for (int i=0; i<mhsList.size(); i++) {
                Country key = mhsList.get(i);
                hashset.add(key);
                for (int j=0; j<loadTo.get(key).size();j++) {
                    hashtab.put(key,loadTo.get(key).get(j));
                }
            }
            fins.close();
            in.close();
        } catch (FileNotFoundException ex) {
            try {
                Scanner scan = new Scanner(new FileReader("countries.txt"));
                String res;
                while(scan.hasNextLine()) {
                    res = scan.nextLine();
                    String a = res.substring(0,res.indexOf(","));
                    String b = res.substring(res.indexOf(",")+1,res.length());
                    hashtab.put(new Country(a,b),null);
                    hashset.add(new Country(a,b));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            hashtab.put(new Country("be","Belgium"), new MyImage("https://fullsuitcase.com/wp-content/uploads/2020/08/Belfry-Tower-of-Bruges-Belfort-Brugge-.jpg", "Belfry of Bruges"));
            hashtab.put(new Country("be","Belgium"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/f/fd/Gent_Gravensteen_R01.jpg", "Gravensteen"));
            hashtab.put(new Country("be","Belgium"), new MyImage("https://img.atlasobscura.com/Rl1Kx3QPvMI1F9zV8vfCDOYZrPwsDH1WfTJ1JO6jYoQ/rs:fill:580:580:1/g:ce/q:81/sm:1/scp:1/ar:1/aHR0cHM6Ly9hdGxh/cy1kZXYuczMuYW1h/em9uYXdzLmNvbS91/cGxvYWRzL3BsYWNl/X2ltYWdlcy85ZDdl/NDdkNjRmODc4NzQy/OTg3YTQ0MTFkZmM4/ZTc5OWU2ZDE2YzJl/LmpwZw.jpg", "Atomium"));
            hashtab.put(new Country("co","Colombia"), new MyImage("https://destinationlesstravel.com/wp-content/uploads/2018/06/DSC_0868-2.jpg", "Las Lajas Sanctuary"));
            hashtab.put(new Country("co","Colombia"), new MyImage("https://thehaphazardtraveler.com/wp-content/uploads/2020/02/Zipaquira-Salt-Cathedral-of-Colombia-Main-Nave-768x1024.jpg", "Salt Cathedral"));
            hashtab.put(new Country("co","Colombia"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/3/33/Valle_de_Cocora2.JPG", "Valle del Cocora"));
            hashtab.put(new Country("kh","Cambodia"), new MyImage("https://smarthistory.org/wp-content/uploads/2021/05/Prasat_Bayon_2014-scaled.jpg", "Bayon Temple"));
            hashtab.put(new Country("kh","Cambodia"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Silver_Pagoda.jpg/600px-Silver_Pagoda.jpg", "Silver Pagoda"));
            hashtab.put(new Country("kh","Cambodia"), new MyImage("https://upload.wikimedia.org/wikipedia/commons/1/1e/Le_Palais_Royal_%28Phnom_Penh%29_%286997773481%29.jpg", "Palacio Real de Nom Pen"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String text ="";
        MyHashSet<Country> ct = hashtab.keySet();
        DLList<Country> ctList = ct.toDLList();
        for (int i=0; i<ctList.size(); i++) {
            if (hashtab.get(ctList.get(i)).get(1) != null) {
                int res = hashtab.get(ctList.get(i)).size()-1;
                text += ctList.get(i).toString() + " - " + res + "\n";
            }
        }
        textA = new JTextArea();
        textA.setEditable(false);
        textA.setText(text);
        scrollPlane = new JScrollPane(textA);
		scrollPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPlane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPlane.setBounds(10,10,515,400);
        add(scrollPlane);

        abbreField.setBounds(10,480,150,30);
        add(abbreField);

        abbreButton.setBounds(160,480,150,30);
        add(abbreButton);
        abbreButton.addActionListener(this);

        urlIn.setBounds(10,515,150,30);
        add(urlIn);
       
        landmark.setBounds(10,550,150,30);
        add(landmark);

        addImage.setBounds(160,515,150,30);
        add(addImage);
        addImage.addActionListener(this);

        deleteCur.setBounds(160,550,150,30);
        add(deleteCur);
        deleteCur.addActionListener(this);

        showAll.setBounds(640,10,150,30);
        add(showAll);
        showAll.addActionListener(this);

        nextIm.setBounds(585,10,50,30);
        add(nextIm);
        nextIm.addActionListener(this);

        prevIm.setBounds(530,10,50,30);
        add(prevIm);
        prevIm.addActionListener(this);

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
        if (imageOn > -1) {
            int j=imageOn;
            if (remove == true) {
                hashtab.remove(currentCountry,j);
                remove = false;
                currentCountry = null;
                String text ="";
                MyHashSet<Country> ct = hashtab.keySet();
                DLList<Country> ctList = ct.toDLList();
                for (int i=0; i<ctList.size(); i++) {
                    if (hashtab.get(ctList.get(i)).get(1) != null) {
                        int res = hashtab.get(ctList.get(i)).size()-1;
                        text += ctList.get(i).toString() + " - " + res + "\n";
                    }
                }
                textA.setText(text);
                imageOn = -1;
            } else {
                hashtab.get(currentCountry).get(imageOn).drawImage(g,530, 55);
            }
        }
    }

    public MyHashTable<Country,MyImage> save() {
        return hashtab;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abbreButton) {
            String res = abbreField.getText();
            currentCountry = new Country(res,"");
            currentCountry = hashset.get(currentCountry);
            String text = "";
            text += currentCountry + "\n";
            if (hashtab.get(currentCountry).get(1) == null) {
                text += "Has Images: " + false;
            } else {
                text += "Has Images: " + true;
            }
            if (hashtab.get(currentCountry).get(1) != null) {
                text += "\n" + "Landmark: " + hashtab.get(currentCountry).get(1).getLandmark();
                imageOn = 1;
            }
            textA.setText(text); 
        } else if (e.getSource() == showAll) {
            currentCountry = null;
            String text ="";
            MyHashSet<Country> ct = hashtab.keySet();
            DLList<Country> ctList = ct.toDLList();
            for (int i=0; i<ctList.size(); i++) {
                if (hashtab.get(ctList.get(i)).get(1) != null) {
                    int res = hashtab.get(ctList.get(i)).size()-1;
                    text += ctList.get(i).toString() + " - " + res + "\n";
                }
            }
            textA.setText(text);
            imageOn = -1;
        } else if (e.getSource() == addImage) {
            String abbrev = abbreField.getText();
            String url = urlIn.getText();
            String landcation = landmark.getText();
            MyImage mi = new MyImage(url, landcation);
            currentCountry = new Country(abbrev,"");
            currentCountry = hashset.get(currentCountry);
            hashtab.put(currentCountry, mi);
            String res = abbreField.getText();
            currentCountry = new Country(res,"");
            currentCountry = hashset.get(currentCountry);
            String text = "";
            text += currentCountry + "\n";
            if (hashtab.get(currentCountry).get(1) == null) {
                text += "Has Images: " + false;
            } else {
                text += "Has Images: " + true;
            }
            if (hashtab.get(currentCountry).get(1) != null) {
                text += "\n" + "Landmark: " + hashtab.get(currentCountry).get(1).getLandmark();
                imageOn = 1;
            }
            textA.setText(text); 
        } else if (e.getSource() == nextIm) {
            if (currentCountry != null) {
                if (imageOn < hashtab.get(currentCountry).size()-1) {
                    imageOn++;
                } else {
                    imageOn = 1;
                }
            }

            String text = "";
            text += currentCountry + "\n";
            if (hashtab.get(currentCountry).get(imageOn) == null) {
                text += "Has Images: " + false;
            } else {
                text += "Has Images: " + true;
            }
            if (hashtab.get(currentCountry).get(imageOn) != null) {
                text += "\n" + "Landmark: " + hashtab.get(currentCountry).get(imageOn).getLandmark();
            }
            textA.setText(text);
        } else if (e.getSource() == prevIm) {
            if (currentCountry != null) {
                if (imageOn > 1) {
                    imageOn--;
                } else {
                    imageOn = hashtab.get(currentCountry).size()-1;
                }
            }

            String text = "";
            text += currentCountry + "\n";
            if (hashtab.get(currentCountry).get(imageOn) == null) {
                text += "Has Images: " + false;
            } else {
                text += "Has Images: " + true;
            }
            if (hashtab.get(currentCountry).get(imageOn) != null) {
                text += "\n" + "Landmark: " + hashtab.get(currentCountry).get(imageOn).getLandmark();
            }
            textA.setText(text);
        } else if (e.getSource() == deleteCur) {
            remove = true;
        }
        repaint();
    }

}
