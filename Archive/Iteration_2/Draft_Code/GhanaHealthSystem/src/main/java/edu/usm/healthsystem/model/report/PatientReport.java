package edu.usm.healthsystem.model.report;
import edu.usm.healthsystem.model.client.Client;
import edu.usm.healthsystem.model.client.Patient;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.io.File;

public class PatientReport implements Report {
    public static final int COLUMNS = 16;
    public List<String[]> reportData;

    @Override
    public void generate(Client client) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        File file = new File("paitent_report_" + date + ".csv");
        String filepath = file.getAbsolutePath();
        System.out.println(filepath);

        reportData = generateReportData(client);
        generateCSV(filepath, reportData);
    }

    /**
     * New method: generates report for multiple patients
     */
    public void generate(List<Patient> patients) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        File file = new File("paitent_report_" + date + ".csv");
        String filepath = file.getAbsolutePath();
        System.out.println(filepath);

        // Start report with header
        List<String[]> report = PatientReportHeaderGenerator.addHeader(getYear(), getFacility(), getSubDistrict(), getSubDistrict());

        // Add each patient's data
        for (Patient patient : patients) {
            PatientGenerator.addInfo(report, patient);
        }

        generateCSV(filepath, report);
        setReportData(report);
    }

    public List<String[]> generateReportData(Client client) {
        List<String[]> report = PatientReportHeaderGenerator.addHeader(getYear(), getFacility(), getSubDistrict(), getSubDistrict());

        if (client instanceof Patient) {
            PatientGenerator.addInfo(report, (Patient) client);
        }

        return report;
    }

    public List<String[]> getReportData() {
        return reportData;
    }

    public void setReportData(List<String[]> report) {
        this.reportData = report;
    }

    public void generateCSV(String filePath, List<String[]> data) {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (!fileExists) {
                System.out.println("Creating new file: " + filePath);
            } else {
                System.out.println("Appending to existing file: " + filePath);
                // Optional: skip header if file exists
                if (!data.isEmpty() && data.get(0)[0].contains("FAMILY PLANNING CLIENT REGISTER")) {
                    int firstDataRow = 0;
                    for (int i = 0; i < data.size(); i++) {
                        if (!data.get(i)[0].contains("FAMILY") && !data.get(i)[0].startsWith("-")) {
                            firstDataRow = i;
                            break;
                        }
                    }
                    data = data.subList(firstDataRow, data.size());
                }
            }

            for (String[] row : data) {
                writer.append(String.join(",", row)).append("\n");
            }

            System.out.println("CSV write complete at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error generating CSV file: " + e.getMessage());
        }
    }

    public String getYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return Integer.toString(year);
    }

    public String getDistrict() { return "Ghana NHIS"; }

    public String getClinic() { return "Family Planning Clinic"; }

    public String getFacility() { return "Family Planning"; }

    public String getSubDistrict() { return "Accra West"; }

    public static int parseInt(String s) {
        return (s == null || s.isEmpty()) ? 0 : (int) Double.parseDouble(s);
    }
}
