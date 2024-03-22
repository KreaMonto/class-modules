package main;


import java.io.*;
import java.net.*;
import java.util.*;


// creating the server to connect at...

public class ChatServer {
    private int port;
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    public ChatServer(int port) {
        this.port = port; // 10000
        clients = new ArrayList<>();
    }

    // creating the server session and establishing connection with the clients
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            
            // Looking for new connections
            while (true) { 
                Socket clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                Thread thread = new Thread(client);
                thread.start();
                clients.add(client);
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // I have to define a method to send a message to all the users with the client handler class
    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // starting the server in my default port
    public static void main(String[] args) {
        int port = 10000;
        ChatServer server = new ChatServer(port);
        server.start();
    }
}

// we will need a class to handle all the essential parts here...

class ClientHandler implements Runnable {
    private Socket socket;
    private ChatServer server;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            username = in.readLine();
            server.broadcastMessage(username + " joined the chat.", null);

            while (true) {
                String message = in.readLine();
if (message == null) {
                    break;
                }
                server.broadcastMessage(message, this);
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Client disconnect error: " + e.getMessage());
        }
    }
}
