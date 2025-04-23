package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;

public class MainInterface extends JFrame {

    private JLabel backgroundLabel;

    protected final static int WINDOW_WIDTH = 1200;
    protected final static int WINDOW_HEIGHT = 800;
    protected final static String BACKGROUND_PATH = "images/Background.jpg"; // Needs to be changed to actual image to work.

    public MainInterface() {
        setTitle("Nurse Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set fixed window size (width x height)
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Center on screen

        // Load the resource using the class loader
        URL imageUrl = getClass().getClassLoader().getResource(BACKGROUND_PATH);
        if (imageUrl == null) {
            System.out.println("Image file does not exist at: " + BACKGROUND_PATH);
            return;
        }

        ImageIcon backgroundImage = new ImageIcon(imageUrl);
        Image img = backgroundImage.getImage();
        img = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);

        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        // Load image icons for the buttons
        ImageIcon patientIcon = createResizedIcon("images/patient_icon.png", 70, 80);  // Replace with actual file path
        ImageIcon inventoryIcon = createResizedIcon("images/inventory_icon.png", 80, 80);  // Replace with actual file path
        JButton patientButton = createButton("      Patient Service", patientIcon, new Color(71, 163, 255));  // Darker blue
        JButton inventoryButton = createButton("    Inventory Service", inventoryIcon, new Color(62, 181, 62));  // Muted dark green color

        patientButton.addActionListener((ActionEvent e) -> {
            new PatientInterface();
            dispose(); // Close current window
        });

        inventoryButton.addActionListener((ActionEvent e) -> {
            new InventoryInterface();
            dispose();
        });

        // GridBagLayout for positioning buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(patientButton, gbc);

        gbc.gridy = 1;
        panel.add(inventoryButton, gbc);

        add(panel);

        setVisible(true);
    }

    // Method to resize an ImageIcon to a specific width and height
    public static ImageIcon createResizedIcon(String path, int width, int height) {
        try {
            // Load the resource using the class loader
            URL imageUrl = MainInterface.class.getResource(path);
            if (imageUrl == null) {
                throw new IllegalArgumentException("Resource not found: " + path);
            }
            // Create the ImageIcon from the resource URL
            ImageIcon icon = new ImageIcon(imageUrl);
            // Resize the image
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
            return null; // Or handle the error differently (e.g., return a default icon)
        }
    }

    private JButton createButton(String text, ImageIcon icon, Color buttonColor) {
        JButton button = new JButton(text, icon);
        // Use a softer font like Segoe UI, set it to regular style, and adjust size
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setFocusPainted(false);
        button.setBackground(buttonColor); // Keep original background color
        button.setForeground(Color.BLACK); // Set text color to black
        button.setPreferredSize(new Dimension(400, 100)); // Make the button larger
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainInterface::new);
    }
}