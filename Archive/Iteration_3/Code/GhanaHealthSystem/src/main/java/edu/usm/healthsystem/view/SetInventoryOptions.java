package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class SetInventoryOptions extends JDialog{

    private boolean confirmed = false;
    private final Map<String, JTextField> inputFields = new HashMap<>();

    private static final String[] categories = {
            "LO-FEM", "Overette", "Male Condom", "Female Condom",
            "Copper T", "Micro G", "Micr - N", "Postinor 2",
            "Sampoo", "Depo", "Vasectomy", "LAM",
            "Natural", "Norigynon"
    };

    public SetInventoryOptions(Frame owner) {
        super(owner, "Generate Report Options", true); // true makes it modal

        setSize(FamilyPlanningUI.WINDOW_WIDTH, FamilyPlanningUI.WINDOW_HEIGHT);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // === Background image ===
        ImageIcon backgroundImage = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false); // Transparent to show background
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);

        JLabel title = new JLabel("Enter the current Inventory Totals.");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);
        inputPanel.add(title);
        inputPanel.add(Box.createVerticalStrut(20));

        // === Category fields in 2-column layout ===
        JPanel categoryPanel = new JPanel(new GridBagLayout());
        categoryPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set column weight to make both columns take equal space
        gbc.weightx = 1.0;
        gbc.gridwidth = 1;

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];

            JLabel label = new JLabel(category + ":");
            label.setFont(labelFont);
            label.setForeground(Color.BLACK);

            JTextField field = new JTextField();
            field.setFont(fieldFont);
            field.setPreferredSize(new Dimension(250, 30));  // Set the preferred size here
            inputFields.put(category, field);

            int row = i / 2;
            int col = (i % 2) * 2;

            gbc.gridx = col;
            gbc.gridy = row;
            categoryPanel.add(label, gbc);

            gbc.gridx = col + 1;
            gbc.gridwidth = 1; // Reset gridwidth to 1 for input field
            categoryPanel.add(field, gbc);
        }

        inputPanel.add(categoryPanel);
        inputPanel.add(Box.createVerticalStrut(20));

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton setBtn = new JButton("Set");
        JButton cancelBtn = new JButton("Cancel");

        setBtn.setFont(labelFont);
        cancelBtn.setFont(labelFont);

        setBtn.addActionListener((ActionEvent e) -> {
            confirmed = true;
            setVisible(false);
        });

        cancelBtn.addActionListener((ActionEvent e) -> {
            confirmed = false;
            setVisible(false);
        });

        buttonPanel.add(setBtn);
        buttonPanel.add(cancelBtn);

        background.add(inputPanel, BorderLayout.CENTER);
        background.add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Map<String, Integer> getData() {
        Map<String, Integer> data = new HashMap<>();
        for (Map.Entry<String, JTextField> entry : inputFields.entrySet()) {
            try {
                int value = Integer.parseInt(entry.getValue().getText().trim());
                data.put(entry.getKey(), value);
            }
            catch (NumberFormatException e) {
                data.put(entry.getKey(), 0); // Default to 0 if invalid field
            }
        }
        return data;
    }
}