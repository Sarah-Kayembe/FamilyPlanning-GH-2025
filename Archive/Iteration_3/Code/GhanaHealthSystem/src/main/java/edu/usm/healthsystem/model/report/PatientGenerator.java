package edu.usm.healthsystem.model.report;

import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;

import java.util.List;
import java.time.Month;

public class PatientGenerator {

    public static void addInfo(List<String[]> report, Patient patient) {
        // Assuming you want one patient per row.
        String[] row = new String[24]; // Total: S/No, Date, NHIS, CardNo, Name, Status, Sex, Address, Method, FirstUse, Age, Parity + 12 months
        row[0] = "1";  // S/No hardcoded for now — can be incremented in loops.
        row[1] = patient.getDate(); // Registration date
        row[2] = patient.getNhisNumber();
        row[3] = patient.getCardNumber();
        row[4] = patient.getName() + " " +patient.getLastName(); 
        row[5] = patient.getMaritalStatus();
        row[6] = patient.getSex();
        row[7] = patient.getAddress();
        if (patient instanceof  FamilyPlanningPatient) {
            FamilyPlanningPatient familyPlanningPatient = (FamilyPlanningPatient) patient;
            row[8] = familyPlanningPatient.getMethodOfChoice();
            row[9] = familyPlanningPatient.isFirstUse() ? "Y" : "N";
        }
        row[10] = Integer.toString(patient.getAge());
        if (patient instanceof  FamilyPlanningPatient) {
            FamilyPlanningPatient familyPlanningPatient = (FamilyPlanningPatient) patient;
            row[11] = Integer.toString(familyPlanningPatient.getParity());
        }
        report.add(row);
    }

}
