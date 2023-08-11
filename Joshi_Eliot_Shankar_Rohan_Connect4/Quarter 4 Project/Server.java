import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int portNumber = 1000;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Manager manager = new Manager();
        int n = 0;

        // This loop will run and wait for one connection at a time.
        while (true) {
            System.out.println("Run the while loop: " + n + " times.");
            System.out.println("Waiting for a connection");

            // Wait for a connection.
            Socket clientSocket = serverSocket.accept();

            ServerThread st = new ServerThread(clientSocket, manager);
            Thread newThread = new Thread(st);
            manager.add(newThread, st);
            n++;

            if (n == -1) {
                //manager.broadcast("Start game", null);
                break;
            }
        }
        serverSocket.close();
    }
}