package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLEditorKit;

public class BattleshipInstructions {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Online Battleship Instructions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the frame to full screen
            frame.setUndecorated(true); // Remove window decorations
            frame.setLocationRelativeTo(null);

            // Create a custom panel with a background image
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        Image backgroundImage = ImageIO.read(new File("background.jpg")); // Replace "background.jpg" with your image path
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            backgroundPanel.setLayout(new BorderLayout());

            // Create the title label and set its properties
            JLabel titleLabel = new JLabel("Online Battleship Instructions", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Impact", Font.BOLD, 32)); // Set Impact font and bold
            titleLabel.setForeground(Color.BLACK); // Set text color to white
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlePanel.setOpaque(false); // Make the panel transparent
            titlePanel.add(titleLabel);
            backgroundPanel.add(titlePanel, BorderLayout.NORTH);

            JEditorPane textPane = new JEditorPane();
            textPane.setEditable(false);
            textPane.setContentType("text/html"); // Set content type to HTML
            textPane.setFont(new Font("Impact", Font.PLAIN, 18)); // Set Impact font for the text

            String instructions = "<html><body><font color='#FF0000' size='6'><b>Objective:</b></font><br><font size='+2'>" +
                    "The objective of Online Battleship is to sink all of your opponent's ships before they sink yours.<br><br>" +
                    "<font color='#FF0000' size='6'><b>Setup:</b></font><br>" +
                    "1. Each player has a game board with two grids: one to place their ships and another to track their opponent's moves.<br>" +
                    "2. Players can choose to play against the CPU or another human player.<br><br>" +
                    "<font color='#FF0000' size='6'><b>Gameplay vs. CPU:</b></font><br>" +
                    "1. Players take turns calling out grid coordinates (e.g., A3, D7) to target CPU's ships.<br>" +
                    "2. Hits are marked when a shot lands on CPU's ship. Misses occur on empty squares.<br>" +
                    "3. The CPU targets squares on the player's grid after each player's turn.<br><br>" +
                    "<font color='#FF0000' size='6'><b>Gameplay vs. Human Player:</b></font><br>" +
                    "1. Players take turns calling out grid coordinates to target opponent's ships.<br>" +
                    "2. Hits are marked when a shot lands on opponent's ship. Misses occur on empty squares.<br><br>" +
                    "<font color='#FF0000' size='6'><b>Winning:</b></font><br>" +
                    "The player wins by sinking all of their opponent's ships. The opponent wins if they sink all of the player's ships.<br><br>" +
                    "<font color='#FF0000' size='6'><b>Additional Tips:</b></font><br>" +
                    "- Use strategic guessing and deduction to locate and sink opponent's ships efficiently.<br>" +
                    "- Keep track of opponent's previous shots to anticipate their next moves.<br>" +
                    "- Communicate effectively in team play to coordinate attacks if playing in teams.<br></body></html>";

            textPane.setText(instructions);
            JScrollPane scrollPane = new JScrollPane(textPane);

            backgroundPanel.add(scrollPane, BorderLayout.CENTER);

            // Create the return button with custom font
            JButton returnButton = new JButton("Return to Main Screen");
            returnButton.setFont(new Font("Impact", Font.PLAIN, 25)); // Set Impact font for the button text
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                    // Add your return logic here (e.g., close the instructions window and return to main screen)
                    frame.dispose(); // Close the instructions window
                    // Add code to navigate back to the main screen (not included in this example)
                    
                    Main.mainMenu();
                }
            });

            // Create a panel to hold the button at the bottom
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setOpaque(false); // Make the button panel transparent
            buttonPanel.add(returnButton);

            backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

            frame.setContentPane(backgroundPanel); // Set the content pane to the custom background panel
            frame.setVisible(true);
        });
    }
}


