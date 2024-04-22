package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinScreen {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("You Win!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setUndecorated(true); // Remove window decorations for fullscreen
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window to fullscreen
            frame.setLocationRelativeTo(null);

            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon backgroundImage = new ImageIcon("res/tiles/WinScreen.png");
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            };
            backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

            /*JLabel winLabel = new JLabel("You Win!");
            winLabel.setFont(new Font("Impact", Font.BOLD, 65));
            winLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            winLabel.setForeground(Color.WHITE);

            JLabel savedLabel = new JLabel("You have saved your crew");
            savedLabel.setFont(new Font("Impact", Font.PLAIN, 45));
            savedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            savedLabel.setForeground(Color.WHITE);*/

            JButton returnButton = new JButton("Return to Main Menu");
            returnButton.setFont(new Font("Impact", Font.PLAIN, 18));
            returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    Main.closeGameWindow(); // Close the game window
                    Main.mainMenu();
                    System.out.println("Returning to main menu...");
                }
            });

            backgroundPanel.add(Box.createVerticalGlue());
            //backgroundPanel.add(winLabel);
            //backgroundPanel.add(savedLabel);
            backgroundPanel.add(Box.createVerticalStrut(50));
            backgroundPanel.add(returnButton);
            backgroundPanel.add(Box.createVerticalGlue());

            frame.setContentPane(backgroundPanel);
            frame.setVisible(true);
        });
    }
}


/*package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WinScreen {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("You Win!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Set the frame size
            frame.setLocationRelativeTo(null); // Center the frame on the screen

            // Create the background panel
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Load the background image
                    ImageIcon backgroundImage = new ImageIcon("/Users/franciscohenriques/Desktop/maxresdefault.jpg");
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
                }
            };
            backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

            // Create a label for "You Win!"
            JLabel winLabel = new JLabel("You Win!");
            winLabel.setFont(new Font("Impact", Font.BOLD, 65));
            winLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
            winLabel.setForeground(Color.WHITE); // Set text color

            // Create a label for "You have saved your crew"
            JLabel savedLabel = new JLabel("You have saved your crew");
            savedLabel.setFont(new Font("Impact", Font.PLAIN, 45));
            savedLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
            savedLabel.setForeground(Color.WHITE); // Set text color

            // Create a button for "Return to Main Menu"
            JButton returnButton = new JButton("Return to Main Menu");
            returnButton.setFont(new Font("Impact", Font.PLAIN, 18));
            returnButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your return logic here
                	// Add your return logic here (e.g., close the instructions window and return to main screen)
                    frame.dispose(); // Close the instructions window
                    // Add code to navigate back to the main screen (not included in this example)
                    
                    Main.mainMenu();
                    System.out.println("Returning to main menu...");
                }
            });

            // Add components to the background panel
            backgroundPanel.add(Box.createVerticalGlue()); // Push components to the top
            backgroundPanel.add(winLabel);
            backgroundPanel.add(savedLabel);
            backgroundPanel.add(Box.createVerticalStrut(50)); // Add vertical space
            backgroundPanel.add(returnButton);
            backgroundPanel.add(Box.createVerticalGlue()); // Push components to the bottom

            // Add the background panel to the frame
            frame.setContentPane(backgroundPanel);
            frame.setVisible(true);
      
            // Play the music
            String path = "/Users/franciscohenriques/Music/Music/Media.localized/Music/Unknown Artist/Unknown Album/Music Win BattleShip.wav"; // Modify this to your actual file path
            playMusic(path);
        });
    }

    // Method to play music
    private static void playMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
