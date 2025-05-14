package app;

import app.server.BlacklistManager;
import app.server.TCPServer;
import app.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        
        BlacklistManager bl = new BlacklistManager("C:\\Users\\Mi Pc\\Desktop\\project_cyber\\cyber_project\\BlackList.txt");
        ServerGUI gui = new ServerGUI();
        TCPServer server = new TCPServer(bl, gui);
        gui.setServer(server);
        gui.setVisible(true);
    }
}
