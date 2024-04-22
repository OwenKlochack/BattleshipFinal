package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class BattleshipHomeScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private boolean musicOn = true; // Initial state of music is on

    public BattleshipHomeScreen() {
        setTitle("Aqua Armageddon test 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            // Override paintComponent to draw image background
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load the image
                    BufferedImage backgroundImage = ImageIO.read(new File("res/tiles/HomeScreen.png"));
                    // Draw the image as the background
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(new GridBagLayout());

        // Add title label
        //JLabel titleLabel = new JLabel("AQUA ARMAGEDDON");
        //titleLabel.setForeground(Color.WHITE);
        //titleLabel.setFont(new Font("Rockwell", Font.BOLD,30));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.insets = new Insets(10, 0, 70, 0);
        //panel.add(titleLabel, titleConstraints);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical layout

        JButton playVsAIButton = new JButton("Player vs AI");
        JButton instructionsButton = new JButton("Instructions");
        JButton settingsButton = new JButton("   Settings   ");
        JButton exitButton = new JButton(" Exit Game ");

        // Set alignment and border for buttons
        playVsAIButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playVsAIButton.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50)); // Adjust padding as needed
        instructionsButton.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50)); // Adjust padding as needed
        settingsButton.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50)); // Adjust padding as needed
        exitButton.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50)); // Adjust padding as needed

        // Add action listener to "Player vs AI" button
        playVsAIButton.addActionListener(e -> {
            dispose(); // Close the current JFrame
            // Open the Player vs AI screen
            Main.startGame();
        });

        // Add action listener to "Instructions" button
        instructionsButton.addActionListener(e -> {
            dispose(); // Close the current JFrame
            // Open the instructions screen
            BattleshipInstructions.main(null);
        });

        // Add action listener to "Settings" button
        settingsButton.addActionListener(e -> {
            dispose(); // Close the current JFrame
            // Open the settings screen with the size of the BattleshipHomeScreen window
            new SettingsMenu(musicOn, getSize());
        });

        // Add action listener to exit button
        exitButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Exit Game", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose(); // Close the JFrame
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(playVsAIButton);
        buttonPanel.add(instructionsButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(exitButton);

        // Add button panel to the bottom of the main panel in the very last row
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        panel.add(buttonPanel, gbc);

        add(panel);

        // Set preferred size of the JFrame
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Settings menu class
    private class SettingsMenu extends JFrame {
        private static final long serialVersionUID = 1L;
        private Clip clip;

        public SettingsMenu(boolean musicOn, Dimension screenSize) {
            setTitle("Settings");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        BufferedImage backgroundImage = ImageIO.read(new File("res/tiles/HomeScreen.png"));
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            panel.setLayout(new GridBagLayout());
            panel.setBackground(Color.WHITE);

            // Add checkbox for music toggle
            JCheckBox musicCheckbox = new JCheckBox("Music On");
            musicCheckbox.setSelected(musicOn);
            musicCheckbox.addActionListener(e -> {
                if (musicCheckbox.isSelected()) {
                    // Play music
                    playMusic();
                } else {
                    // Stop music
                    stopMusic();
                }
            });

            // Centering the checkbox
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
            panel.add(musicCheckbox, gbc);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> {
                dispose(); // Close the settings window
                // Return to the main menu
                new BattleshipHomeScreen();
            });

            // Centering the button
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(closeButton, gbc);

            setContentPane(panel);
            setSize(screenSize); // Set size to match the BattleshipHomeScreen window size
            setLocationRelativeTo(null);
            setVisible(true);
        }

        // Method to play music
        private void playMusic() {
            try {
                File audioFile = new File("res/audio/BattleshipAudio.WAV");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                System.out.println("Music is playing...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Method to stop music
        private void stopMusic() {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }
            System.out.println("Music is stopped.");
        }
    }

    public static void main(String[] args) {
        // Create an instance of BattleshipHomeScreen
        BattleshipHomeScreen homeScreen = new BattleshipHomeScreen();
    }
}