package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ServerSock {
    public static final int PORT = 8100;
    public List<ClientThread> clientsList = new ArrayList<>();
    public static ThreadPoolExecutor pool;
    public boolean stop = false;
    public static final int second = 1000;
    public static ServerSocket serverSocket;

    public ServerSock() {
        System.out.println("Server is running...");
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(10 * second);
            pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            while (!stop) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, this, clientsList);
                clientsList.add(client);
                System.out.println(client.getSocket() + " has joined.");
                pool.execute(client);
            }
            System.out.println("Server stopped");
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
            stop = true;
            System.out.println("Server shutting down.");
            //System.exit(1);
        }
    }

    public void stopServer() {
        stop = true;
    }

    public static void main(String[] args) {
        ServerSock server = new ServerSock();
    }
}
