package app.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    private ServerSocket serverSocket;
    private BlacklistManager blacklistManager;
    private ServerGUI gui;

    
    public TCPServer(BlacklistManager blacklistManager, ServerGUI gui) {
        this.blacklistManager = blacklistManager;
        this.gui = gui;
    }

    
    public void startServer() {
        try {
            serverSocket = new ServerSocket(5000);
            gui.appendToTextArea("Serveur en écoute sur le port 5000...\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                InetAddress clientAddress = clientSocket.getInetAddress();
                int clientPort = clientSocket.getPort();

                gui.appendToTextArea("Connexion demandée par : " + clientAddress + ":" + clientPort + "\n");

                if (blacklistManager.isIPBlocked(clientAddress.getHostAddress()) || 
                    blacklistManager.isPortBlocked(clientPort)) {
                    gui.appendToTextArea("Connexion refusée pour " + clientAddress + ":" + clientPort + "\n");
                    clientSocket.close();
                } else {
                    ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
                    new Thread(connectionHandler).start();
                }
            }
        } catch (IOException e) {
            gui.appendToTextArea("Erreur du serveur : " + e.getMessage() + "\n");
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            gui.appendToTextArea("Erreur lors de l'arrêt du serveur : " + e.getMessage() + "\n");
        }
    }
}

