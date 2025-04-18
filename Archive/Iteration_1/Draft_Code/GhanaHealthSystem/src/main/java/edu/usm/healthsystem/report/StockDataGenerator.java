package edu.usm.healthsystem.report;

import java.util.List;

public class StockDataGenerator {

    /**
     * @param report
     */
    public static void addInfo(List<String[]> report) {
        String[] r1 = rowOne();
        String[] r2 = rowTwo();
        String[] r3 = rowThree();
        String[] r4a = rowFourA();
        String[] r4b = rowFourB();
        String[] r5 = rowFive();
        String[] r6a = rowSixA(r1, r2, r3, r4a, r5);
        String[] r6b = rowSixB();
        String[] r7 = rowSeven();
        String[] r8 = rowEight(r7, r6a);

        report.add(r1);
        report.add(r2);
        report.add(r3);
        report.add(r4a);
        report.add(r4b);
        report.add(r5);
        report.add(r6a);
        report.add(r6b);
        report.add(r7);
        report.add(r8);
    }
    
    /**
     * @param report - The report to regenerate the information for
     * 
     * Regenerates the rows that depend on other values
     */
    public static void regenInfo(List<String[]> report) {
    	String[] r6 = rowSixA(report.get(6), report.get(7), report.get(8), report.get(9), report.get(11) );
    	String[] r8 = rowEight(report.get(14), r6);
    	
    	report.set(12, r6);
    	report.set(15, r8);
    }

    /**
     * @return - Row 1 of the report in array form
     */
    private static String[] rowOne() {
    	String[] r1 = {"1. Beginning", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	// column 1 skipped because it is for totals further down.
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add item info from the beginning of the month
    	}
    	
    	return r1;
	}
    
    /**
     * @return - Row 2 of the report in array form
     */
    private static String[] rowTwo() {
    	String[] r2 = {"2. Received", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}; 
    	
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add received information from the current month
    	}
    	
    	
    	return r2;
	}
    
    /**
     * @return - Row 3 of the report in array form
     */
    private static String[] rowThree() {
    	String[] r3 = {"3. Issued", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add received information from the current month
    	}
    	
    	return r3;
	}

    /**
     * @return - Row 4a of the report in array form
     */
    private static String[] rowFourA() {
    	String[] r4a = {"4a. Transferred Amount", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add transferred amount information from the current month
    	}
    	
    	return r4a;
	}


    /**
     * @return - Row 4b of the report in array form
     */
    private static String[] rowFourB() {
    	String[] r4b = {"4b. To Where? (MOH/GMRA/PPAG/CC/Private)", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add transferred location information from the current month
    	}

    	return r4b;
	}


    /**
     * @return - Row 5 of the report in array form
     */
    private static String[] rowFive() {
    	String[] r5 = {"5. Loss/Expired", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}; 
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add expired information from the current month
    	}
    	
    	return r5;
	}
    
    

    /**
     * @param r1 - Row one of the report
     * @param r2 - Row two of the report
     * @param r3 - Row three of the report
     * @param r4a - Row four A of the report
     * @param r5 - Row five of the report
     * @return - Row 6 of the report in array form
     */
    private static String[] rowSixA(String[] r1, String[] r2, String[] r3, String[] r4a, String[] r5) {
    	
    	String[] r6 = new String[16];
    	r6[0] = "6a. Ending [1 + 2 - 3 - 4a - 5]";
    	r6[1] = "";
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int start = MonthlyReport.parseInt(r1[i]);
	    	int rec = MonthlyReport.parseInt(r2[i]);
	    	int issued = MonthlyReport.parseInt(r3[i]);
	    	int transferred = MonthlyReport.parseInt(r4a[i]);
	    	int expired = MonthlyReport.parseInt(r5[i]);
	    	
	    	int fi = start + rec - issued - transferred - expired;
	    	r6[i] = "" + fi;
    	}
    	
    	return r6;
	}
    
    private static String[] rowSixB() {
    	
    	String[] r6 = new String[16];
    	r6[0] = "6b. Physical Stock (Manually Enterred)";
    	r6[1] = "";
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// add in manually entered info
    	}
    	
    	return r6;
	}

    
    
    /**
     * @return - Row 7 of the report in array form
     */
    private static String[] rowSeven() {
    	String[] r7 = {"7. Number of months required", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}; 
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		// Add expired information from the current month
    	}
    	
    	return r7;
	}

    
    
    /**
     * @param r7 - Row seven of the report
     * @param r6 - Row six of the report
     * @return - Row 8 of the report in array form
     */
    private static String[] rowEight(String[] r7, String[]r6) {
    	String[] r8 = new String[16];
    	
    	r8[0] = "8. Quantity Required [line 7 - line 6]";
    	r8[1] = "";
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int line7 = MonthlyReport.parseInt(r7[i]);
	    	int line6 = MonthlyReport.parseInt(r6[i]);
	    	int fi = Math.max(0, line7 - line6); // can't require a negative quantity

	    	r8[i] = "" + fi;
    	}
    	
    	return r8;
    }
    

}
