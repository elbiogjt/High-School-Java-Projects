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
import javax.swing.JScrollPane;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.ListIterator;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Screen extends JPanel implements ActionListener{
    private MyArrayList<Student> group = new MyArrayList<Student>();
    private JButton binarySearch = new JButton("Binary Search");
    private JButton seqSearch = new JButton("Sequential Search");
    private JButton bubbleSort = new JButton("Bubble Sort");
    private JButton mergeSort = new JButton("Merge Sort");
    private JButton shuffle = new JButton("Shuffle");
    private JTextField lastNam = new JTextField();
    private JTextArea typeArea = new JTextArea();
    private JScrollPane scrollPlane = new JScrollPane(typeArea);
    private boolean result = false;
    private int rounds = 0;
    private int resultLocation = -1;

    public Screen() {
        try {
			Scanner scan = new Scanner(new FileReader("names.txt"));
            String a = "";
            String b = "";
            int counter = 0;
            int countin = 0;
            boolean imp = false;
			while (scan.hasNextLine()){
                if (counter == 0) {
                    a = scan.next();
                    counter++;
                } else if (counter == 1) {
                    b = scan.next();
                    Student temp = new Student(a,b,(int)(Math.random()*4+14));
                    if (countin == 0) {
                        group.add(temp);
                        countin++;
                    } else {
                        binaryInsert(group,0,group.size()-1,temp);
                    }
                    counter = 0;
                }
			}
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        lastNam = new JTextField();
        lastNam.setBounds(10,480,150,30);
        add(lastNam);

        mergeSort.setBounds(590,50,150,30);
        add(mergeSort);
        mergeSort.addActionListener(this);

        bubbleSort.setBounds(590,85,150,30);
        add(bubbleSort);
        bubbleSort.addActionListener(this);

        shuffle.setBounds(590,120,150,30);
        add(shuffle);
        shuffle.addActionListener(this);

        binarySearch.setBounds(10,515,150,30);
        add(binarySearch);
        binarySearch.addActionListener(this);

        seqSearch.setBounds(10,550,150,30);
        add(seqSearch);
        seqSearch.addActionListener(this);

        typeArea = new JTextArea();
        typeArea.setEditable(false);
        typeArea.setText(group.toString());

        scrollPlane = new JScrollPane(typeArea);
		scrollPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPlane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPlane.setBounds(10,10,515,400);
        add(scrollPlane);

        setLayout(null);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Remember to capitalize!", 300, 425);
        g.drawString("Passes: " + rounds,165,530);

        if (result == true) {
            g.drawString("Result: " + group.get(resultLocation).toString(), 165, 545);
            g.drawString("Location: " + resultLocation, 165, 560);
        } else {
            g.drawString("Result: Null",165,545);
            g.drawString("Location: Null", 165,560);
        }
    }

    public void binaryInsert(MyArrayList<Student> groups, int p1, int p2, Student stu) {
        int middle = (p1 + p2) / 2;
        if (p2 < p1) {
            group.add(stu,p1);
        } else if (p2 >= p1) {
            if (groups.get(middle).getLastName().equals(stu.getLastName())) {
                
            } else if (groups.get(middle).getLastName().compareTo(stu.getLastName()) > 0) {
                binaryInsert(groups,p1,middle-1,stu);
            } else {
                binaryInsert(groups,middle+1,p2,stu);
            }
        }
    }

    public void binarySearch(MyArrayList<Student> groups, int p1, int p2, String b) {
        rounds++;
        if (p2 >= p1) {
            int middle = p1+(p2-p1)/2;
            if (groups.get(middle).getLastName().equals(b)) {
                resultLocation = middle;
                System.out.println(resultLocation);
            } else if (groups.get(middle).getLastName().compareTo(b) > 0) {
                binarySearch(groups,p1,middle-1,b);
            } else {
                binarySearch(group,middle+1,p2,b);
            }
        }
    } 

    public void bubbleSort() {
        Student temp;
        rounds = 0;
        for (int i=0; i<group.size()-1; i++) {
            for (int j=0; j<group.size()-i-1; j++) {
                rounds++;  
                if (group.get(j+1).getLastName().compareTo(group.get(j).getLastName()) < 0) {
                    temp=group.get(j);
                    group.set(j,group.get(j+1));
                    group.set(j+1,temp);
                }
            }
        }
    }

    public void sequentialSearch(String lastnameresult) {
        for (int i=0; i<group.size(); i++) {
            rounds++;
            if (group.get(i).getLastName().equals(lastnameresult)) {
                resultLocation = i;
                break;
            }
        }
        repaint();
    }

    public void div(int start, int end) {
        rounds++;
        int middle = (end+start) / 2;
        if (start < end && (end - start) >= 1) {
            div(start,middle);
            div(middle+1,end);
            merge(start,middle,end);
        }
    }

    public void merge(int start, int middle, int end) {
        rounds++;
        MyArrayList<Student> temps = new MyArrayList<Student>();
        int left = start;
        int right = middle + 1;
        while (left <= middle && right <= end) {
            if (group.get(left).getLastName().compareTo(group.get(right).getLastName()) < 0 || group.get(left).getLastName().equals(group.get(right).getLastName())) {
                temps.add(group.get(left));
                left++;
            } else {
                temps.add(group.get(right));
                right++;
            }
        }
        while (left <= middle) {
            temps.add(group.get(left));
            left++;
        }
        while (right <= end) {
            temps.add(group.get(right));
            right++;
        }
        for (int i = 0; i < temps.size(); start++) {
            group.set(start, temps.get(i++));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == binarySearch) {    
            String searchedlastname = lastNam.getText();
            bubbleSort();
            rounds = 0;
            binarySearch(group,0,group.size(),searchedlastname);
            if (resultLocation > -1) {
                result = true;
            }
        } else if (e.getSource() == seqSearch) {
            String searchedlastname = lastNam.getText();
            rounds = 0;
            sequentialSearch(searchedlastname);
            result = true;
        } else if (e.getSource() == bubbleSort) {
            bubbleSort();
            result = false;
        } else if (e.getSource() == shuffle) {
            Student temp;
            for (int i=0; i<group.size(); i++) {
                int rand = (int)(Math.random()*group.size());
                temp = group.get(i);
                group.set(i,group.get(rand));
                group.set(rand,temp);
            }
            result = false;
        } else if (e.getSource() == mergeSort) {
            rounds = 0;
            div(0,group.size()-1);
            result = false;
        }
        typeArea.setText(group.toString());
        repaint();
    }
}