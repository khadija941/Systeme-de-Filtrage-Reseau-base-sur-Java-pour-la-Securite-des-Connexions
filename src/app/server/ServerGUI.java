package app.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame {
    private JTextArea textArea;
    private TCPServer server;

    public ServerGUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Serveur TCP");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.DARK_GRAY);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(Color.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pare-feu"));
        add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Lancer le Serveur");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(0, 123, 255));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.addActionListener((ActionEvent e) -> startServer());

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.add(startButton);
        add(panel, BorderLayout.SOUTH);
    }

    public void startServer() {
        if (server != null) {
            appendToTextArea("Démarrage du serveur...\n");
            new Thread(() -> server.startServer()).start();
        } else {
            appendToTextArea("Serveur non initialisé.\n");
        }
    }

    public void appendToTextArea(String message) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(message);
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    public void setServer(TCPServer server) {
        this.server = server;
    }
}

