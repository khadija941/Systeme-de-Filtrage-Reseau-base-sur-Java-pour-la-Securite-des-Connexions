package app;

import app.server.BlacklistManager;
import app.server.TCPServer;
import app.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        // 1. Créer l'instance de BlacklistManager
        BlacklistManager bl = new BlacklistManager("C:\\Users\\Mi Pc\\Desktop\\project_cyber\\cyber_project\\BlackList.txt");

        // 2. Créer l'interface graphique
        ServerGUI gui = new ServerGUI();

        // 3. Créer l'instance de TCPServer et lui passer la référence à l'interface graphique
        TCPServer server = new TCPServer(bl, gui);

        // 4. Passer l'instance de TCPServer à l'interface graphique
        gui.setServer(server);

        // 5. Rendre visible l'interface graphique
        gui.setVisible(true);
    }
}
