package edu.usm.healthsystem.model.report;
import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.report.PatientReport;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientReportTest {

    @Test
    public void testGenerateCSV() throws IOException {
        // Setup a mock Patient object
        Patient patient = new Patient();
        patient.setName("John");    
        patient.setLastName("Doe");
        patient.setAge(30);
        patient.setAddress("172 Mullberry lane");
        patient.setSex("Non Binary");
        // Set any other necessary patient data here...

        // Create an instance of the PatientReport class
        PatientReport patientReport = new PatientReport();

        // Generate the CSV
        patientReport.generate(patient);

        // Now, check the generated CSV file (for example, "patient_report_MM_yyyy.csv")
        String date = "04_2025";  // Adjust this depending on the current date
        String filePath = "paitent_report_" + date + ".csv";

        // Print the absolute path for debugging
        java.io.File file = new java.io.File(filePath);
        System.out.println("Looking for file at: " + file.getAbsolutePath());

        // Read the file and print its contents
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);  // Print the contents of the CSV
            }
        }

        // Optionally, you can also assert that certain contents are present in the CSV
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean nameFound = false;
            while ((line = br.readLine()) != null) {
                if (line.contains("John") && line.contains("Doe")) {
                    nameFound = true;
                    break;
                }
            }
            assertTrue(nameFound, "Patient name should be found in the CSV");
        }
    }
}