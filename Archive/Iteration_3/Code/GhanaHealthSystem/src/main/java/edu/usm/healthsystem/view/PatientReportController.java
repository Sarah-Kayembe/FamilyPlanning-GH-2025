package edu.usm.healthsystem.view;

import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;
import edu.usm.healthsystem.model.familyplanning.Item;
import edu.usm.healthsystem.model.report.PatientReport;

import javax.swing.*;
import java.time.Month;
import java.util.Map;
import java.util.List;

public class PatientReportController {

    private static JFrame parent;
    private static PatientPanel patientPanel;

    public PatientReportController(JFrame parent,PatientPanel panel) {
        PatientReportController.parent = parent;
        patientPanel = panel;
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
        List<FamilyPlanningPatient> patients = getPatientList();

        PatientReportOptions dialog = new PatientReportOptions(parent,patient,patients);
        dialog.setVisible(true);
    }

    private static List<FamilyPlanningPatient> getPatientList() {
        return patientPanel.getFamilyPlanningPatients();
    }

}