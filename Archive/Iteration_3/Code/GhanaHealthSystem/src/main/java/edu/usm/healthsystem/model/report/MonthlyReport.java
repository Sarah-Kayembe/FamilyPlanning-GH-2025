package edu.usm.healthsystem.model.report;

import edu.usm.healthsystem.model.client.Client;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MonthlyReport implements Report {
    public static final int COLUMNS = 16;
    public List<String[]> reportData;

    /**
     * Generates the CSV using a Client
     */
    public void generateReport(String employeeName, Map<String, Integer> row6b) {
    	// Title the report
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "test-output/monthly_report_" + date + ".csv";

        reportData = generateReportData(employeeName, row6b);

        //generate the report
        generateCSV(filepath, reportData);
    }
    
    public List<String[]> generateReportData(String employeeName, Map<String, Integer> row6b) {
    	 // Add header
        List<String[]> report = HeaderGenerator.addHeader(getClinic(), getDistrict(), getRegion(), employeeName);
        
        // Add item information
        StockDataGenerator.addInfo(report, row6b);
        
        // Add funds and cedis information
        FundsCEDISGenerator.addInfo(report);
        
        // Add patient information
        AcceptorsGenerator.addInfo(report);
        
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
    
    public String getRegion() { return "Ghana"; }

    public String getDistrict() { return "Ghana NHIS"; }

    public String getClinic() { return "Family Planning Clinic"; }
    
    /**
     * @param s - String to parse
     * @return - 0 if its null or empty and the integer otherwise. To prevent Integer.parseInt() throwing errors.
     */
    public static int parseInt(String s) {
    	return (s == "" || s == null) ? 0 : (int) Double.parseDouble(s);
    }

	@Override
	public void generate(Client client) {
		
	}
}