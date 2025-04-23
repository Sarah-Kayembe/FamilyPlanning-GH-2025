package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;

public class PatientInterface extends JFrame {

    private JLabel backgroundLabel;

    public PatientInterface() {
        setTitle("Patient Interface");
        setSize(MainInterface.WINDOW_WIDTH, MainInterface.WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon(MainInterface.BACKGROUND_PATH);
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);

        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        JLabel label = new JLabel("Patient Interface - Under Construction");
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        setVisible(true);
    }
}