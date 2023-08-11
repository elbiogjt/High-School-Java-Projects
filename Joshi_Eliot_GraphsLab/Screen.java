import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

public class Screen extends JPanel implements ActionListener{
    private Graph<Friend> graph = new Graph<Friend>();
    private int id;
    private int mode = 0;; //1 = adminview, 2 = user, 0 is neither(you cant see either)
    private JButton adminView;
    private JButton customerView;
    private JButton logIn;
    private JButton add;
    private JButton remove;
    private JButton logOut;
    private JButton disconnect;
    private JTextField emailField;
    private JTextField nameField;
    private JTextField passField;
    private JTextField idField;
    private JTextField idField1;
    private JTextField idField2;
    private JTextField searchField;
    private JLabel idLabel1;
    private JLabel idLabel2;
    private JButton connect;
    private JLabel emailLabel;
    private JButton search;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextField schoolField;
    private JLabel passLabel;
    private JLabel schoolLabel;
    private JLabel depLabel;
    private JTextArea textA = new JTextArea();
    private JScrollPane scrollPlane = new JScrollPane(textA);
    private String text;
    private Friend current;

    public Screen() {
        id = 1;
        Friend A = new Friend("Benji","MVHS","100025030@mvla.net",id++,"dogdog");
        Friend B = new Friend("Elot","LAHS","10003060@lahs.net",id++,"catcat");
        Friend C = new Friend("Charles","LAHS","100030490@lahs.net",id++,"moose");
        Friend D = new Friend("Kevin","MVHS","100025043@mvla.net",id++,"mouse");
        Friend E = new Friend("Matt","WLHS","mben@wlhs.net",id++,"computer");
        Friend Z = new Friend("Kenny","WLHS","klam@wlhs.net",id++,"phone");
        Friend X = new Friend("Metro","WLHS","bmetro@wlhs.net",id++,"deskeater");
        Friend Y = new Friend("Charles","MVHS","100025244@mvla.net",id++,"comb");
        Friend W = new Friend("Kevin","LAHS","100033490@lahs.net",id++,"abcd");
        graph.add(A);
        graph.add(B);
        graph.add(C);
        graph.add(D);
        graph.add(E);
        graph.add(Z);
        graph.add(X);
        graph.add(Y);
        graph.add(W);
        graph.addEdge(A,B);
        graph.addEdge(A,C);
        graph.addEdge(B,C);
        graph.addEdge(B,D);
        graph.addEdge(C,E);
        graph.addEdge(D,E);
        graph.addEdge(Z,X);
        graph.addEdge(Z,Y);
        graph.addEdge(X,Y);
        graph.addEdge(X,W);

        adminView = new JButton();
        Font font = new Font("Arial", Font.PLAIN, 20);
        adminView.setFont(font);
        adminView.setHorizontalAlignment(SwingConstants.CENTER);
        adminView.setBounds(7, 7, 150, 30);
        adminView.setText("Admin View");
        this.add(adminView);
        adminView.addActionListener(this);

        customerView = new JButton();
        customerView.setFont(font);
        customerView.setHorizontalAlignment(SwingConstants.CENTER);
        customerView.setBounds(7, 42, 150, 30);
        customerView.setText("Customer");
        this.add(customerView);
        customerView.addActionListener(this);

        logIn = new JButton();
        logIn.setFont(font);
        logIn.setHorizontalAlignment(SwingConstants.CENTER);
        logIn.setBounds(7, 112, 150, 30);
        logIn.setText("Log In");
        this.add(logIn);
        logIn.addActionListener(this);

        search = new JButton();
        search.setFont(font);
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.setBounds(7, 462, 150, 30);
        search.setText("Search");
        this.add(search);
        search.addActionListener(this);

        disconnect = new JButton();
        disconnect.setFont(font);
        disconnect.setHorizontalAlignment(SwingConstants.CENTER);
        disconnect.setBounds(7, 357, 150, 30);
        disconnect.setText("Disconnect");
        this.add(disconnect);
        disconnect.addActionListener(this);

        remove = new JButton();
        remove.setFont(font);
        remove.setHorizontalAlignment(SwingConstants.CENTER);
        remove.setBounds(7, 147, 150, 30);
        remove.setText("Remove");
        this.add(remove);
        remove.addActionListener(this);

        logOut = new JButton();
        logOut.setFont(font);
        logOut.setHorizontalAlignment(SwingConstants.CENTER);
        logOut.setBounds(7, 147, 150, 30);
        logOut.setText("Log Out");
        this.add(logOut);
        logOut.addActionListener(this);

        schoolField = new JTextField("");
        schoolField.setFont(font);
        schoolField.setHorizontalAlignment(SwingConstants.CENTER);
        schoolField.setBounds(447, 7, 130, 30);
        this.add(schoolField);

        depLabel = new JLabel();
        depLabel.setFont(font);
        depLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depLabel.setBounds(7, 287, 150, 30);
        depLabel.setText("$");
        this.add(depLabel);

        add = new JButton();
        add.setFont(font);
        add.setHorizontalAlignment(SwingConstants.CENTER);
        add.setBounds(7, 77, 150, 30);
        add.setText("Add");
        this.add(add);
        add.addActionListener(this);

        emailField = new JTextField();
        emailField.setFont(font);
        emailField.setHorizontalAlignment(SwingConstants.CENTER);
        emailField.setBounds(167, 7, 130, 30);
        emailField.setText("");
        this.add(emailField);

        nameField = new JTextField();
        nameField.setFont(font);
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setBounds(307, 7, 130, 30);
        nameField.setText("");
        this.add(nameField);

        idField = new JTextField();
        idField.setFont(font);
        idField.setHorizontalAlignment(SwingConstants.CENTER);
        idField.setBounds(7, 182, 150, 30);
        idField.setText("");
        this.add(idField);

        searchField = new JTextField();
        searchField.setFont(font);
        searchField.setHorizontalAlignment(SwingConstants.CENTER);
        searchField.setBounds(7, 427, 150, 30);
        searchField.setText("");
        this.add(searchField);

        connect = new JButton();
        connect.setFont(font);
        connect.setHorizontalAlignment(SwingConstants.CENTER);
        connect.setBounds(7, 217, 150, 30);
        connect.setText("Connect");
        this.add(connect);
        connect.addActionListener(this);

        idField1 = new JTextField();
        idField1.setFont(font);
        idField1.setHorizontalAlignment(SwingConstants.CENTER);
        idField1.setBounds(7, 252, 150, 30);
        idField1.setText("");
        this.add(idField1);

        idLabel1 = new JLabel();
        idLabel1.setFont(font);
        idLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel1.setBounds(7, 287, 150, 30);
        idLabel1.setText("ID 1");
        this.add(idLabel1);

        idField2 = new JTextField();
        idField2.setFont(font);
        idField2.setHorizontalAlignment(SwingConstants.CENTER);
        idField2.setBounds(7, 322, 150, 30);
        idField2.setText("");
        this.add(idField2);

        idLabel2 = new JLabel();
        idLabel2.setFont(font);
        idLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel2.setBounds(7, 357, 150, 30);
        idLabel2.setText("ID 2");
        this.add(idLabel2);

        emailLabel = new JLabel();
        emailLabel.setFont(font);
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setBounds(167, 43, 130, 30);
        emailLabel.setText("Email");
        this.add(emailLabel);

        schoolLabel = new JLabel();
        schoolLabel.setFont(font);
        schoolLabel.setHorizontalAlignment(SwingConstants.CENTER);
        schoolLabel.setBounds(447, 43, 130, 30);
        schoolLabel.setText("School");
        this.add(schoolLabel);

        nameLabel = new JLabel();
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBounds(307, 43, 130, 30);
        nameLabel.setText("Name");
        this.add(nameLabel);

        idLabel = new JLabel();
        idLabel.setFont(font);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setBounds(7, 217, 150, 30);
        idLabel.setText("ID");
        this.add(idLabel);

        passField = new JTextField();
        passField.setFont(font);
        passField.setHorizontalAlignment(SwingConstants.CENTER);
        passField.setBounds(587, 7, 130, 30);
        passField.setText("");
        this.add(passField);

        passLabel = new JLabel();
        passLabel.setFont(font);
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passLabel.setBounds(587, 42, 130, 30);
        passLabel.setText("Pass");
        this.add(passLabel);

        text = "";
        textA = new JTextArea();
        textA.setEditable(false);
        textA.setText(text);
        scrollPlane = new JScrollPane(textA);
		scrollPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPlane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPlane.setBounds(162,74,550,490);
        add(scrollPlane); 
        setLayout(null);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(30,30,30));
        g.fillRect(0,0,900,900);
        g.setColor(Color.WHITE);
        if (mode == 1) {
            scrollPlane.setBounds(162,74,550,490);
            textA.setVisible(true);
            scrollPlane.setVisible(true);
            searchField.setVisible(false);
            search.setVisible(false);
            passLabel.setVisible(true);
            schoolField.setVisible(true);
            passField.setVisible(true);
            passLabel.setVisible(true);
            nameField.setVisible(true);
            emailField.setVisible(true);
            nameLabel.setVisible(true);
            emailLabel.setVisible(true);
            idLabel.setVisible(true);
            add.setVisible(true);
            remove.setVisible(true);
            connect.setBounds(7, 217, 150, 30);
            logOut.setVisible(false);
            idField.setVisible(true);
            idField1.setVisible(true);
            idField2.setVisible(true);
            idLabel1.setVisible(true);
            idLabel2.setVisible(true);
            disconnect.setVisible(false);
            connect.setVisible(true);
            depLabel.setVisible(false);
            schoolLabel.setVisible(true);
        } else if (mode == 2) {
            scrollPlane.setBounds(162,74,550,490);
            textA.setVisible(true);
            scrollPlane.setVisible(true);
            passLabel.setVisible(false);
            searchField.setVisible(true);
            search.setVisible(true);
            schoolField.setVisible(false);
            passField.setVisible(true);
            passLabel.setVisible(true);
            idLabel.setVisible(true);
            nameField.setVisible(false);
            emailField.setVisible(false);
            nameLabel.setVisible(false);
            emailLabel.setVisible(false);
            disconnect.setVisible(true);
            add.setVisible(false);
            remove.setVisible(false);
            logOut.setVisible(true);
            idField.setVisible(true);
            depLabel.setVisible(false);
            logIn.setText("Log In");
            logIn.setVisible(true);
            schoolLabel.setVisible(false);
            idField1.setVisible(true);
            idField2.setVisible(false);
            idLabel1.setVisible(true);
            idLabel2.setVisible(false);
            connect.setVisible(true);
            connect.setBounds(7, 322, 150, 30);
        } else {
            textA.setVisible(false);
            scrollPlane.setVisible(false);
            passLabel.setVisible(false);
            schoolField.setVisible(false);
            passField.setVisible(false);
            passLabel.setVisible(false);
            searchField.setVisible(false);
            search.setVisible(false);
            nameField.setVisible(false);
            emailField.setVisible(false);
            nameLabel.setVisible(false);
            schoolLabel.setVisible(false);
            emailLabel.setVisible(false);
            add.setVisible(false);
            remove.setVisible(false);
            disconnect.setVisible(false);
            logIn.setVisible(false);
            idLabel.setVisible(false);
            idField.setVisible(false);
            depLabel.setVisible(false);
            logOut.setVisible(false);
            idField1.setVisible(false);
            idField2.setVisible(false);
            idLabel1.setVisible(false);
            idLabel2.setVisible(false);
            connect.setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminView) {
            if (mode != 1) {
                mode = 1;
                for (Friend each: graph.getKeySet()) {
                    each.setType(2);
                }
            } else {
                mode = 0;
                for (Friend each: graph.getKeySet()) {
                    each.setType(0);
                }
            }
            idLabel1.setText("ID 1");
            textA.setText(graph.toString());
        } else if (e.getSource() == customerView) {
            if (mode != 2) {
                mode = 2;
            } else {
                mode = 0;
            }
            for (Friend each: graph.getKeySet()) {
                each.setType(1);
            }
            idLabel1.setText("Other ID");
            textA.setText("");
        } else if (e.getSource() == connect) {
            Friend a,b;
            if (current == null) {
                int id1 = Integer.parseInt(idField1.getText());
                a = new Friend("","","",id1,"");
                int id2 = Integer.parseInt(idField2.getText());
                b = new Friend("","","",id2,"");
            } else {
                a = current;
                int id2 = Integer.parseInt(idField1.getText());
                b = new Friend("","","",id2,"");
            }
            
            graph.addEdge(a,b);
            if (mode == 1) {
                textA.setText(graph.toString());
            } else if (mode == 2) {
                String text = current.toString() + "\n\nFriends:\n";
                DLList<Friend> set = graph.getHashMap().get(current).toDLList();
                for (int j=0; j<set.size(); j++) {
                    text += set.get(j).toString() + "\n";
                }
                textA.setText(text);
            }
        } else if (e.getSource() == add) {
            String name = nameField.getText();
            String school = schoolField.getText();
            String pass = passField.getText();
            String email = emailField.getText();
            Friend newA = new Friend(name,school,email,id++,pass);
            newA.setType(2);
            graph.add(newA);
            text = graph.toString();
            textA.setText(text);
        } else if (e.getSource() == remove) {
            int idG = Integer.parseInt(idField.getText());
            Friend newA = new Friend("","","",idG,"");
            System.out.println(newA);
            if (newA.equals(null)) {} else {
                graph.remove(newA);
            }
            textA.setText(graph.toString());
        } else if (e.getSource() == logOut) {
            mode = 0;
        } else if (e.getSource() == logIn) {
            int idG = Integer.parseInt(idField.getText());
            String pass = passField.getText();
            Friend newA = new Friend("","","",idG,"");
            current = graph.getKeySet().get(newA);
            if (current.getPass().equals(pass)) {
                String text = current.toString() + "\n\nFriends:\n";
                DLList<Friend> set = graph.getHashMap().get(current).toDLList();
                for (int j=0; j<set.size(); j++) {
                    text += set.get(j).toString() + "\n";
                }
                textA.setText(text);
            } else {
                textA.setText("Wrong Pass, TRY AGAIN");
            }
        } else if (e.getSource() == disconnect) {
            if (current != null) {
                Friend a = current;
                int id2 = Integer.parseInt(idField1.getText());
                Friend b = new Friend("","","",id2,"");
                graph.removeEdge(a, b);
                String text = current.toString() + "\n\nFriends:\n";
                DLList<Friend> set = graph.getHashMap().get(current).toDLList();
                for (int j=0; j<set.size(); j++) {
                    text += set.get(j).toString() + "\n";
                }
                textA.setText(text);
            }
        } else if (e.getSource() == search) {
            String name = searchField.getText();
            DLList<Friend> graphset = graph.getKeySet().toDLList();
            String text = "";
            for (int i=0; i<graphset.size(); i++) {
                if (name.equals(graphset.get(i).getName())) {
                    text += graphset.get(i).toString() + "\n";
                }
            }
            if (text.equals("")) {
                text = "None with that name";
            } 
            textA.setText(text);
        }

        if (mode != 2) {
            current = null;
        }
        repaint();
    }

}