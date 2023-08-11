import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
 
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Screen extends JPanel implements ActionListener{
    private ArrayList<MVHS> group = new ArrayList<MVHS>();
    private ArrayList<MVHS> displayedGroup = new ArrayList<MVHS>();
    private JTextField searchBox;
    private JButton finishSearch;
    private JButton filterStudents;
    private JButton filterTeachers;
    private JButton filterAdmins;
    private JButton filterAll;
    private JButton deleteGuy;
    private int guyNum;
    private boolean searchUsed = false;
    
    public Screen() {
        guyNum=-1;
        group.add(new Teacher("Mr. Nguyen"));
        group.add(new Teacher("Mrs. Liu"));
        group.add(new Student("Ayan"));
        group.add(new Student("Elot"));
        group.add(new Admin("Mr. Jimenez"));
        group.add(new Admin("Mr. Al Valdez"));
        for (int i=0; i<group.size(); i++) {
            displayedGroup.add(group.get(i));
        }

        setLayout(null);

        searchBox = new JTextField();
        searchBox.setBounds(50,400,300,30);
        add(searchBox);

        filterStudents = new JButton("Filter Students");
        filterStudents.setBounds(50,50,150,30);
        add(filterStudents);
        filterStudents.addActionListener(this);

        filterTeachers = new JButton("Filter Teachers");
        filterTeachers.setBounds(230,50,150,30);
        add(filterTeachers);
        filterTeachers.addActionListener(this);

        filterAdmins = new JButton("Filter Admins");
        filterAdmins.setBounds(410,50,150,30);
        add(filterAdmins);
        filterAdmins.addActionListener(this);
        
        filterAll = new JButton("All Groups");
        filterAll.setBounds(590,50,150,30);
        add(filterAll);
        filterAll.addActionListener(this);

        finishSearch = new JButton("Search for Name");
        finishSearch.setBounds(50,435,300,30);
        add(finishSearch);
        finishSearch.addActionListener(this);

        deleteGuy = new JButton("Delete Person");
        deleteGuy.setBounds(50,470,300,30);
        add(deleteGuy);
        deleteGuy.addActionListener(this);

        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = 400;
        for (int i=0; i<displayedGroup.size(); i++) {
            g.drawString(displayedGroup.get(i).toString(), 400,w);
            w = 400+(20*(i+1));
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<displayedGroup.size(); i++) {
            displayedGroup.remove(i);
            i--;
        }

        if (e.getSource() == finishSearch) {
            searchUsed = true;
            String searchRes = searchBox.getText();
            for (int i=0; i<group.size(); i++) {
                searchUsed = true;
                if (group.get(i).getName().equals(searchRes)) {
                    displayedGroup.add(group.get(i));
                    guyNum = i;
                }
            }
        } else if (e.getSource() == filterStudents) {
            for(int i=0; i<group.size(); i++) {
                searchUsed = false;
                if (group.get(i).getRole().equals("student")) {
                    displayedGroup.add(group.get(i));
                }
            }
        } else if (e.getSource() == filterTeachers) {
            for(int i=0; i<group.size(); i++) {
                searchUsed = false;
                if (group.get(i).getRole().equals("teacher")) {
                    displayedGroup.add(group.get(i));
                }
            }
        } else if (e.getSource() == filterAdmins) {
            for(int i=0; i<group.size(); i++) {
                searchUsed = false;
                if (group.get(i).getRole().equals("admin")) {
                    displayedGroup.add(group.get(i));
                }
            } 
        } else if (e.getSource() == filterAll) {
            for(int i=0; i<group.size(); i++) {
                searchUsed = false;
                displayedGroup.add(group.get(i));
            }
        } else if (e.getSource() == deleteGuy) {
            if (searchUsed==true) {
                group.remove(guyNum);
            }

            for(int i=0; i<group.size(); i++) {
                displayedGroup.add(group.get(i));
            }
            searchUsed = false;
        }
        repaint();
    }
}