package main;

import java.io.*;
import java.net.*;

// probably this will be a weird implementation but I'll do my best

public class ChatClient {
	// first I need to define everything --- 
    private String serverAddress; // this will be local host
    private int port; // I'll use the 10000
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // the constructor
    public ChatClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    // here will be the start function for the client to enter at the server... the server must be running first
    public void start() {
        try {
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to the server at " + serverAddress + ":" + port); //if we successfully connect it will show this message
            
            // creating the BufferReader object to read the server data
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Requesting the user to put their name
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your username: ");
            String username = consoleInput.readLine();
            out.println(username);

            // creating the thread to read the server messages continuously 
            Thread readThread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            String message = in.readLine();
                            System.out.println(message);
                        } catch (IOException e) {
                            break;
                        }
                    }
                    System.out.println("Connection closed.");
                    disconnect();}
            });
            readThread.start();

            while (true) {
                String message = consoleInput.readLine();
                out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // the disconnect function will be here...
    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close(); // do not forget to close the socket Santiago!!!
        } catch (IOException e) {
            System.out.println("Client disconnect error: " + e.getMessage());
        }
    }

    // starting the client connection with the server
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 10000;

        ChatClient client = new ChatClient(serverAddress, port);
        client.start();
    }
}