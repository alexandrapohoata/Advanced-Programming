package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

        boolean isLogged = false;

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

            String response = in.readLine();
            System.out.println("Server:" + request);
            switch (response) {
                case "FRIENDS_DONE" -> System.out.println("I added your friends: " + params);
                case "SIGNED_UP_FAIL" -> System.out.println("There is another one called " + params + " connected on server.");
                case "SIGNED_UP" -> System.out.println("You have registered successfully.");
                case "SIGNED_IN" -> {
                    System.out.println("You have signed in successfully.");
                    isLogged = true;
                }
                case "SIGNED_IN_FAIL" -> System.out.println("There is another " + params + " connected on server.");
                case "DEFAULT" -> System.out.println("Server received the request ...");
                case "NONAME" -> System.out.println("Your name is missing..");
                case "STOP" -> {
                    System.out.println("Server stopped.");
                    stop = true;
                }
                default -> {
                    if (response.contains("READ#")) {
                        System.out.println(response);
                        int messages = Integer.parseInt(response.split("#", 2)[1]);
                        System.out.println("\n\nServer messages:");
                        for (int i = 0; i < messages; i++)
                            System.out.println(in.readLine());
                        System.out.println("\n");
                    } else {
                        System.out.println(response);
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
