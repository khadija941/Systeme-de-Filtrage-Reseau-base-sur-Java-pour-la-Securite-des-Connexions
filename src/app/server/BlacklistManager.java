package app.server;

import java.io.*;
import java.util.*;

public class BlacklistManager {

    public Set<String> blockedIPs;
    public Set<Integer> blockedPorts;
    
    public BlacklistManager(String filename) {
        blockedIPs = new HashSet<>();
        blockedPorts = new HashSet<>();
        loadBlacklist(filename);
    }

    public void loadBlacklist(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
               
                if (line.startsWith("IP:")) {
                    String ip = line.substring(3).trim(); 
                    blockedIPs.add(ip);
                }
                
                else if (line.startsWith("PORT:")) {
                    int port = Integer.parseInt(line.substring(5).trim()); 
                    blockedPorts.add(port);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier blacklist : " + e.getMessage());
        }
    }

    public boolean isIPBlocked(String ip) {
        return blockedIPs.contains(ip);
    }

    public boolean isPortBlocked(int port) {
        return blockedPorts.contains(port);
    }
  
    }

