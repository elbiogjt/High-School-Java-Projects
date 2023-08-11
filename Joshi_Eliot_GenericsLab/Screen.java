import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
 
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Screen extends JPanel implements ActionListener{
    private ArrayList<Pair<Student,Schedule>> studentList;
    private ArrayList<Pair<Student,Schedule>> displayedList;
    private ArrayList<Schedule> schedList;
    private ArrayList<BufferedImage> imgs;
    private Student s1,s2,s3;
    private Schedule sched1,sched2,sched3;
    private JTextField searchBox;
    private JTextField newperiod;
    private JTextField newclass;
    private JTextArea searchRes;
    private JButton filterAll;
    private JButton finishSearch;
    private JButton addClass;
    private Font newF;
    private int guyNum = -1;
    private boolean searchUsed = false;
    private BufferedImage john;
    private BufferedImage ayan;
    private BufferedImage omer;
    
    public Screen() {
        studentList = new ArrayList<Pair<Student,Schedule>>();
        displayedList = new ArrayList<Pair<Student,Schedule>>();
        schedList = new ArrayList<Schedule>();
        imgs = new ArrayList<BufferedImage>();
        newF = new Font("serif", Font.BOLD, 20);
        s1= new Student("Omer");
        s2= new Student("Ayan");
        s3= new Student("John");
        sched1 = new Schedule();
        sched2 = new Schedule();
        sched3 = new Schedule();
        sched1.addClass(1,"AP Calc BC");
        sched1.addClass(2,"Adv. Computer Science");
        sched1.addClass(3,"Civics/Econ");
        sched1.addClass(4,"AP Phys C");
        sched1.addClass(5,"Philosophy");
        sched2.addClass(2,"Adv. Computer Science");
        sched2.addClass(3,"Discrete Mathematics");
        sched2.addClass(4,"AP Gov/Macro");
        sched2.addClass(5,"Intermediate weight training");
        sched2.addClass(6,"AP Phys C");
        sched2.addClass(7,"Philosophy");
        sched3.addClass(1,"Lit Cult Soc");
        sched3.addClass(2,"Adv Comp Sci");
        sched3.addClass(3,"AP Psych");
        sched3.addClass(4,"AP Stats");
        sched3.addClass(5,"AP Gov/Macro");
        schedList.add(sched1);
        schedList.add(sched2);
        schedList.add(sched3);

        try {
            ayan = ImageIO.read(new File("ayan.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            omer = ImageIO.read(new File("omer.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            john = ImageIO.read(new File("john.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgs.add(omer);
        imgs.add(ayan);
        imgs.add(john);

        studentList.add(new Pair<Student,Schedule>(s1,sched1));
        studentList.add(new Pair<Student,Schedule>(s2,sched2));
        studentList.add(new Pair<Student,Schedule>(s3,sched3));
        for (int i=0; i<studentList.size(); i++) {
            displayedList.add(studentList.get(i));
        }

        setLayout(null);

        searchBox = new JTextField();
        searchBox.setBounds(50,400,300,30);
        add(searchBox);

        filterAll = new JButton("Show All");
        filterAll.setBounds(590,50,150,30);
        add(filterAll);
        filterAll.addActionListener(this);

        finishSearch = new JButton("Search for Name");
        finishSearch.setBounds(50,435,300,30);
        add(finishSearch);
        finishSearch.addActionListener(this);

        newperiod = new JTextField();
        newperiod.setBounds(590,100,150,30);
        add(newperiod);

        newclass = new JTextField();
        newclass.setBounds(590,135,150,30);
        add(newclass);

        addClass = new JButton("Add Class");
        addClass.setBounds(590,170,150,30);
        add(addClass);
        addClass.addActionListener(this);
        newclass.setVisible(false);
        addClass.setVisible(false);
        newperiod.setVisible(false);

        searchRes = new JTextArea(sched1.toString());
        add(searchRes);
        searchRes.setVisible(false);
        searchRes.setEditable(true);
        searchRes.setBounds(400, 420, 300, 300);

        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,650);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (searchUsed) {
            g.drawString("New Period #:",500,120);
            g.drawString("New Class Name:",490,150);
        }
        g.setFont(newF);
        
        int w = 400;
        for (int i=0; i<displayedList.size(); i++) {
            g.drawString(displayedList.get(i).getObjectT().toString(), 400,w);
            w = 400+(20*(i+1));
        }
        if (guyNum != -1) {
            //g.drawString(displayedList.get(0).getObjectS().toString(),425,w); //heres the problem for some reason
            g.drawImage(imgs.get(guyNum), 50, 50, null);
            searchRes.setText(schedList.get(guyNum).toString());
            searchRes.setVisible(true);
        } else {
            searchRes.setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finishSearch) {
            for (int i=0; i<displayedList.size(); i++) {
                displayedList.remove(i);
                i--;
            }

            searchUsed = true;
            String searchRes = searchBox.getText();
            for (int i=0; i<studentList.size(); i++) {
                searchUsed = true;
                if (studentList.get(i).getObjectT().toString().equals(searchRes)) {
                    displayedList.add(studentList.get(i));
                    guyNum = i;
                    break;
                } else {
                    guyNum = -1;
                }
            }
            newclass.setVisible(true);
            addClass.setVisible(true);
            newperiod.setVisible(true);
        } else if (e.getSource() == filterAll) {
            for (int i=0; i<displayedList.size(); i++) {
                displayedList.remove(i);
                i--;
            }

            for(int i=0; i<studentList.size(); i++) {
                searchUsed = false;
                displayedList.add(studentList.get(i));
                guyNum = -1;
            }
            newclass.setVisible(false);
            addClass.setVisible(false);
            newperiod.setVisible(false);
        } else if (e.getSource() == addClass) {
            if (searchUsed == true) {
                String newpnumS = newperiod.getText();
                int newpnum = Integer.parseInt(newpnumS);
                String newpname = newclass.getText();
                if(guyNum == 0) {
                    sched1.addClass(newpnum,newpname);
                } else if (guyNum == 1) {
                    sched2.addClass(newpnum,newpname);
                } else if (guyNum == 2) {
                    sched3.addClass(newpnum,newpname);
                }
            }
        }
        repaint();
    }
}