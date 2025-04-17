package edu.usm.healthsystem.model.report;
import edu.usm.healthsystem.model.client.Client;
import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.Appointment;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class PatientReport implements Report {
    public static final int COLUMNS = 16;
    public List<String[]> reportData;

    /**
     * Generates the CSV using a Client
     */
    @Override
    public void generate(Client client) {
    	// Title the report
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "paitent_report_" + date + ".csv";

        reportData = generateReportData(client);

        //generate the report
        generateCSV(filepath, reportData);
    }
    
    public List<String[]> generateReportData(Client client) {
    	 // Add header
        List<String[]> report = PatientReportHeaderGenerator.addHeader(getYear(), getFacility(), getSubDistrict(), getSubDistrict());
        
        // Add appointment information
        //Appointment.addInfo(report);
        
        // Add Paitent information
        if (client instanceof Patient) {
            PatientGenerator.addInfo(report, (Patient) client);
        }
        
        return report;
    }
    
    public List<String[]> getReportData() {
    	return reportData;
    }
    
    public void setReportData(List<String[]> report) {
    	reportData = report;
    }


    public void generateCSV(String filePath, List<String[]> data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : data) {
                writer.append(String.join(",", row)).append("\n");
            }
            System.out.println("CSV file generated successfully at: " + filePath);
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

    public String getSubDistrict() { return "Accra West"; } // TODO: need to let the user input

    
    /**
     * @param s - String to parse
     * @return - 0 if its null or empty and the integer otherwise. To prevent Integer.parseInt() throwing errors.
     */
    public static int parseInt(String s) {
    	return (s == "" || s == null) ? 0 : (int) Double.parseDouble(s);
    }
}