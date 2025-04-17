package edu.usm.healthsystem.model.report;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to generate the header for the Patient Report.
 */
public class PatientReportHeaderGenerator {

    /**
     * Generates the header for the patient-specific report.
     * @param year - The year for the report
     * @param facility - The facility name
     * @param subDistrict - The sub-district name
     * @param district - The district name
     * @return List of header lines in String format
     */
    public static List<String[]> addHeader(String year, String facility, String subDistrict, String district) {
        List<String[]> header = new ArrayList<>();
    
        // Add the header lines as arrays of strings (each line as a single-element array)
        header.add(new String[]{"FAMILY PLANNING CLIENT REGISTER"});
        header.add(new String[]{"YEAR: " + year});
        header.add(new String[]{"FACILITY / ZONE: " + facility + 
                                "    Sub-district: " + subDistrict + 
                                "    District: " + district});
        header.add(new String[]{""}); // Empty line
        
        // Adjust the table header to be an array of strings for each column
        String tableHeader = String.format("%-5s %-10s %-10s %-10s %-20s %-15s %-15s %-15s %-15s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s %-5s",
                    "S/NO", "Date", "NHIS Reg. No.", "Card No", "NAME", "Marital Status", "Sex", "Address (Location/WP/Community/Hse No.)",
                    "Method of Choice", "1st ever use of method (Y/N)", "Age", "Parity",
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        header.add(tableHeader.split("")); // Split the tableHeader into individual columns
    
        // Separator line
        header.add(new String[]{"-".repeat(180)});
    
        return header;
    }
    
}
