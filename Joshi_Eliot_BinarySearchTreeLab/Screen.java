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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Screen extends JPanel implements ActionListener{
    private BinarySearchTree<Account> bst = new BinarySearchTree<Account>();
    private int mode = 0;; //1 = adminview, 2 = customer, 0 is neither(you cant see either)
    private Node<Account> acc;
    private JButton adminView;
    private JButton customerView;
    private JButton search;
    private JButton add;
    private JButton remove;
    private JButton logOut;
    private JButton deposit;
    private JButton withdraw;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField pinField;
    private JTextField withdep;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JLabel pinLabel;
    private JTextField balanceField;
    private JLabel balanceLabel;
    private JLabel depLabel;
    private JTextArea textA = new JTextArea();
    private JScrollPane scrollPlane = new JScrollPane(textA);
    private String text;

    public Screen() {
        try {
            Scanner scan = new Scanner(new FileReader("names.txt"));
            String res = "";
            while(scan.hasNextLine()) {
                res = scan.nextLine();
                String last = res.substring(0,res.indexOf(","));
                String first = res.substring(res.indexOf(",")+1,res.length());
                int balstart = (int)(Math.random()*10000000);
                double bal = (double)(balstart) /100;
                int pin = (int) (Math.random()*9999);
                bst.add(new Account(last,first,pin,bal));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

        search = new JButton();
        search.setFont(font);
        search.setHorizontalAlignment(SwingConstants.CENTER);
        search.setBounds(7, 112, 150, 30);
        search.setText("Search");
        this.add(search);
        search.addActionListener(this);

        add = new JButton();
        add.setFont(font);
        add.setHorizontalAlignment(SwingConstants.CENTER);
        add.setBounds(7, 147, 150, 30);
        add.setText("Add");
        this.add(add);
        add.addActionListener(this);

        logOut = new JButton();
        logOut.setFont(font);
        logOut.setHorizontalAlignment(SwingConstants.CENTER);
        logOut.setBounds(7, 147, 150, 30);
        logOut.setText("Log Out");
        this.add(logOut);
        logOut.addActionListener(this);

        deposit = new JButton();
        deposit.setFont(font);
        deposit.setHorizontalAlignment(SwingConstants.CENTER);
        deposit.setBounds(7, 182, 150, 30);
        deposit.setText("Deposit");
        this.add(deposit);
        deposit.addActionListener(this);

        withdraw = new JButton();
        withdraw.setFont(font);
        withdraw.setHorizontalAlignment(SwingConstants.CENTER);
        withdraw.setBounds(7, 217, 150, 30);
        withdraw.setText("Withdraw");
        this.add(withdraw);
        withdraw.addActionListener(this);

        withdep = new JTextField();
        withdep.setFont(font);
        withdep.setHorizontalAlignment(SwingConstants.CENTER);
        withdep.setBounds(7, 252, 150, 30);
        this.add(withdep);

        depLabel = new JLabel();
        depLabel.setFont(font);
        depLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depLabel.setBounds(7, 287, 150, 30);
        depLabel.setText("$");
        this.add(depLabel);

        remove = new JButton();
        remove.setFont(font);
        remove.setHorizontalAlignment(SwingConstants.CENTER);
        remove.setBounds(7, 77, 150, 30);
        remove.setText("Remove");
        this.add(remove);
        remove.addActionListener(this);

        lastNameField = new JTextField();
        lastNameField.setFont(font);
        lastNameField.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameField.setBounds(167, 7, 130, 30);
        lastNameField.setText("");
        this.add(lastNameField);

        firstNameField = new JTextField();
        firstNameField.setFont(font);
        firstNameField.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameField.setBounds(307, 7, 130, 30);
        firstNameField.setText("");
        this.add(firstNameField);

        pinField = new JTextField();
        pinField.setFont(font);
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        pinField.setBounds(447, 7, 130, 30);
        pinField.setText("");
        this.add(pinField);

        lastNameLabel = new JLabel();
        lastNameLabel.setFont(font);
        lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lastNameLabel.setBounds(167, 43, 130, 30);
        lastNameLabel.setText("LastName");
        this.add(lastNameLabel);

        firstNameLabel = new JLabel();
        firstNameLabel.setFont(font);
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameLabel.setBounds(307, 43, 130, 30);
        firstNameLabel.setText("First Name");
        this.add(firstNameLabel);

        pinLabel = new JLabel();
        pinLabel.setFont(font);
        pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pinLabel.setBounds(445, 42, 130, 30);
        pinLabel.setText("Pin");
        this.add(pinLabel);

        balanceField = new JTextField();
        balanceField.setFont(font);
        balanceField.setHorizontalAlignment(SwingConstants.CENTER);
        balanceField.setBounds(587, 7, 130, 30);
        balanceField.setText("");
        this.add(balanceField);

        balanceLabel = new JLabel();
        balanceLabel.setFont(font);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setBounds(587, 42, 130, 30);
        balanceLabel.setText("Balance");
        this.add(balanceLabel);

        text = bst.toString();
        textA = new JTextArea();
        textA.setEditable(false);
        textA.setText(text);
        scrollPlane = new JScrollPane(textA);
		scrollPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPlane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            pinField.setVisible(true);
            pinLabel.setVisible(true);
            firstNameField.setVisible(true);
            lastNameField.setVisible(true);
            firstNameLabel.setVisible(true);
            lastNameLabel.setVisible(true);
            add.setVisible(true);
            remove.setVisible(true);
            search.setText("Search");
            search.setVisible(true);
            logOut.setVisible(false);
            deposit.setVisible(false);
            withdraw.setVisible(false);
            withdep.setVisible(false);
            depLabel.setVisible(false);
            g.drawString("Passes: " + bst.getPasses(),20,400);
        } else if (mode == 2) {
            scrollPlane.setBounds(162,74,550,40);
            textA.setVisible(true);
            scrollPlane.setVisible(true);
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            pinField.setVisible(true);
            pinLabel.setVisible(true);
            firstNameField.setVisible(true);
            lastNameField.setVisible(true);
            firstNameLabel.setVisible(true);
            lastNameLabel.setVisible(true);
            add.setVisible(false);
            remove.setVisible(false);
            logOut.setVisible(true);
            deposit.setVisible(true);
            withdraw.setVisible(true);
            withdep.setVisible(true);
            depLabel.setVisible(true);
            search.setText("Log In");
            search.setVisible(true);
            g.drawString("Passes: " + bst.getPasses(),20,400);

        } else {
            textA.setVisible(false);
            scrollPlane.setVisible(false);
            balanceLabel.setVisible(false);
            balanceField.setVisible(false);
            pinField.setVisible(false);
            pinLabel.setVisible(false);
            firstNameField.setVisible(false);
            lastNameField.setVisible(false);
            firstNameLabel.setVisible(false);
            lastNameLabel.setVisible(false);
            add.setVisible(false);
            remove.setVisible(false);
            search.setVisible(false);
            deposit.setVisible(false);
            withdraw.setVisible(false);
            withdep.setVisible(false);
            depLabel.setVisible(false);
            logOut.setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminView) {
            textA.setText(bst.toString());
            mode = 1;
        } else if (e.getSource() == customerView) {
            textA.setText("");
            mode = 2;
        } else if (e.getSource() == search) {
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            Account account = new Account(lastName, firstName, 0, 0);
            if (bst.contains(account)) {
                acc = bst.getNode(account);
                textA.setText(acc.get().toStringFull());
            }
        } else if (e.getSource() == add) {
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            int pin = Integer.parseInt(pinField.getText());
            double bal = Double.parseDouble(balanceField.getText());
            Account account = new Account(lastName, firstName, pin, bal);
            bst.add(account);
            text = bst.toString();
            textA.setText(text);
        } else if (e.getSource() == remove) {
            bst.remove(acc.get());
            text = bst.toString();
            textA.setText(text);
        } else if (e.getSource() == logOut) {
            textA.setText("");
            acc = null;
        } else if (e.getSource() == withdraw) {
            double withdraw = Double.parseDouble(withdep.getText());
            Account account = acc.get();
            bst.getNode(account).get().setBal(-withdraw);
            textA.setText(acc.get().toStringFull());
        } else if (e.getSource() == deposit) {
            double deposit = Double.parseDouble(withdep.getText());
            Account account = acc.get();
            bst.getNode(account).get().setBal(deposit);
            textA.setText(acc.get().toStringFull());
        }
        repaint();
    }

}