package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;




public class PatientReportOptions extends JDialog {

    private boolean confirmed = false;
    private final Map<String, JTextField> inputFields = new HashMap<>();
    private List<FamilyPlanningPatient> patients;
    private static final String[] categories = {
            "Serial Number", "Date", "NHIS Reg Number", "Client’s Card Number",
            "Name", "Marital Status", "Sex (M/F)", "Address",
            "Contraceptive method", "First ever use method", "Age", "Parity",
            "Months"
    };

    public PatientReportOptions(Frame owner,FamilyPlanningPatient patient, List<FamilyPlanningPatient> patients) {
        super(owner, "Generate Report Options", true);
        this.patients = patients;

        setSize(FamilyPlanningUI.WINDOW_WIDTH, FamilyPlanningUI.WINDOW_HEIGHT);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);

        JLabel title = new JLabel("Edit Patient Information");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);
        inputPanel.add(title);
        inputPanel.add(Box.createVerticalStrut(20));

        JPanel categoryPanel = new JPanel(new GridBagLayout());
        categoryPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Getting the current Date and formatting
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayStr = LocalDate.now().format(dateFormatter);

        for (int i = 0; i < categories.length; i++) {
            String category = categories[i];

            JLabel label = new JLabel(category + ":");
            label.setFont(labelFont);
            label.setForeground(Color.BLACK);
            JTextField field = new JTextField();
            field.setFont(fieldFont);
            field.setPreferredSize(new Dimension(250, 30));
            field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            // Auto-fill and lock the Date field
            if ("Date".equals(category)) {
                field.setText(todayStr);
                field.setEditable(false);
            }

            inputFields.put(category, field);

            int row = i / 2;
            int col = (i % 2) * 2;

            gbc.gridx = col;
            gbc.gridy = row;
            categoryPanel.add(label, gbc);

            gbc.gridx = col + 1;
            categoryPanel.add(field, gbc);
        }

        if (patient != null) {
            inputFields.get("Name").setText(patient.getName() + " " + patient.getLastName());
            inputFields.get("Sex (M/F)").setText(patient.getSex());
        }

        inputPanel.add(categoryPanel);
        inputPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton generateBtn = new JButton("Generate");
        JButton cancelBtn = new JButton("Cancel");
        generateBtn.setFont(labelFont);
        cancelBtn.setFont(labelFont);

        // Generate CSV
        generateBtn.addActionListener((ActionEvent e) -> {
            confirmed = true;
            setVisible(false);

            // File path to save CSV
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
            String filepath = "test-output/paitent_report_" + date + "_Multi.csv";
            File csvFile = new File(filepath);

            try (PrintWriter writer = new PrintWriter(csvFile)) {
                // Write static headers
                writer.println("FAMILY PLANNING CLIENT REGISTER,,,,,,,,,,,,,,,,,,,,,,,");
                writer.println("YEAR:,2025,,,,,,,,,,,,,,,,,,,,,,,");
                writer.println("FACILITY / ZONE:,Family Planning,Sub-district:,Accra West,District:,Accra West,,,,,,,,,,,,,,,,");

                // Write the column headers
                writer.println("S/NO,Date,NHIS Reg. No.,Card No,NAME,Marital Status,Sex,Address (Location/WP/Community/Hse No.),Method of Choice,1st ever use of method (Y/N),Age,Parity,Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec");

                // Write patient rows
                int sno = 1;
                for ( FamilyPlanningPatient fpPatient : patients) {
                    // Write patient data
                    writer.printf("%d,%s,%s,%s,%s,%s,%s,%s,null,null,%s,0", sno, fpPatient.getDate(),fpPatient.
                            getNhisNumber(),fpPatient.getCardNumber(),fpPatient.getName(), fpPatient.
                            getMaritalStatus(), fpPatient.getSex(),fpPatient.getAddress(),fpPatient.getAge());
                    // Fill the rest of the months
                    for (int i = 0; i < 12; i++) {
                        writer.print(",null");
                    }
                    writer.println(); // New line for next patient
                    sno++;
                }

                JOptionPane.showMessageDialog(this, "CSV generated successfully!");

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to generate CSV.");
            }
        });
        cancelBtn.addActionListener((ActionEvent e) -> {
            confirmed = false;
            setVisible(false);
        });

        buttonPanel.add(generateBtn);
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
            } catch (NumberFormatException e) {
                data.put(entry.getKey(), 0);
            }
        }
        return data;
    }
    public Map<String, JTextField> getInputFields() {
        return inputFields;
    }

}
