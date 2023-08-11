import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.plaf.synth.SynthScrollBarUI;
 
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener{
    private MyArrayList<Song> group = new MyArrayList<Song>();
    private JButton sortTitle = new JButton("Sort Title");
    private JButton sortArtist = new JButton("Sort Artist");
    private JButton sortAlbum = new JButton("Sort Album");
    private JButton shuffle = new JButton("Shuffle");
    private JButton addSong = new JButton("Add");
    private JButton removeSong = new JButton("Remove");
    private JTextField songTitle = new JTextField();
    private JTextField songArtist = new JTextField();
    private JTextField songAlbum = new JTextField();
    private JTextField urlIn = new JTextField();
    private JTextArea typeArea = new JTextArea();

    public Screen() {
        group.add(new Song("Ghost Town", "Kanye West", "Ye", "https://upload.wikimedia.org/wikipedia/en/thumb/7/74/Ye_album_cover.jpg/220px-Ye_album_cover.jpg"));
        group.add(new Song("No Mistake", "Kanye West", "Ye", "https://upload.wikimedia.org/wikipedia/en/thumb/7/74/Ye_album_cover.jpg/220px-Ye_album_cover.jpg"));
        group.add(new Song("M y . l i f e", "J. Cole", "The Off-Season", "https://images.complex.com/complex/images/c_fill,dpr_auto,f_auto,q_auto,w_1400/fl_lossy,pg_1/kooztyen6ekwytguixn0/tos-cover?fimg-ssr-default"));
        group.add(new Song("Outside", "C. Gambino", "Camp", "https://upload.wikimedia.org/wikipedia/en/thumb/e/e2/Childish-gambino-camp.jpg/220px-Childish-gambino-camp.jpg"));
        group.add(new Song("Savior", "Kendrick Lamar", "Mr. Morale & The Big Steppers", "https://okayplayer-wpengine.netdna-ssl.com/wp-content/uploads/2022/05/kendrick-lamar-mr-morale-and-the-big-steppers-album-cover.jpeg"));
        group.add(new Song("Legacy", "Jay-Z", "4:44", "https://upload.wikimedia.org/wikipedia/commons/a/ac/4-44_album_cover.png"));

        songTitle = new JTextField();
        songTitle.setBounds(10,410,150,30);
        add(songTitle);

        songArtist = new JTextField();
        songArtist.setBounds(10,445,150,30);
        add(songArtist);

        songAlbum = new JTextField();
        songAlbum.setBounds(10,480,150,30);
        add(songAlbum);

        urlIn = new JTextField();
        urlIn.setBounds(10,515,150,30);
        add(urlIn);

        sortTitle.setBounds(590,50,150,30);
        add(sortTitle);
        sortTitle.addActionListener(this);

        sortArtist.setBounds(590,85,150,30);
        add(sortArtist);
        sortArtist.addActionListener(this);

        sortAlbum.setBounds(590,120,150,30);
        add(sortAlbum);
        sortAlbum.addActionListener(this);

        shuffle.setBounds(590,155,150,30);
        add(shuffle);
        shuffle.addActionListener(this);

        addSong.setBounds(10,550,150,30);
        add(addSong);
        addSong.addActionListener(this);

        removeSong.setBounds(10,585,150,30);
        add(removeSong);
        removeSong.addActionListener(this);

        typeArea = new JTextArea(group.toString());
        add(typeArea);
        typeArea.setVisible(true);
        typeArea.setEditable(false);
        typeArea.setBounds(10, 10, 390, 400);

        setLayout(null);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,625);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Title", 165,425);
        g.drawString("Remember to capitalize!", 300, 425);
        g.drawString("Artist",165,460);
        g.drawString("Album",165,495);
        g.drawString("URL",165,530);
        int w=10;
        for (int i=0;i<group.size(); i++) {
            group.get(i).drawAlbumArt(g,410,w);
            w+=45;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sortTitle) {
            Song temp;
            for (int i=0; i<group.size()-1; i++) {
                for (int j=0; j<group.size()-i-1; j++) {
                    if (group.get(j+1).getName().compareTo(group.get(j).getName()) < 0) {
                        temp=group.get(j);
                        group.set(j,group.get(j+1));
                        group.set(j+1,temp);
                    }
                }
            }
        } else if (e.getSource() == sortArtist) {
            Song temp;
            for (int i=0; i<group.size(); i++) {
                for (int j=0; j<group.size()-1-i; j++) {
                    if (group.get(j+1).getArtist().compareTo(group.get(j).getArtist()) < 0) {
                        temp=group.get(j);
                        group.set(j,group.get(j+1));
                        group.set(j+1,temp);
                    }
                }
            }
        } else if (e.getSource() == sortAlbum) {
            Song temp;
            for (int i=0; i<group.size(); i++) {
                for (int j=0; j<group.size()-i-1; j++) {
                    if (group.get(j+1).getAlbum().compareTo(group.get(j).getAlbum()) < 0) {
                        temp=group.get(j);
                        group.set(j,group.get(j+1));
                        group.set(j+1,temp);
                    }
                }
            }
        } else if (e.getSource() == shuffle) {
            Song temp;
            for (int i=0; i<group.size(); i++) {
                int rand = (int)(Math.random()*group.size());
                temp = group.get(i);
                group.set(i,group.get(rand));
                group.set(rand,temp);
            }
        } else if (e.getSource() == addSong) {
            String songT = songTitle.getText();
            String songAr = songArtist.getText();
            String songAl = songAlbum.getText();
            String urllink = urlIn.getText();
            group.add(new Song(songT,songAr,songAl,urllink));
        } else if (e.getSource() == removeSong) {
            String songT = songTitle.getText();
            String songAr = songArtist.getText();
            String songAl = songAlbum.getText();
            for (int i=0; i<group.size(); i++) {
                if (songT.equals(group.get(i).getName())) {
                    if (songAr.equals(group.get(i).getArtist())) {
                        if (songAl.equals(group.get(i).getAlbum())) {
                            group.remove(i);
                        }
                    }
                }
            }
        }
        typeArea.setText(group.toString());
        repaint();
    }
}