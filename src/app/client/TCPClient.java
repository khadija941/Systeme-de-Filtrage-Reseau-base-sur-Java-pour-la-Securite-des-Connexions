package app.client;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; 
        int serverPort = 5000;               

        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la connexion au serveur: " + e.getMessage());
        }
    }
}

