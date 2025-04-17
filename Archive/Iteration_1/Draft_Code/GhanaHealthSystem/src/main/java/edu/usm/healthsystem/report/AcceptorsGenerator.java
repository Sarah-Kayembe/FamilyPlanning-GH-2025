package edu.usm.healthsystem.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class AcceptorsGenerator {
	
    /**
     * @param report - The report to add the acceptor information to
     * 
     * Adds all of the acceptor information to the report
     */
    public static void addInfo(List<String[]> report) {
    	// Acceptors by method section
    	String[] acceptors = {"Acceptors By Method", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	String[] r13a = rowThirteenA();
    	String[] r13b = rowThirteenB();
    	String[] r13c = rowThirteenC(r13a, r13b);
    	String[] r14 = rowFourteen();
    	String[] r15 = rowFifteen();
    	
    	// Add rows to report
    	report.add(acceptors);
    	report.add(r13a);
    	report.add(r13b);
    	report.add(r13c);
    	report.add(r14);
    	report.add(r15);
    }
    
    /**
     * @param report - The report to regenerate the acceptor information
     * 
     * Recalculates all the totals and row 13c for the report
     */
    public static void regenInfo(List<String[]> report) {
    	report.set(27, regenTotal(report.get(27)));
    	report.set(28, regenTotal(report.get(28)));
    	
    	String[] r13c = rowThirteenC(report.get(27), report.get(28));
    	report.set(29, r13c);
    	
    	report.set(30, regenTotal(report.get(30)));
    	report.set(31, regenTotal(report.get(31)));
    }
    
    /**
     * @param row - The row to regenerate the total for
     * @return - The row with an updated total in column 1
     */
    private static String[] regenTotal(String[] row) {
    	int total = 0;
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int cell = MonthlyReport.parseInt(row[i]);
	    	total += cell;
    	}
    	
    	row[1] = Integer.toString(total);
    	
    	return row;
    }
    
	/**
	 * @return - Row 13a of the report in array format
	 */
	private static String[] rowThirteenA() {
    	String[] r13a = {"13a. New Acceptors", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	int total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		int acceptor = 0;
    		// Add new acceptor info for the month
    		total += acceptor;
    	}
    	r13a[1] = Integer.toString(total);
    	
    	return r13a;
	}
	
	/**
	 * @return - Row 13b of the report in array format
	 */
	private static String[] rowThirteenB() {
    	String[] r13b = {"13b. Continuing Acceptors", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	int total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		int acceptor = 0;
    		// Add continuing acceptor info for the month
    		
    		total += acceptor;
    	}
    	r13b[1] = Integer.toString(total);
    	
    	return r13b;
	}
	
	/**
	 * @param r13a - Row 13a on the report, new acceptors
	 * @param r13b - Row 13b on the report, continuing acceptors
	 * @return - Row 13c of the report in array format
	 */
	private static String[] rowThirteenC(String[] r13a, String[] r13b) {
    	String[] r13c = new String[16];
    	
    	r13c[0] = "13c. Total";
    	int total = 0;
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
	    	int a = MonthlyReport.parseInt(r13a[i]);
	    	int b = MonthlyReport.parseInt(r13b[i]);
	    	int fi = a + b;
	    	total += fi;

	    	r13c[i] = Integer.toString(fi);
    	}
    	r13c[1] = Integer.toString(total);
    	
    	return r13c;
	}

	/**
	 * @return - Row 14 of the report in array format
	 */
	private static String[] rowFourteen() {
    	String[] r14 = {"14. Total Visits", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	int total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		int visits = 0;
    		// Add new visit info for the month
    		
    		total += visits;
    	}
    	r14[1] = Integer.toString(total);
    	
    	return r14;
	}
	/**
	 * @return - Row 15 of the report in array format
	 */
	private static String[] rowFifteen() {
    	String[] r15 = {"15. Couple-Years of Protection", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    	
    	int total = 0;
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		int cyp = 0;
    		// Add new couple years of protection info
    		
    		total += cyp;
    	}
    	r15[1] = Integer.toString(total);
    	
    	return r15;
	}
}
