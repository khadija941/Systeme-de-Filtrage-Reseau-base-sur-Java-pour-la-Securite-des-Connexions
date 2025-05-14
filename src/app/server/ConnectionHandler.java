package app.server;

import java.io.*;
import java.net.*;

public class ConnectionHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'initialisation des flux : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            output.println("Bienvenue sur le serveur TCP !");
            System.out.println("Client connecté : " + clientSocket.getInetAddress());
            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Message du client : " + clientMessage);
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la communication avec le client : " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connexion fermée avec le client : " + clientSocket.getInetAddress());
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
