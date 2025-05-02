package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.familyplanning.ContraceptiveMethod;
import edu.usm.healthsystem.model.familyplanning.Item;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.EnumMap;
import java.util.Map;

public class FamilyPlanningPatientForm extends JDialog {

    private boolean confirmed = false;

    private final JTextField medicalHistoryField = new JTextField();
    private final JCheckBox firstUseCheckbox = new JCheckBox("First Time Use?");
    private final JComboBox<String> methodChoiceBox;
    private final JSpinner paritySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
    private final Map<Month, JTextField> usageFields = new EnumMap<>(Month.class);

    public FamilyPlanningPatientForm(Frame owner) {
        super(owner, "Family Planning Patient Form", true);
        setSize(500, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        methodChoiceBox = new JComboBox<>(ContraceptiveMethod.getMethods());
        methodChoiceBox.setSelectedIndex(0);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(createLabeled("Medical History:", medicalHistoryField));
        formPanel.add(firstUseCheckbox);
        formPanel.add(Box.createVerticalStrut(10));

        formPanel.add(createLabeled("Method of Choice:", methodChoiceBox));
        formPanel.add(createLabeled("Parity (# of children):", paritySpinner));
        formPanel.add(Box.createVerticalStrut(10));

        formPanel.add(new JLabel("Monthly Usage (units used per month):"));
        for (Month month : Month.values()) {
            JTextField usageField = new JTextField();
            usageField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
            usageFields.put(month, usageField);
            formPanel.add(createLabeled(month.toString(), usageField));
        }

        JButton confirmButton = new JButton("Save");
        confirmButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        JPanel buttons = new JPanel();
        buttons.add(confirmButton);
        buttons.add(cancelButton);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private JPanel createLabeled(String labelText, Component input) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        panel.add(label, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        return panel;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getMedicalHistory() {
        return medicalHistoryField.getText().trim();
    }

    public boolean isFirstUse() {
        return firstUseCheckbox.isSelected();
    }

    public String getMethodOfChoice() {
        return (String) methodChoiceBox.getSelectedItem();
    }

    public int getParity() {
        return (int) paritySpinner.getValue();
    }

    public Map<Month, Item> getMonthlyUsage() {
        Map<Month, Item> usage = new EnumMap<>(Month.class);
        for (Map.Entry<Month, JTextField> entry : usageFields.entrySet()) {
            try {
                int quantity = Integer.parseInt(entry.getValue().getText().trim());
                usage.put(entry.getKey(), new Item(entry.getKey().toString(), quantity)); // Modify if Item has different constructor
            } catch (NumberFormatException e) {
                usage.put(entry.getKey(), new Item(entry.getKey().toString(), 0));
            }
        }
        return usage;
    }

}