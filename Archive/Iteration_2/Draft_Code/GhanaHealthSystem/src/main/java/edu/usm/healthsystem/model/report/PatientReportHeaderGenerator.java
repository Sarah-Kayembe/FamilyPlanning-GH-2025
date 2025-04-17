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
    
        header.add(new String[]{"FAMILY PLANNING CLIENT REGISTER"});
        header.add(new String[]{"YEAR: " + year});
        header.add(new String[]{"FACILITY / ZONE: " + facility +
                "    Sub-district: " + subDistrict +
                "    District: " + district});
        header.add(new String[]{""}); // Empty line
    
        // Now add table header: each entry is a true CSV column
        header.add(new String[]{
                "S/NO", "Date", "NHIS Reg. No.", "Card No", "NAME",
                "Marital Status", "Sex", "Address (Location/WP/Community/Hse No.)",
                "Method of Choice", "1st ever use of method (Y/N)", "Age", "Parity",
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        });
        
        return header;
    }    
    
}
