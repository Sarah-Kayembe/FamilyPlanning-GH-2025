package edu.usm.healthsystem.model.report;
import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;
import edu.usm.healthsystem.model.report.PatientReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientReportTest {

    Path tempDir = Path.of("test-output");

    // Handel one Patent
    @Test
    public void testGenerateCSV() throws IOException {
       // Ensure tempDir is created (JUnit ensures this with @TempDir)
       Files.createDirectories(tempDir);

       // Setup a mock Patient object
       Patient patient = new FamilyPlanningPatient();
       patient.setName("John");
       patient.setLastName("Doe");
       patient.setAge(30);
       patient.setAddress("172 Mullberry lane");
       patient.setSex("Non Binary");

       // Override generate to use tempDir
       PatientReport patientReport = new PatientReport() {
           @Override
           public void generate(List<Patient> patients) {
               String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
               // Use tempDir correctly
               String filePath = tempDir.resolve("patient_report_" + date + ".csv").toAbsolutePath().toString();
               List<String[]> report = PatientReportHeaderGenerator.addHeader(getYear(), getFacility(), getSubDistrict(), getSubDistrict());
               PatientGenerator.addInfo(report, patient);
               generateCSV(filePath, report);
           }
       };

       // Generate the CSV
       patientReport.generate(List.of(patient));

       // Validate output
       String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
       File expectedFile = tempDir.resolve("patient_report_" + date + ".csv").toFile();
       System.out.println("Looking for file at: " + expectedFile.getAbsolutePath());

       assertTrue(expectedFile.exists(), "CSV file should have been created.");

       // Print and validate contents
       try (BufferedReader br = new BufferedReader(new FileReader(expectedFile))) {
           String line;
           boolean nameFound = false;
           while ((line = br.readLine()) != null) {
               System.out.println(line);
               if (line.contains("John") && line.contains("Doe")) {
                   nameFound = true;
               }
           }
           assertTrue(nameFound, "Patient name should be found in the CSV");
       }
    }
    // Handel Multi Patents
    @Test
    public void testGenerateCSVWithMultiplePatients() throws IOException {
        Files.createDirectories(tempDir);

        Patient patient1 = new FamilyPlanningPatient();
        patient1.setName("John");
        patient1.setLastName("Doe");

        Patient patient2 = new FamilyPlanningPatient();
        patient2.setName("Jane");
        patient2.setLastName("Smith");

        Patient patient3 = new FamilyPlanningPatient();
        patient3.setName("Alice");
        patient3.setLastName("Johnson");

        List<Patient> patients = List.of(patient1, patient2, patient3);

        PatientReport patientReport = new PatientReport() {
            @Override
            public void generate(List<Patient> patients) {
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
                String filePath = tempDir.resolve("paitent_report_" + date + "_Multi.csv").toAbsolutePath().toString();
                List<String[]> report = PatientReportHeaderGenerator.addHeader(getYear(), getFacility(), getSubDistrict(), getSubDistrict());
                for (Patient p : patients) {
                    PatientGenerator.addInfo(report, p);
                }
                generateCSV(filePath, report);
            }
        };

        patientReport.generate(patients);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        File expectedFile = tempDir.resolve("paitent_report_" + date + "_Multi.csv").toFile();
        System.out.println("Looking for file at: " + expectedFile.getAbsolutePath());

        assertTrue(expectedFile.exists(), "CSV file should have been created.");

        boolean johnFound = false;
        boolean janeFound = false;
        boolean aliceFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("John") && line.contains("Doe")) johnFound = true;
                if (line.contains("Jane") && line.contains("Smith")) janeFound = true;
                if (line.contains("Alice") && line.contains("Johnson")) aliceFound = true;
            }
        }

        assertTrue(johnFound, "John Doe should be in the CSV.");
        assertTrue(janeFound, "Jane Smith should be in the CSV.");
        assertTrue(aliceFound, "Alice Johnson should be in the CSV.");
    }
}
