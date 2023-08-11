import java.net.*;
import java.io.*;

public class ServerThread implements Runnable {
    private Socket clientSocket;
    private Manager manager;
    private ObjectOutputStream out;

    public ServerThread(Socket clientSocket, Manager manager) {
        this.clientSocket = clientSocket;
        this.manager = manager;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": connection opened.");
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.out.println("error tee hee");
        }
        try {
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            while (true) {
                //break;
                
                broadcast(in.readObject());
                System.out.println("broadcasted message in serverthread");
            }

            // Clears and close the output stream.
            /*out.flush();
            out.close();
            System.out.println(Thread.currentThread().getName() + ": connection closed.");*/
        }

        catch (Exception ex) {
            System.out.println("Error listening for a connection");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void send(Object pair) {
        try {
            out.writeObject(pair);
            out.flush();
        } catch (IOException io) {
            //out.writeObject(pair);
        }
    }

    public void broadcast(Object pair) {
        System.out.println(Thread.currentThread().getName() + ": broadcasting " + pair);
        manager.broadcast(pair, Thread.currentThread());
    }
}