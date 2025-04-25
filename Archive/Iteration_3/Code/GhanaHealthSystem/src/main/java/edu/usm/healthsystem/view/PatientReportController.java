package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;
import edu.usm.healthsystem.model.familyplanning.Item;
import edu.usm.healthsystem.model.report.PatientReport;

import javax.swing.*;
import java.time.Month;
import java.util.Map;

public class PatientReportController {

    private static JFrame parent;

    public PatientReportController(JFrame parent) {
        PatientReportController.parent = parent;
    }

    public static void handleCreateAppointment(FamilyPlanningPatient patient) {
        if (patient == null) {
            JOptionPane.showMessageDialog(parent, "Please select a patient before creating an appointment.");
            return;
        }

        FamilyPlanningPatientForm form = new FamilyPlanningPatientForm(parent);
        form.setVisible(true);

        if (!form.isConfirmed()) return;

        try {
            patient.setMedicalHistory(form.getMedicalHistory());
            patient.setFirstUse(form.isFirstUse());
            patient.setMethodOfChoice(form.getMethodOfChoice());
            patient.setParity(form.getParity());

            Map<Month, Item> usage = form.getMonthlyUsage();
            patient.setMonthlyUsage(usage);

            JOptionPane.showMessageDialog(parent, "Appointment data saved for: " + patient.getName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to save appointment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void handleGenerateReport(FamilyPlanningPatient patient) {
        if (patient == null) {
            JOptionPane.showMessageDialog(parent, "Please select a patient before generating a report.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                parent,
                "Generate a report for " + patient.getName() + " " + patient.getLastName() + "?",
                "Confirm Report Generation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            PatientReport report = new PatientReport();
            report.generate(java.util.List.of(patient));
            JOptionPane.showMessageDialog(parent, "Patient report generated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to generate patient report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}