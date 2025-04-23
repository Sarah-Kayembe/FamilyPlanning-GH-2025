package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {
    public PatientPanel(FamilyPlanningUI parent) {
        setLayout(new BorderLayout());

        JLabel backgroundLabel = createBackgroundLabel();
        backgroundLabel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Patient Interface - Under Construction", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        backgroundLabel.add(label, BorderLayout.CENTER);

        ImageIcon backIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);
        JButton backButton = createButton("    Back", backIcon, new Color(232, 60, 60));
        backButton.addActionListener(e -> parent.showView("Menu"));

        backgroundLabel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;
        backgroundLabel.add(backButton, gbc);

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
