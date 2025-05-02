package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;
import edu.usm.healthsystem.model.report.MonthlyReport;
import edu.usm.healthsystem.model.report.PatientReport;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PatientPanel extends JPanel {

    private JComboBox<Object> patientComboBox;
    private List<FamilyPlanningPatient> familyPlanningPatients;
    private final PatientReportController reportController;
    private DefaultComboBoxModel<Object> model;

    public PatientPanel(FamilyPlanningUI parent) {
        setLayout(new BorderLayout());
        this.reportController = new PatientReportController(parent, this);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        familyPlanningPatients = new ArrayList<>(Arrays.asList(
                createPatient(date, "exampRegNum15263","cardnum2773","John", "Doe","Married", "Male","Chicken Dinner Road",25),
                createPatient(date,"exampRegNum15263","cardnum23873","Jane", "Smith","No Marry", "Female", "Frying Pan Alley",69),
                createPatient(date,"exampRegNum15263","cardnum2263", "Alice", "Johnson","Forever alone","Non Binary","Ha-ha Road",42)
        ));

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
        model = new DefaultComboBoxModel<>();
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
        Object selectedItem = patientComboBox.getSelectedItem();
        gbc.gridy = 1;
        backgroundLabel.add(patientComboBox, gbc);

        // Create Appointment Button
        ImageIcon icon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);
        JButton createButton = createButton("      Create Appointment", null, new Color(62, 181, 62));
        createButton.addActionListener(e -> {
            FamilyPlanningPatient selectedPatient = getSelectedPatient();
            if (selectedPatient == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
            } else {
                PatientReportController.handleCreateAppointment(selectedPatient);
            }
        });
        gbc.gridy = 2;
        backgroundLabel.add(createButton, gbc);

        // Generate Patient Report Button
        ImageIcon reportIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/report_icon.png", 80, 70);
        JButton reportButton = createButton(" Generate Report ", reportIcon, new Color(60, 130, 200));
        reportButton.addActionListener(e -> {
            FamilyPlanningPatient selectedPatient = getSelectedPatient();
            if (selectedPatient == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient first.");
            } else {
                PatientReportController.handleGenerateReport(selectedPatient);
            }
        });
        gbc.gridy = 3;
        backgroundLabel.add(reportButton, gbc);

        //Preview
        ImageIcon previewIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/preview_icon.png", 80, 70);
        JButton previewReportButton = createButton("  Preview Report   ", previewIcon, new Color(255, 193, 7));
        previewReportButton.addActionListener(e -> previewReport());
        gbc.gridy = 4;
        backgroundLabel.add(previewReportButton, gbc);

        // Add New Patient Button
        ImageIcon addPatientIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/add_patient.png", 80, 70);
        JButton addPatientButton = createButton(" Add New Patient ", addPatientIcon, new Color(40, 167, 69));
        addPatientButton.addActionListener(e -> addNewPatient());
        gbc.gridy = 5;  // **Important**: Shift other buttons down if needed
        backgroundLabel.add(addPatientButton, gbc);

        // Delete Selected Patient Button
        ImageIcon deletePatientIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/trash.png", 80, 70);
        JButton deletePatient = createButton("  Delete Patient   ", deletePatientIcon, new Color(220, 53, 69));
        deletePatient.addActionListener(e -> {
            FamilyPlanningPatient selectedPatient = getSelectedPatient();
            if (selectedPatient == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete " + selectedPatient.getName() + " " + selectedPatient.getLastName() + "?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.removeElement(selectedPatient);
                    familyPlanningPatients.remove(selectedPatient);
                    patientComboBox.setSelectedIndex(0); // Reset to placeholder
                }
            }
        });
        gbc.gridy = 6;
        backgroundLabel.add(deletePatient, gbc);

        // Back Button
        ImageIcon backIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);
        JButton backButton = createButton("    Back", backIcon, new Color(232, 60, 60));
        backButton.addActionListener(e -> parent.showView("Menu"));
        gbc.gridy = 7;
        backgroundLabel.add(backButton, gbc);

        add(backgroundLabel, BorderLayout.CENTER);

    }

    private FamilyPlanningPatient createPatient(String date, String NHISreg,
                                                String cardNum, String firstName,
                                                String lastName, String maritalStat,
                                                String sex, String address, int age) {
        FamilyPlanningPatient patient = new FamilyPlanningPatient();
        patient.setDate(date);
        patient.setNhisNumber(NHISreg);
        patient.setCardNumber(cardNum);
        patient.setName(firstName);
        patient.setLastName(lastName);
        patient.setMaritalStatus(maritalStat);
        patient.setSex(sex);
        patient.setAddress(address);
        patient.setAge(age);
        return patient;
    }

    private void previewReport() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "test-output/paitent_report_" + date + "_Multi.csv";
        File file = new File(filepath);

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Report not found:\n" + filepath,
                    "File Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Preview Report", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(1800,800);
        dialog.setLocationRelativeTo(this);

        CSVViewerPanel viewer = new CSVViewerPanel();
        viewer.loadCSV(file);
        dialog.setContentPane(viewer);
        dialog.setVisible(true);
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

    private void addNewPatient() {
        JTextField dateField = new JTextField();
        JTextField NHISregField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField sexField = new JTextField();
        JTextField martialField = new JTextField();
        JTextField clintcardField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField ageField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Clint Card:"));
        panel.add(clintcardField);
        panel.add(new JLabel("NHIS Registration Number:"));
        panel.add(NHISregField);
        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Martial Status:"));
        panel.add(martialField);
        panel.add(new JLabel("Sex (Male/Female/Non Binary):"));
        panel.add(sexField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);



        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Patient",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String date = dateField.getText().trim();
            String NHISreg = NHISregField.getText().trim();
            String Clintcard = clintcardField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String martialstat = martialField.getText().trim();
            String sex = sexField.getText().trim();
            String address = addressField.getText().trim();
            int Age = Integer.parseInt(ageField.getText().trim());

            if (firstName.isEmpty() || lastName.isEmpty() || sex.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            FamilyPlanningPatient newPatient = createPatient(date,NHISreg,Clintcard,firstName,lastName,martialstat,sex,address,Age);
            model.addElement(newPatient);
            familyPlanningPatients.add(newPatient);
            System.out.println("hello");
        }
    }

    public List<FamilyPlanningPatient> getFamilyPlanningPatients() {
        return familyPlanningPatients;
    }
}
