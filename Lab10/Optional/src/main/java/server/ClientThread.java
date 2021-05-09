package server;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientThread extends Thread {
    private final Socket socket;
    private final ServerSock serverSocket;
    private final List<ClientThread> clients;   // conexiunile de pe server
    private String name = null;
    private boolean isLoggedIn = false;

    private final List<String> messageHistoryList = new ArrayList<>();
    private final List<ClientThread> myFriends = new ArrayList<>(10);
    private boolean stop = false;

    public ClientThread(Socket socket, ServerSock serverSocket, List<ClientThread> clients) throws IOException {
        this.socket = socket;
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    public String getClientName() {
        return name;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public List<ClientThread> getMyFriends() {
        return myFriends;
    }

    public ClientThread getSocketByClientName(String name) {
        for (ClientThread clientThread : clients) {
            if (clientThread.getClientName().equals(name))
                return clientThread;
        }
        return null;
    }

    public boolean isRegistered() {
        return this.name != null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run() {
        String answer = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            /*
            preiau in InputStreamReader inputul de la client prin getInputStream()
            inputul il plasez in memorie intr-un BufferReader
             */
            while (!stop) {
                // client → server
                String request = in.readLine(); // am citit cererea in request

                if (request == null || request.isEmpty())
                    continue;

                // afisez mesajul de la client
                System.out.println("CLIENT:" + request);

                String[] args;
                String command;
                String parameters;

                args = request.split(" ", 2);
                command = args[0];
                parameters = "";

                try { parameters = args[1]; }
                catch (ArrayIndexOutOfBoundsException ignored) {}

                System.out.println("command:" + command);
                System.out.println("params:" + parameters);

                boolean block_sending = false;

                switch (command) {
                    case "stop" -> {
                        if (isLoggedIn) {
                            serverSocket.stopServer();

                            answer = "BYE_BYE";
                            notifyAllClients(answer, false);
                            stop = true;
                            clients.clear();
                        } else
                            answer = "NO_ACTION";
                    }
                    case "network" -> {
                        if (isLoggedIn) {
                            ClientGraphNetwork network = new ClientGraphNetwork(clients);
                            List<Graph<ClientThread, DefaultEdge>> connections = network.createConnections();
                            answer = "NETWORK#" + connections.toString();
                        } else
                            answer = "NO_ACTION";
                    }
                    case "send" -> {
                        if (isLoggedIn) {
                            // in acest caz, parameters reprezinta mesajul propriu zis
                            answer = parameters;

                            notifyAllClients(answer, true);
                        } else
                            answer = "NO_ACTION";
                    }
                    case "friend" -> {
                        if (isLoggedIn) {
                            args = parameters.split(" ");
                            boolean isSomeoneMissing = false;
                            for (String name : args) {
                                ClientThread client = getSocketByClientName(name);
                                if (client == null) {
                                    isSomeoneMissing = true;
                                    break;
                                }

                                myFriends.add(client);
                            }
                            if (isSomeoneMissing) {
                                answer = "SOMEONE_IS_MISSING";
                                // chiar daca a fost cineva adaugat, golesc lista de prieteni
                                myFriends.clear();
                            } else
                                answer = "FRIENDS_DONE";
                        } else
                            answer = "NO_ACTION";
                    }
                    case "register" -> {
                        if (!parameters.equals("")) {
                            boolean is_another = false;

                            if (isRegistered()) {
                                for (ClientThread clientThread : clients) {
                                    if (clientThread.getClientName().equals(parameters)) {
                                        answer = "SIGNED_UP_FAIL";
                                        is_another = true;
                                        break;
                                    }
                                }
                            }

                            if (!is_another) {
                                if (isRegistered())
                                    answer = "ALREADY_REGISTRED";
                                else {
                                    answer = "SIGNED_UP";
                                    this.name = parameters;
                                }
                            }
                        } else {
                            answer = "NONAME";
                        }
                    }
                    case "login" -> {
                        if (!isRegistered())
                            answer = "NO_REGISTER";
                        else
                            answer = parameters.equals("") ? "NONAME" : this.name.equals(parameters) ? "SIGNED_IN" : "SIGNED_IN_FAIL";

                        if (isLoggedIn)
                            answer = "ALREADY_LOGGEDIN";
                        else
                            isLoggedIn = answer.equals("SIGNED_IN");
                    }
                    case "read" -> {
                        if (isLoggedIn) {
                            out.println("READ#" + messageHistoryList.size());
                            for (String message : messageHistoryList) {
                                out.println(message);
                                out.flush();
                            }

                            block_sending = true;
                        } else
                            answer = "NO_ACTION";
                    }
                    default -> answer = "DEFAULT";
                }

                // adaug in istoric mesajele
                messageHistoryList.add(request);

                if (!block_sending) {
                    // trimit mesajul la acest client
                    out.println(answer);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        }

        // s-a deconectat ?
        try {
            socket.close();

            // il stergem din lista
            for (ClientThread clientThread : clients) {
                if (clientThread.getSocket() == this.socket) {
                    clients.remove(this);
                    break;
                }
            }

            // il stergem si de la prieteni
            for (ClientThread friend : myFriends) {
                if (friend.getSocket() == this.socket) {
                    myFriends.remove(this);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        System.out.println("A client has been disconnected!");
    }

    private void notifyAllClients(String answer, boolean friendsOnly) throws IOException {
        if (friendsOnly) {
            for (ClientThread friend : myFriends) {
                if (friend == this)
                    continue;

                // poate intre timp s-a deconectat cineva
                if (friend.getSocket().isClosed())
                    continue;

                PrintWriter out = new PrintWriter(friend.getSocket().getOutputStream());
                out.println(answer);
            }
        } else {
            for (ClientThread client : clients) {
                if (client == this)
                    continue;

                PrintWriter out = new PrintWriter(client.getSocket().getOutputStream());
                out.println(answer);
            }
        }
    }
}