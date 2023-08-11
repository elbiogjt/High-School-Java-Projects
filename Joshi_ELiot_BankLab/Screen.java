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
    private ArrayList<Account> accts = new ArrayList<Account>();
    private JTextField pinInput;
    private JTextField username;
    private JTextField difNamePin;
    private JButton difPinLive;
    private JButton difNameLive;
    private JButton loginButton;
    private JButton withdrawl;
    private JButton deposit;
    private JTextField depWith;
    private Color lightGrey = new Color(185,185,185);
    private Color darkGrey = new Color(22,22,22);
    private Color white = new Color(0,0,0);
    private Color realWhite = new Color(255,255,255);
    private Font font1 = new Font("Monospaced", Font.PLAIN, 20);
    private JButton signOut;
    private int guy = 0;
    private double balance;
    private boolean badRead;
   
    public Screen(){
        accts.add(new Account("Jennifer", 999.99, 1234));
        accts.add(new Account("Jose", 500.01, 4321));
        accts.add(new Account("Jerry", 50.50, 1111));
        accts.add(new Account("January", 0.01, 0777));
        accts.add(new Account("Jason", 60000.20, 1324));
        
        setLayout(null);
       
        difNamePin = new JTextField();
        difNamePin.setBounds(50,400,100,30);
        add(difNamePin);

        difPinLive = new JButton("Change Pin");
        difPinLive.setBounds(50,435,100,30);
        add(difPinLive);
        difPinLive.addActionListener(this); 
        
        difNameLive = new JButton("ChangeName");
        difNameLive.setBounds(50,470,100,30);
        add(difNameLive);
        difNameLive.addActionListener(this);  

        pinInput = new JTextField();
        pinInput.setBounds(50,250,100,30);
        add(pinInput);

        username = new JTextField();
        username.setBounds(50,200,100,30);
        add(username);
       
        loginButton = new JButton("Login");
        loginButton.setBounds(50,300,100,30);
        add(loginButton);
        loginButton.addActionListener(this);    
       
        depWith = new JTextField();
        depWith.setBounds(600,200,150,30);
        add(depWith);
       
        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(600,300,150,30);
        add(withdrawl);
        withdrawl.addActionListener(this);
       
        deposit = new JButton("Deposit");
        deposit.setBounds(600,250,150,30);
        add(deposit);
        deposit.addActionListener(this);
       
        signOut = new JButton("Sign Out");
        signOut.setBounds(300,500,200,30);
        add(signOut);
        signOut.addActionListener(this);
       
        setFocusable(true);
    }
   
 
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }
   
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(white);
        g.fillRect(0,0,800,600);
        g.setColor(realWhite);
        g.drawString("A T M",740,20);
        g.setColor(lightGrey);
        g.fillRect(25,25,750,550);
        g.setColor(darkGrey);
        g.fillRect(200,350,350,100);
        g.setColor(white);
        g.drawString("PIN",27,270);
        g.drawString("NAME",27,220);
        g.drawString("Change name or pin:",43,390);                 //here
        g.setFont(font1);
        g.setColor(realWhite);
        g.drawString("input card",210,430);
        g.setColor(white);
        g.fillRect(225,375,300,20);
       
        if (guy == 1) {
            g.drawString("Name: " + accts.get(0).getName(), 350,150);
            g.drawString("Balance: " +  balance, 325,200);
        } else if (guy == 2) {
            g.drawString("Name: " + accts.get(1).getName(), 350,150);
            g.drawString("Balance: " +  balance, 325,200);
        } else if (guy == 3) {
            g.drawString("Name: " + accts.get(2).getName(), 350,150);
            g.drawString("Balance: " +  balance, 325,200);
        } else if (guy == 4) {
            g.drawString("Name: " + accts.get(3).getName(), 350,150);
            g.drawString("Balance: " +  balance, 325,200);
        } else if (guy == 5) {
            g.drawString("Name: " + accts.get(4).getName(), 350,150);
            g.drawString("Balance: " +  balance, 325,200);
        } else {
            g.fillRect(0,0,800,800);
            g.setColor(realWhite);
            g.drawString("Welcome! Input name and pin to the left!",150,175);
            for (int i=0;i<accts.size();i++) {
                accts.get(i).disableAccess();
            }
            g.setColor(white);
        }
        
        if (badRead == true) {
            g.drawString("Doesn't work, try again",20,520);
        }
    }
   
   
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == loginButton){
            String pinTxt = pinInput.getText();
            String giveName = username.getText();
            int pin = Integer.parseInt(pinTxt);    
            for (int i=0; i<accts.size(); i++) {
                accts.get(i).checkPin(pin,giveName);
            }
           
            if (accts.get(0).checkPin(pin,giveName) ==  true) {
                guy = 1;
                balance = accts.get(0).getBal();
            } else if (accts.get(1).checkPin(pin,giveName) == true) {
                guy = 2;
                balance = accts.get(1).getBal();
            } else if (accts.get(2).checkPin(pin,giveName) == true) {
                guy = 3;
                balance = accts.get(2).getBal();
            } else if (accts.get(3).checkPin(pin,giveName) == true) {
                guy = 4;
                balance = accts.get(3).getBal();
            } else if (accts.get(4).checkPin(pin,giveName) == true) {
                guy = 5;
                balance = accts.get(4).getBal();
            } else {
                guy = 0;
            }
        }
       
        if (e.getSource() == deposit) {
            String dapWath = depWith.getText();
            int x = Integer.parseInt(dapWath);
           
            if (guy == 1) {
                accts.get(0).deposit(x);
                balance = accts.get(0).getBal();
            } else if (guy == 2) {
                accts.get(1).deposit(x);
                balance = accts.get(1).getBal();
            } else if (guy == 3) {
                accts.get(2).deposit(x);
                balance = accts.get(2).getBal();
            } else if (guy == 4) {
                accts.get(3).deposit(x);
                balance = accts.get(3).getBal();
            } else if (guy == 5) {
                accts.get(4).deposit(x);
                balance = accts.get(4).getBal();
            }
            repaint();
        }
       
        if (e.getSource() == withdrawl) {
            String dapWath = depWith.getText();
            int x = Integer.parseInt(dapWath);
           
            if (guy == 1) {
                accts.get(0).withdraw(x);
                balance = accts.get(0).getBal();
            } else if (guy == 2) {
                accts.get(1).withdraw(x);
                balance = accts.get(1).getBal();
            } else if (guy == 3) {
                accts.get(2).withdraw(x);
                balance = accts.get(2).getBal();
            } else if (guy == 4) {
                accts.get(3).withdraw(x);
                balance = accts.get(3).getBal();
            } else if (guy == 5) {
                accts.get(4).withdraw(x);
                balance = accts.get(4).getBal();
            }
        }

        if(e.getSource() == difNameLive) {
            String changed = difNamePin.getText();
            if (guy==1){
                accts.get(0).changeName(changed);
            } else if (guy==2){
                accts.get(1).changeName(changed);
            } else if (guy==3){
                accts.get(2).changeName(changed);
            } else if (guy==4){
                accts.get(3).changeName(changed);
            } else if (guy==5){
                accts.get(4).changeName(changed);
            }
        }

        if(e.getSource() == difPinLive) {
            String changed = difNamePin.getText();
            int chang = Integer.parseInt(changed);
            if ((chang >= 1000) && (chang <= 9999)) {
                badRead = false;
                if (guy==1){
                    accts.get(0).changePin(chang);
                } else if (guy==2){
                    accts.get(1).changePin(chang);
                } else if (guy==3){
                    accts.get(2).changePin(chang);
                } else if (guy==4){
                    accts.get(3).changePin(chang);
                } else if (guy==5){
                    accts.get(4).changePin(chang);
                }
            } else {
                badRead = true;
            }
        }
       
        if(e.getSource() == signOut) {
            for (int i=0; i<accts.size(); i++) {
                accts.get(i).disableAccess();
            }
            guy = 0;
        }
       
        //refresh
        repaint();
    }
   
}
