package edu.usm.healthsystem.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class AcceptorsGenerator {
	
    /**
     * @param report
     */
    public static void addAcceptors(List<String[]> report) {
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
