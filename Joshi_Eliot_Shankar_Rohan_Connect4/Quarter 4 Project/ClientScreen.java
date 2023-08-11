import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JTextField;



public class ClientScreen extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private JButton start;
    private JButton confirm;

    private ObjectOutputStream out;
    private MyHashMap<Pair<Integer, Integer>, Integer> board;
    private JTextField rowEnter;

    private boolean usedCol;
    private boolean temporary = true;
    private boolean isStart;
    private boolean playSong;
    private boolean isEnd;
    private boolean isStarting;

    private int xpos;
    private int ypos;

    private JComboBox<String> choiceDisplay;
    private JComboBox<String> columnClear;
    private JComboBox<String> rowClear;
    private JComboBox<String> doubleUp;

    String[] columnYesNo;
    String[] rowYesNo;
    String[] doubleYesNo;
    String[] colorChoices;
    private String playerWon;

    
    private int color;
    private int whosTurn; //equals 1, 2, or 3 depending on which color's turn it is right now
    private int upperBound; //changes based on the number of players, yet to be implemented
    private int hasPowerup;
    private int numTurns;
    private int prevColor;

    public ClientScreen() {
        playerWon = "";
        
        isStart = true;
        isEnd = false;

        playSong = true;
        
        this.setLayout(null);
        this.setFocusable(true);
        usedCol = false;

        hasPowerup = 0;
        numTurns = 0;

        columnYesNo = new String[]{"Use", "Don't Use"};
        rowYesNo = new String[]{"Use", "Don't Use"};
        doubleYesNo = new String[]{"Use", "Don't Use"};

        board = new MyHashMap<Pair<Integer, Integer>, Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.put(new Pair<>(i,j), 0);
            }
        }
        color = 0;
        colorChoices = new String[]{"Red", "Green", "Blue"};

        isStarting = true;

        whosTurn = 1;

        upperBound = 4;

        this.addMouseListener(this);
		this.addMouseMotionListener(this);

        //setting up the row enter textbox
        rowEnter = new JTextField();
        rowEnter.setFont(new Font("Arial", Font.PLAIN, 200));
        rowEnter.setHorizontalAlignment(SwingConstants.LEFT);
        rowEnter.setBounds(500, 60, 100, 30);
        rowEnter.setText("");
		rowEnter.addActionListener(this);
        this.add(rowEnter);

        confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setBounds(500, 100, 100, 30);
		this.add(confirm);

        start = new JButton("START!");
		start.addActionListener(this);
		start.setBounds(100, 100, 100, 30);
		this.add(start);

        choiceDisplay = new JComboBox<>(colorChoices);
        choiceDisplay.addActionListener(this);
        choiceDisplay.setBounds(100, 500, 150, 50);
        this.add(choiceDisplay);

        columnClear = new JComboBox<>(columnYesNo);
        columnClear.addActionListener(this);
        columnClear.setBounds(600, 160, 150, 50);
        this.add(columnClear);
        
        rowClear = new JComboBox<>(rowYesNo);
        rowClear.addActionListener(this);
        rowClear.setBounds(600, 200, 150, 50);
        this.add(rowClear);

        doubleUp = new JComboBox<>(doubleYesNo);
        doubleUp.addActionListener(this);
        doubleUp.setBounds(600, 240, 150, 50);
        this.add(doubleUp);

        columnClear.setSelectedIndex(1);
        rowClear.setSelectedIndex(1);
        doubleUp.setSelectedIndex(1);

        changeMode("start");
        //animate();
    }

    public void paintComponent(Graphics g) {
        //animate();
        String imageName = "";
        if (playerWon.equals("")) {
            if (isStart) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0,0, 1000,700);
                g.setColor(Color.BLACK);
                g.drawString("Click the start button to begin!", 500, 100);
                g.drawString("Place tiles, and try to get 4 in a row before your opponent! ", 500, 120);
                g.drawString("Use special power up tiles for cool effects! ", 500, 140);
                g.drawString("But be careful - you can only use each power-up once!", 500, 160);
            }
            if (!isStart && !isEnd) {
                int mult = 0; //implementing gravity for the connect-4 tiles (since when you remove a row, it needs to sort of fall down)
                if (!usedCol) {
                    int GRID_SIZE = 9;
                    for (int column = 0; column < GRID_SIZE; column++) {
                        for (int row = GRID_SIZE - 2; row >= 0; row--) {
                            Pair<Integer,Integer> currentPos = new Pair<>(column, row);
                            Pair<Integer,Integer> belowPos = new Pair<>(column, row + 1);
                            if (board.get(currentPos) == 0) {
                                continue;
                            }
                            while (row + 1 < GRID_SIZE && board.get(belowPos) == 0) {
                                if (board.get(currentPos) != 0) {
                                    board.put(belowPos, board.get(currentPos));
                                    try {
                                        mult ++;
                                        out.writeObject(new Pair<>(belowPos, board.get(currentPos)));
                                    } catch (IOException e) {
                                        //do nothing lmfao
                                        System.out.println("error 200");
                                    }
                                    board.put(currentPos, 0);
                                    try {
                                        mult ++;
                                        out.writeObject(new Pair<>(currentPos, 0));
                                        
                                    } catch (IOException e) {
                                        //do nothing lmfao
                                        System.out.println("error 209");
                                    }
                                    row++;
                                    currentPos = new Pair<Integer,Integer>(column, row);
                                    belowPos = new Pair<Integer,Integer>(column, row + 1);
                                }
                                
                            }
                        }
                    }
                    if (mult%3 != 0)  {
                        for (int i =0; i < mult%3; i ++) {
                            try {
                                out.writeObject(new Pair<>(new Pair<>(1, 1), 9));
                            } catch (Exception beta) {
                                System.out.println("error 225");
                            }
                            
                        }
                    }
                }
        
                if (columnClear.getSelectedItem().equals("Use")) {
                    rowClear.setSelectedIndex(1);
                    doubleUp.setSelectedIndex(1);
                } else if (rowClear.getSelectedItem().equals("Use")) {
                    columnClear.setSelectedIndex(1);
                    doubleUp.setSelectedIndex(1);
                } else if (doubleUp.getSelectedItem().equals("Use")) {
                    columnClear.setSelectedIndex(1);
                    rowClear.setSelectedIndex(1);
                }
            }
    
            if (!isStart && !isEnd) {
                super.paintComponent(g);
                
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0,0, 1000,700);
                try {
                    Image image = ImageIO.read(getClass().getResource("bg.jpg"));
                    image = image.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
                    g.drawImage(image, 500, 300, null);
                } catch (IOException sendhelp) {
                    System.out.println(sendhelp);
                    sendhelp.printStackTrace();
                }
                for (int i=0; i<9; i++) {
                    for (int j = 0; j < 9; j ++) {
                        int color = board.get(new Pair<>(i, j));
                        if (color == 0) {
                            g.setColor(Color.white);
                        } else if (color == 1) {
                            g.setColor(Color.red);
                        } else if (color == 2) {
                            g.setColor(Color.blue);
                        } else if (color == 3) {
                            g.setColor(Color.green);
                        }
                        g.fillOval(50*(i), 50*(j), 50, 50);
                    }
                }
                g.setColor(Color.BLACK);
                if (!isStarting) {
                    confirm.setVisible(true);
                    rowEnter.setVisible(true);
                } else {
                    confirm.setVisible(false);
                    rowEnter.setVisible(false);
        
                    g.drawString("Column Clear", 500, 190);
                    g.drawString("Row Clear", 500, 230);
                    g.drawString("Double Up", 500, 270);
                }
                if (xpos < 450) {
                    if (ypos < 450) {
                        g.drawRect(xpos - (xpos % 50), 0, 50, 450);
                    }
                }
            }

            if (hasPlayerWon(1)) {
                playerWon = "The RED player has won!";
                imageName = "redwin.png";
                changeMode("end");
                repaint();
            } else if (hasPlayerWon(2)) {
                playerWon = "The BLUE player has won!";
                imageName = "bluewin.png";
                changeMode("end");
                repaint();
            } else if (hasPlayerWon(3)) {
                playerWon = "The GREEN player has won!";
                imageName = "greenwin.png";
                changeMode("end");
                repaint();
            }  
        }
        
        if (!playerWon.equals("")) {
            if (hasPlayerWon(1)) {
                playerWon = "The RED player has won!";
                imageName = "redwin.png";
                changeMode("end");
            } else if (hasPlayerWon(2)) {
                playerWon = "The BLUE player has won!";
                imageName = "bluewin.png";
                changeMode("end");
            } else if (hasPlayerWon(3)) {
                playerWon = "The GREEN player has won!";
                imageName = "greenwin.png";
                changeMode("end");
            }
            repaint();

            if (playSong) {
                try {
                    URL url = this.getClass().getClassLoader().getResource("kanye.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(url));
                    clip.start();
                } catch (Exception exc) {
                    exc.printStackTrace(System.out);
                }
                playSong = false;
            }
            
            try {
                Image image = ImageIO.read(getClass().getResource(imageName));
                image = image.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
                g.drawRect(0,0,2000,800);
                g.drawImage(image, 0, 0, null);
                isEnd = true;
                isStart = false;
                rowEnter.setVisible(false);
                confirm.setVisible(false);
                choiceDisplay.setVisible(false);
                columnClear.setVisible(false);
                rowClear.setVisible(false);
                doubleUp.setVisible(false);
                start.setVisible(false);
                repaint();
            } catch (IOException sendhelp) {
                System.out.println(sendhelp);
                sendhelp.printStackTrace();
            }
            repaint();
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 700);
    }

    public void poll() throws IOException {
        String hostName = "localhost";
        //String hostName = "10.210.115.158";
        int portNumber = 1000;
        Socket serverSocket = new Socket(hostName, portNumber);

        out = new ObjectOutputStream(serverSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

        try {
            while (true) {
                Object inread = in.readObject();
                System.out.println("received object");

                if (inread.equals("Start Game")) {
                    isStarting = false;
                    System.out.println("starting game  ");
                    repaint();
                } else if (inread instanceof Integer) {
                    if ((int) inread == 0) {
                        int GRID_SIZE = 9;
                    } 
                    if ((int)inread == 100) {
                        usedCol = true;
                        repaint(); 
                    }
                } else if (inread instanceof Pair) {
                    System.out.println("pair detected");
                    //reminder: what is received is this: Pair<Pair<Row, Column>, Integer>, the integer is the color
                    Pair<Pair<Integer, Integer>, Integer> temp;
                    //this mfing yellow isnt going awayyyyyyy D:

                    temp = (Pair<Pair<Integer, Integer>, Integer>) inread;
                    if ((int)temp.getValue() == 5) {
                        board.put(temp.getKey(), prevColor);
                        System.out.println("received a weird guy");
                    } else if ((int)temp.getValue() == 0) {
                        board.put(temp.getKey(), 0);
                        System.out.println("received a zero guy");
                    } else if ((int)temp.getValue() == 9) {
                        System.out.println("placeholder received");
                        whosTurn ++;
                        if (whosTurn == 4) {
                            whosTurn = 1;
                        }
                    } else {
                        try {
                            URL url = this.getClass().getClassLoader().getResource("pop.wav");
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(url));
                            clip.start();
                        } catch (Exception exc) {
                            exc.printStackTrace(System.out);
                        }
                        prevColor = (int) temp.getValue();
                        //if ((int)temp.getValue() != 0) {
                            whosTurn ++;
                            if (whosTurn == 4) {
                                whosTurn = 1;
                            }
                        //}
                        board.put(temp.getKey(), prevColor);
                    }
                    repaint();
                } else {
                    break;
                }
                repaint();
            }
        } catch (Exception e) {
            System.err.println("Exception at: " + hostName);
            e.printStackTrace();
            System.exit(1);
        } 
        serverSocket.close();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            changeMode("game");
        }
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        if (ypos < 450 && xpos < 450) {
            for (int i=0; i<9; i++) {
                if (xpos > i*50 && xpos < (i+1)*50) {
                    addTile(i);
                }  
            }
        }
        //(hasPlayerWon(1));
        if (hasPlayerWon(1)) {
            playerWon = "The RED player has won!";
        } else if (hasPlayerWon(2)) {
            playerWon = "The BLUE player has won!";
        } else if (hasPlayerWon(3)) {
            playerWon = "The GREEN player has won!";
        }        
        repaint();  
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {
        xpos = e.getX();
        ypos = e.getY();
        repaint();
    }

    public void addTile(int column) {
        if (playerWon.equals("")) {
            if (choiceDisplay.getSelectedItem().equals("Red")) {
                color = 1;
            } else if (choiceDisplay.getSelectedItem().equals("Green")) {
                color = 3;
            } else if (choiceDisplay.getSelectedItem().equals("Blue")) {
                color = 2;
            }   
            if (color == whosTurn) {
                try {
                    URL url = this.getClass().getClassLoader().getResource("pop.wav");
                    Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(url));
                    clip.start();
                } catch (Exception exc) {
                    exc.printStackTrace(System.out);
                }
                
                for (int i = 8; i >= 0; i --) {
                    if (board.get(new Pair<>(column, i)) == 0) {
                        numTurns ++;
                        if (doubleUp.getSelectedIndex() == 0) {
                            usedCol = false;
                            doubleUp.setVisible(false);
                            doubleUp.setSelectedIndex(1);
                            board.put(new Pair<>(column, i), color);
                            board.put(new Pair<>(column, i-1), color);
                            try {
                                //pls dont break uwu
                                out.writeObject(new Pair<>(new Pair<>(column, i), color));
                                out.writeObject(new Pair<>(new Pair<>(column, i-1), 5));
                            } catch (IOException io) {System.out.println("error 535");}
                            whosTurn ++;
                            if (whosTurn == 4) {
                                whosTurn = 1;
                            }
                        } else if (rowClear.getSelectedIndex() == 0) {
                            usedCol = false;
                            rowClear.setVisible(false);
                            rowClear.setSelectedIndex(1);
                            for (int a = 0; a < 9; a ++) {
                                if (a != column) {
                                    board.put(new Pair<>(a, i), 0);
                                    try {
                                        out.writeObject(new Pair<>(new Pair<>(a, i), 0));
                                    } catch (IOException e) {
                                        //do nothing lmfao
                                        System.out.println("error 555");
                                    }
                                    //clears the row, now the row needs to implement gravity
                                }
                            }
                            board.put(new Pair<>(column, i), color);
                            try {
                                out.writeObject(new Pair<>(new Pair<>(column, i), color));
                            } catch (IOException e) {
                                //do nothing lmfao
                                System.out.println("error 575");
                            }
                            whosTurn ++;
                            if (whosTurn == 4) {
                                whosTurn = 1;
                            }
                            try {
                                out.writeObject(0);
                            } catch (IOException why) {}
                        } else if (columnClear.getSelectedIndex() == 0) {
                            usedCol = true;
                            columnClear.setVisible(false);
                            columnClear.setSelectedIndex(1);
                            try {
                                for (int l = 0; l < 9; l ++) {
                                    out.writeObject(new Pair<>(new Pair<>(column, l), 0));
                                    board.put(new Pair<>(column, l), 0);
                                }
                                board.put(new Pair<>(column, 8), color);
                                out.writeObject(new Pair<>(new Pair<>(column, 8), color));
                                out.writeObject(100);
                            } catch (IOException io) {System.out.println("error 601");}
                            whosTurn ++;
                            if (whosTurn == 4) {
                                whosTurn = 1;
                            }
                        } else {
                            usedCol = false;
                            board.put(new Pair<>(column, i), color);
                            try {
                                out.writeObject(new Pair<>(new Pair<>(column, i), color));
                            } catch (IOException io) {System.out.println("error 628");}
                            whosTurn ++;
                            if (whosTurn == 4) {
                                whosTurn = 1;
                            }
                        }
                        if (numTurns % 2 == 0) {
                            //hasPowerup ++;
                        }
                        break;
                    }
                }
            }
        }
    }

    public boolean hasPlayerWon(int player) {
        // Check rows
        int ROWS = 9;
        int COLS = 9;
        
        //board.get(new Pair<>(r, c))
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board.get(new Pair<>(r, c)) == player && board.get(new Pair<>(r, c+1)) == player && board.get(new Pair<>(r, c+2)) == player && board.get(new Pair<>(r, c+3)) == player) {
                    changeMode("end");
                    return true;
                }
            }
        }
        
        // Check columns
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board.get(new Pair<>(r, c)) == player && board.get(new Pair<>(r+1, c)) == player && board.get(new Pair<>(r+2, c)) == player && board.get(new Pair<>(r+3, c)) == player) {
                    changeMode("end");
                    return true;
                }
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLS - 3; c++) {
                if (board.get(new Pair<>(r, c)) == player && board.get(new Pair<>(r+1, c+1)) == player && board.get(new Pair<>(r+2, c+2)) == player && board.get(new Pair<>(r+3, c+3)) == player) {
                    changeMode("end");
                    return true;
                }
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 3; c < COLS; c++) {
                if (board.get(new Pair<>(r, c)) == player && board.get(new Pair<>(r+1, c-1)) == player && board.get(new Pair<>(r+2, c-2)) == player && board.get(new Pair<>(r+3, c-3)) == player) {
                    changeMode("end");
                    return true;
                }
            }
        }
        return false;
    }

    public void export() {
        for (int row = 0; row < 9; row ++) {
            for (int col = 0; col < 9; col ++) {
                try {
                    out.writeObject(new Pair<>(new Pair<>(row, col), board.get(new Pair<>(row, col))));
                } catch (Exception bogso) {
                    System.out.println("error 699");
                }
            }
        }
        try {
            out.writeObject(new Pair<>(new Pair<>(1, 1), board.get(new Pair<>(1, 1))));
        } catch (Exception bingus) {
            System.out.println("error 705");
        }
    }

    public void changeMode(String mode) {
        if (mode.equals("start")) {
            rowEnter.setVisible(false);
            confirm.setVisible(false);
            choiceDisplay.setVisible(false);
            columnClear.setVisible(false);
            rowClear.setVisible(false);
            doubleUp.setVisible(false);
            start.setVisible(true);
        } else if (mode.equals("end")) {
            isEnd = true;
            isStart = false;
            rowEnter.setVisible(false);
            confirm.setVisible(false);
            choiceDisplay.setVisible(false);
            columnClear.setVisible(false);
            rowClear.setVisible(false);
            doubleUp.setVisible(false);
            start.setVisible(false);
            repaint();
        } else if (mode.equals("game")) {
            isStart = false;
            rowEnter.setVisible(true);
            confirm.setVisible(true);
            choiceDisplay.setVisible(true);
            columnClear.setVisible(true);
            rowClear.setVisible(true);
            doubleUp.setVisible(true);
            start.setVisible(false);
        }
    }

    public void animate() {
        while (true) {
            repaint();
        }
    }
}