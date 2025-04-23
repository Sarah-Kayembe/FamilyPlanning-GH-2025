package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(FamilyPlanningUI parent) {
        setLayout(new BorderLayout());

        JLabel backgroundLabel = createBackgroundLabel();
        backgroundLabel.setLayout(new GridBagLayout());

        ImageIcon patientIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/patient_icon.png", 70, 80);
        ImageIcon inventoryIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/inventory_icon.png", 80, 80);

        JButton patientButton = createButton("      Patient Service", patientIcon, new Color(71, 163, 255));
        JButton inventoryButton = createButton("    Inventory Service", inventoryIcon, new Color(62, 181, 62));

        patientButton.addActionListener((ActionEvent e) -> parent.showView("Patient"));
        inventoryButton.addActionListener((ActionEvent e) -> parent.showView("Inventory"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;
        backgroundLabel.add(patientButton, gbc);
        gbc.gridy = 1;
        backgroundLabel.add(inventoryButton, gbc);

        add(backgroundLabel, BorderLayout.CENTER);
    }

    private JLabel createBackgroundLabel() {
        ImageIcon bg = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = bg.getImage().getScaledInstance(FamilyPlanningUI.WINDOW_WIDTH, FamilyPlanningUI.WINDOW_HEIGHT, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private JButton createButton(String text, ImageIcon icon, Color color) {
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(400, 100));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        return button;
    }
}

