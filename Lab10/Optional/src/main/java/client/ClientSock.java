package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientSock {
    public static void main(String[] args) throws IOException {
        boolean stop = false;
        String SERVER_ADDRESS = "127.0.0.1";
        final int PORT = 8100;

        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // buffer pt citire de la tastatura
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        // buffer pt citire din socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socket.setSoTimeout(5000);  // 5 secunde

        boolean isLogged = false;
        boolean isRegistered = false;

        while (true) {
            System.out.print("> ");

            /* COMENZI IMPLEMENTATE:
            login name
            send message
            register name
            friends n1 n2 n3 .. nk
            stop
            read
             */
            String request = keyboard.readLine();
            String params = "";
            try {
                params = request.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException ignored) {}

            if (request.equals(""))
                continue;

            out.println(request);

            String response = null;
            try {
                response = in.readLine();
            } catch (SocketTimeoutException timeoutException) {
                System.out.println("Read timed out -> Close the connection...");
                stop = true;
            } catch (SocketException socketException) {
                stop = true;
            }

            if (response != null) {
                System.out.println("Server:" + request);
                switch (response) {
                    case "BYE_BYE" -> System.out.println("The server is going to close. Type exit or write something :) BYE");
                    case "SOMEONE_IS_MISSING" -> System.out.println("One of your friends is not connected anymore.. Try again.");
                    case "ALREADY_REGISTRED" -> System.out.println("You are already registered.");
                    case "ALREADY_LOGGEDIN" -> System.out.println("You are already logged in.");
                    case "NO_REGISTER" -> System.out.println("You are not registered..");
                    case "FRIENDS_DONE" -> System.out.println("I added your friends: " + params);
                    case "SIGNED_UP_FAIL" -> System.out.println("There is another one called " + params + " connected on server.");
                    case "SIGNED_UP" -> {
                        System.out.println("You have registered successfully.");
                        isRegistered = true;
                    }
                    case "SIGNED_IN" -> {
                        System.out.println("You have signed in successfully.");
                        isLogged = true;
                    }
                    case "SIGNED_IN_FAIL" -> System.out.println("Your name is wrong. Try again.");
                    case "DEFAULT" -> System.out.println("Server received the request ...");
                    case "NONAME" -> System.out.println("Your name is missing..");
                    case "STOP" -> {
                        System.out.println("Server stopped.");
                        stop = true;
                    }
                    case "NO_ACTION" -> System.out.println("You must be logged in if you want to perform this command.");
                    default -> {
                        if (response.contains("READ#")) {
                            //System.out.println(response);
                            int messages = Integer.parseInt(response.split("#", 2)[1]);
                            System.out.println("\nServer messages:");
                            for (int i = 0; i < messages; i++)
                                System.out.println(in.readLine());
                            System.out.println("\n");
                        } else if (response.contains("NETWORK#")) {
                            System.out.println("Current network list with edges between connected clients:");
                            System.out.println(response.split("#", 2)[1]);
                        } else {
                            // orice
                            System.out.println(response);
                        }
                    }
                }
            }

            if (stop)
                break;
        }
        System.out.println("Connection closed by client.");
        socket.close();
    }
}
