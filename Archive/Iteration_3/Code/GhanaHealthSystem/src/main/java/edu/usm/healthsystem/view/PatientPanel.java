package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientPanel extends JPanel {

    private JComboBox<Object> patientComboBox;

    public PatientPanel(FamilyPlanningUI parent) {
        setLayout(new BorderLayout());

        List<FamilyPlanningPatient> familyPlanningPatients = Arrays.asList(
                createPatient("John", "Doe"),
                createPatient("Jane", "Smith"),
                createPatient("Alice", "Johnson")
        );

        JLabel backgroundLabel = createBackgroundLabel();
        backgroundLabel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;

        // Title
        JLabel titleLabel = new JLabel("Select a Patient", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridy = 0;
        backgroundLabel.add(titleLabel, gbc);

        // Patient ComboBox with placeholder
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        model.addElement("-- Select a Patient --");
        familyPlanningPatients.forEach(model::addElement);
        patientComboBox = new JComboBox<>(model);
        patientComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        patientComboBox.setPreferredSize(new Dimension(400, 40));
        patientComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof FamilyPlanningPatient p) {
                    setText(p.getName() + " " + p.getLastName());
                } else if (value instanceof String s) {
                    setText(s);
                }
                return this;
            }
        });
        gbc.gridy = 1;
        backgroundLabel.add(patientComboBox, gbc);

        // Create Appointment Button
        ImageIcon icon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);
        JButton createButton = createButton("      Create Appointment", icon, new Color(62, 181, 62));
        createButton.addActionListener(e -> {
            if (getSelectedPatient() == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
            } else {
                System.out.println("Create Appointment for: " + getSelectedPatient().getName());
            }
        });
        gbc.gridy = 2;
        backgroundLabel.add(createButton, gbc);

        // Generate Patient Report Button
        JButton reportButton = createButton(" Generate Patient Report", icon, new Color(60, 130, 200));
        reportButton.addActionListener(e -> {
            if (getSelectedPatient() == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
            } else {
                System.out.println("Generating report for: " + getSelectedPatient().getName());
            }
        });
        gbc.gridy = 3;
        backgroundLabel.add(reportButton, gbc);

        // Back Button
        ImageIcon backIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);
        JButton backButton = createButton("    Back", backIcon, new Color(232, 60, 60));
        backButton.addActionListener(e -> parent.showView("Menu"));
        gbc.gridy = 4;
        backgroundLabel.add(backButton, gbc);

        add(backgroundLabel, BorderLayout.CENTER);
    }

    private FamilyPlanningPatient createPatient(String firstName, String lastName) {
        FamilyPlanningPatient patient = new FamilyPlanningPatient();
        patient.setName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    private JLabel createBackgroundLabel() {
        ImageIcon bg = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = bg.getImage().getScaledInstance(
                FamilyPlanningUI.WINDOW_WIDTH,
                FamilyPlanningUI.WINDOW_HEIGHT,
                Image.SCALE_SMOOTH
        );
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

    public FamilyPlanningPatient getSelectedPatient() {
        Object selected = patientComboBox.getSelectedItem();
        return (selected instanceof FamilyPlanningPatient) ? (FamilyPlanningPatient) selected : null;
    }
}