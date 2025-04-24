package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class InventoryTransactionOptions extends JDialog {

	private boolean confirmed = false;
    private final JTextField employeeField;
    private final Map<String, JTextField> inputFields = new HashMap<>();
	private static final String[] categories = {
            "LO-FEM", "Overette", "Male Condom", "Female Condom",
            "Copper T", "Micro G", "Micr - N", "Postinor 2",
            "Sampoo", "Depo", "Vasectomy", "LAM",
            "Natural", "Norigynon"
    };
	
	public InventoryTransactionOptions(Frame owner) {
        super(owner, "Add Transaction Options", true); // true makes it modal

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

        JLabel title = new JLabel("Enter actual inventory totals for each method.");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);
        inputPanel.add(title);
        inputPanel.add(Box.createVerticalStrut(20));
        
     // Employee name field
        JLabel empLabel = new JLabel("Employee Name:");
        empLabel.setFont(labelFont);
        empLabel.setForeground(Color.BLACK);
        inputPanel.add(empLabel);

        employeeField = new JTextField();
        employeeField.setFont(fieldFont);
        employeeField.setPreferredSize(new Dimension(250, 30));  // Set the preferred size here
        employeeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputPanel.add(employeeField);
        inputPanel.add(Box.createVerticalStrut(15));
	}
	
	public boolean isConfirmed() {
		return confirmed;
	}
	
	public String getEmployeeName() {
        return employeeField.getText().trim();
    }
}
