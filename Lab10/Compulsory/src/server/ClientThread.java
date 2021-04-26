package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientThread extends Thread {
    private final Socket socket;
    private final List<ClientThread> clients;
    private PrintWriter out;
    private final ServerSock serverSocket;

    private List<String> messageHistoryList = new ArrayList<>();
    private List<String> myFriends = new ArrayList<>(10);
    private List<String> connectedPeople = new ArrayList<>();
    private boolean stop = false;

    public ClientThread(Socket socket, ServerSock serverSocket, List<ClientThread> clients) throws IOException {
        this.socket = socket;
        this.serverSocket = serverSocket;
        this.clients = clients;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run() {
        String answer = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
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
                        serverSocket.stopServer();

                        if (clients.size() == 1)
                            answer = "STOP";
                        else {
                            notifyAllClients(answer);
                            block_sending = true;
                        }
                        stop = false;
                    }
                    case "send" -> {
                        // in acest caz, parameters reprezinta mesajul propriu zis
                        answer = parameters;

                        if (clients.size() == 1)
                            answer = "DEFAULT";
                        else {
                            notifyAllClients(answer);
                            block_sending = true;
                        }
                    }
                    case "friend" -> {
                        args = parameters.split(" ");
                        Collections.addAll(myFriends, args);
                        answer = "FRIENDS_DONE";
                    }
                    case "register" -> {
                        if (!parameters.equals("")) {
                            boolean is_another = false;
                            for (String name : connectedPeople) {
                                if (name.equals(parameters)) {
                                    answer = "SIGNED_UP_FAIL";
                                    is_another = true;
                                    break;
                                }
                            }
                            if (!is_another)
                                answer = "SIGNED_UP";
                        } else {
                            answer = "NONAME";
                        }

                        connectedPeople.add(parameters);
                    }
                    case "login" -> {
                        if (!parameters.equals("")) {
                            boolean is_another = false;
                            for (String name : connectedPeople) {
                                if (name.equals(parameters)) {
                                    answer = "SIGNED_IN_FAIL";
                                    is_another = true;
                                    break;
                                }
                            }
                            if (!is_another)
                                answer = "SIGNED_IN";
                        } else {
                            answer = "NONAME";
                        }
                    }
                    case "read" -> {
                        out.println("READ#" + messageHistoryList.size());
                        for (String message : messageHistoryList) {
                            out.println(message);
                            out.flush();
                        }

                        block_sending = true;
                    }
                    default -> {
                        answer = "DEFAULT";
                    }
                }

                // adaug in istoric mesajele
                messageHistoryList.add(request);

                if (!block_sending) {
                    // trimit mesajul la clienti
                    out.println(answer);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private void notifyAllClients(String answer) throws IOException {
        for (ClientThread client : clients) {
            client.out.println(answer);
        }
    }
}