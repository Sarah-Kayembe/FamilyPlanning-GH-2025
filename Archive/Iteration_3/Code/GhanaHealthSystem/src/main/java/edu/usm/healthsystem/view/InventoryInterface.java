package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryInterface extends JFrame {

    private JLabel backgroundLabel;

    public InventoryInterface() {
        setTitle("Inventory Service");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(MainInterface.WINDOW_WIDTH, MainInterface.WINDOW_HEIGHT);  // Match MainInterface size
        setLocationRelativeTo(null); // Center on screen


        // Set the same background image path as MainInterface

        ImageIcon backgroundImage = new ImageIcon(MainInterface.BACKGROUND_PATH);
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);

        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        ImageIcon addIcon = MainInterface.createResizedIcon("add_icon.png", 70, 80);  // path, width, height
        ImageIcon reportIcon = MainInterface.createResizedIcon("report_icon.png", 80, 80);  //path, width, height

        JButton addTransactionButton = createButton("      Add Transaction", addIcon, new Color(62, 181, 62)); // Green
        JButton generateReportButton = createButton("    Generate Report", reportIcon, new Color(71, 163, 255)); // Blue
        JButton backButton = createButton("    Back", reportIcon, new Color(232, 60, 60)); // Red

        addTransactionButton.addActionListener((ActionEvent e) -> {
            // TODO: Implement logic for adding a transaction
            System.out.println("Add Transaction button clicked");
        });

        generateReportButton.addActionListener((ActionEvent e) -> {
            new ReportController(this).handleGenerateReport();
        });

        backButton.addActionListener((ActionEvent e) -> {
            new MainInterface();
            dispose();
        });

        // Button layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addTransactionButton, gbc);

        gbc.gridy = 1;
        panel.add(generateReportButton, gbc);

        gbc.gridy = 2;
        panel.add(backButton, gbc);

        add(panel);

        setVisible(true);
    }

    private JButton createButton(String text, ImageIcon icon, Color buttonColor) {
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setFocusPainted(false);
        button.setBackground(buttonColor);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(400, 100));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        return button;
    }


}