package edu.usm.healthsystem.model.report;

import edu.usm.healthsystem.model.client.Patient;
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
        row[8] = patient.getMethodOfChoice();
        row[9] = patient.isFirstUse() ? "Y" : "N";
        row[10] = Integer.toString(patient.getAge());
        row[11] = Integer.toString(patient.getParity());

        // Monthly usage (Jan to Dec)
        // for (int i = 0; i < 12; i++) {
        //     Month month = Month.of(i + 1);
        //     if (patient.getMonthlyUsage() != null && patient.getMonthlyUsage().get(month) != null) {
        //         row[12 + i] = patient.getMonthlyUsage().get(month).getName(); // or .toString() if you prefer full object detail
        //     } else {
        //         row[12 + i] = ""; // Empty if no data
        //     }
        // }

        report.add(row);
    }
}
