package app.server;

import java.io.*;
import java.util.*;

public class BlacklistManager {

    // Ensemble des IPs bloquées
    public Set<String> blockedIPs;

    // Ensemble des ports bloqués
    public Set<Integer> blockedPorts;

    // Constructeur : lit le fichier dès l'initialisation
    public BlacklistManager(String filename) {
        blockedIPs = new HashSet<>();
        blockedPorts = new HashSet<>();
        loadBlacklist(filename);
    }

    // Lecture du fichier blacklist.txt
    public void loadBlacklist(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Si la ligne commence par "IP:", on ajoute à la liste des IPs
                if (line.startsWith("IP:")) {
                    String ip = line.substring(3).trim(); // ex: "192.168.1.100"
                    blockedIPs.add(ip);
                }
                // Si la ligne commence par "PORT:", on ajoute à la liste des ports
                else if (line.startsWith("PORT:")) {
                    int port = Integer.parseInt(line.substring(5).trim()); // ex: "5001"
                    blockedPorts.add(port);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier blacklist : " + e.getMessage());
        }
    }

    // Vérifie si une IP est bloquée
    public boolean isIPBlocked(String ip) {
        return blockedIPs.contains(ip);
    }

    // Vérifie si un port est bloqué
    public boolean isPortBlocked(int port) {
        return blockedPorts.contains(port);
    }
  
    }

