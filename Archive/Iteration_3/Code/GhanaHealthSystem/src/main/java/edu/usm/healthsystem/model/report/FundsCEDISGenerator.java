package edu.usm.healthsystem.model.report;

import java.util.List;

public class FundsCEDISGenerator {
	static double[] rowNineVals = {0,0,150,150,(3/100),300,1000,150,150,5000,25,1000,1,1,1,1000};
	
    /**
     * @param report - The report to add the Funds and CEDIS information to
     * 
     * Generates all of the rows using the information from row 3 in the report
     */
    public static void addInfo(List<String[]> report) {
    	// Funds Collected
    	String[] fundsCollected = {"Funds Collected", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        String[] r9 = rowNine();
        String[] r10 = rowTen(report.get(8)); // 8 is the row number for 3. Issued
        String[] r11a = rowElevenA(r10);
        String[] r11b = rowElevenB(r10);
        String[] r11c = rowElevenC(r10);
        
        // CEDIS Submitted
    	String[] cedis = {"CEDIS Submitted", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	String[] r12a = rowTwelveA(r10);
    	String[] r12b = rowTwelveB(r10);
    	String[] r12c = rowTwelveC(r10);
        
        
        // Add to report
        report.add(fundsCollected);
        report.add(r9);
        report.add(r10);
        report.add(r11a);
        report.add(r11b);
        report.add(r11c);
        
        report.add(cedis);
        report.add(r12a);
        report.add(r12b);
        report.add(r12c);
    }
    
    /**
     * @param report - The report to regenerate information for
     * 
     * Regenerates all of the rows using the new information in the report
     */
    public static void regenInfo(List<String[]> report) {
    	// Funds Collected
        String[] r10 = rowTen(report.get(8)); // 8 is the row number for 3. Issued
        String[] r11a = rowElevenA(r10);
        String[] r11b = rowElevenB(r10);
        String[] r11c = rowElevenC(r10);
        
        // CEDIS Submitted
    	String[] r12a = rowTwelveA(r10);
    	String[] r12b = rowTwelveB(r10);
    	String[] r12c = rowTwelveC(r10);
        
    	// 17 - 9. Unit Price
        report.set(18, r10);
        report.set(19, r11a);
        report.set(20, r11b);
        report.set(21, r11c);
        // 22 - CEDIS SUBMITTED
        report.set(23, r12a);
        report.set(24, r12b);
        report.set(25, r12c);
    }

	/**
     * @return - The Unit price row, row 9, in array format
     */
    private static String[] rowNine() {
        return new String[] {"9. Unit Price (CEDIS)", "TOTAL", "150/cycle", "150/cycle", "3peices/100", "300/peice",
                "1000/unit", "150/cycle", "150/cycle", "5000/pack", "25/tab", "1000/dose", "", "", "", "1000/dose"};
    }


    /**
     * @param r3 - Row 3, the amount issued for each item
     * @return - Row 10 of the report in array form
     */
    private static String[] rowTen(String[] r3) {
    	String[] r10 = new String[16];
    	
    	r10[0] = "10. CEDIS Collected [line 3 x line 9]";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int issued = MonthlyReport.parseInt(r3[i]);
	    	double cedis = rowNineVals[i];
	    	double fi = issued * cedis;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;

	    	r10[i] = Double.toString(rounded);
    	}
    	
    	r10[1] = Double.toString(total);
    	
    	return r10;
    }

    /**
     * @param r10 - Row 10, the CEDIS collected
     * @return - Row 11a of the report in array form
     */
    private static String[] rowElevenA(String[] r10) {
    	String[] r11a = new String[16];
    	
    	r11a[0] = "11a. CEDIS Retined - SDHS 50% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	double half = 0.5;
	    	
	    	double fi = cedis * half;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r11a[i] = Double.toString(rounded);
    	}
    	
    	r11a[1] = Double.toString(total);
    	
    	return r11a;
    }
    
    /**
     * @param r10 - Row 10 of the report, CEDIS collected
     * @return - Row 11b of the report in array format
     */
    private static String[] rowElevenB(String[] r10) {
    	String[] r11b = new String[16];
    	
    	r11b[0] = "11b. CEDIS Retained - DHMT 10% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	double tenth = 0.1;
	    	
	    	double fi = cedis * tenth;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r11b[i] = Double.toString(rounded);
    	}
    	
    	r11b[1] = Double.toString(total);
    	
    	return r11b;
    }
    
    /**
     * @param r10 - Row 10 of the report, CEDIS collected
     * @return - Row 11c of the report in array format
     */
    private static String[] rowElevenC(String[] r10) {
    	String[] r11c = new String[16];
    	
    	r11c[0] = "11c. CEDIS Retained - RHMT 10% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	double tenth = 0.1;
	    	
	    	double fi = cedis * tenth;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r11c[i] = Double.toString(rounded);
    	}
    	
    	r11c[1] = Double.toString(total);
    	
    	return r11c;
    }
    
	/**
	 * @param r10 - Row 10 of the report, CEDIS collected
	 * @return - Row 12a of the report in array format
	 */
	private static String[] rowTwelveA(String[] r10) {
    	String[] r12a = new String[16];
    	
    	r12a[0] = "12a. SDHS - DHMT 50% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	double half = 0.5;
	    	
	    	double fi = cedis * half;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r12a[i] = Double.toString(rounded);
    	}
    	
    	r12a[1] = Double.toString(total);
    	
    	return r12a;
	}
	
	/**
	 * @param r10 - Row 10 of the report, CEDIS collected
	 * @return - Row 12b of the report in array format
	 */
	private static String[] rowTwelveB(String[] r10) {
    	String[] r12b = new String[16];
    	
    	r12b[0] = "12b. DHMT - REG. 40% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	
	    	double fi = cedis * 0.4;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r12b[i] = Double.toString(rounded);
    	}
    	
    	r12b[1] = Double.toString(total);
    	
    	return r12b;
	}
    
    /**
	 * @param r10 - Row 10 of the report, CEDIS collected
	 * @return - Row 12c of the report in array format
     */
    private static String[] rowTwelveC(String[] r10) {
    	String[] r12c = new String[16];
    	
    	r12c[0] = "12c. RHMT - CENT. ACC. 30% x line 10";
    	
    	double total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cedis = MonthlyReport.parseInt(r10[i]);
	    	
	    	double fi = cedis * 0.3;
	    	// rounded to two decimals
	    	double rounded = Math.round(fi * 100.0f) / 100.0f; 
	    	total += fi;
	        

	    	r12c[i] = Double.toString(rounded);
    	}
    	
    	r12c[1] = Double.toString(total);
    	
    	return r12c;
	}


}