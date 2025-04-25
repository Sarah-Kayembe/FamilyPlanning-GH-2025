package edu.usm.healthsystem.model.report;

import java.util.List;
import java.util.Map;

import edu.usm.healthsystem.model.familyplanning.Item;
import edu.usm.healthsystem.service.inventory.InventoryService;
import edu.usm.healthsystem.service.inventory.InventoryTransaction;

public class StockDataGenerator {
	private static InventoryService inventory;
	private final static String[] categories = {"", "", "LO-FEM", "Overette", "Male Condom", "Female Condom", 
            "Copper T", "Micro G", "Micr - N", "Postinor 2", "Sampoo", "Depo", "Vasectomy", "LAM", "Natural", "Norigynon"};

    /**
     * @param report
     */
    public static void addInfo(List<String[]> report, Map<String, Integer> row6b) {
    	inventory = InventoryService.getInstance();
    	
        String[] r1 = rowOne();
        String[] r2 = rowTwo();
        String[] r3 = rowThree();
        String[] r4a = rowFourA();
        String[] r4b = rowFourB();
        String[] r5 = rowFive();
        String[] r6a = rowSixA(r1, r2, r3, r4a, r5);
        String[] r6b = rowSixB(row6b);
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
    	
    	List<Item> beginning = inventory.getBeginningSnapshot();
        for (int i = 2; i < MonthlyReport.COLUMNS; i++) {
            String category = categories[i];
            for (Item item : beginning) {
                if (item.getName().equals(category)) {
                    r1[i] = String.valueOf(item.getAmount());
                    break;
                }
            }
        }
        
    	return r1;
	}
    
    /**
     * @return - Row 2 of the report in array form
     */
    private static String[] rowTwo() {
    	// System.out.print(inventory.getReceivedTransactions());
    	return transactionRow("2. Received", inventory.getReceivedTransactions());
	}
    
    /**
     * @return - Row 3 of the report in array form
     */
    private static String[] rowThree() {
    	// System.out.print(inventory.getIssuedTransactions());
    	return transactionRow("3. Issued", inventory.getIssuedTransactions());
	}

    /**
     * @return - Row 4a of the report in array form
     */
    private static String[] rowFourA() {
    	return transactionRow("4a. Transferred Amount", inventory.getTransferredTransactions());
	}


    /**
     * @return - Row 4b of the report in array form
     */
    private static String[] rowFourB() {
    	List<InventoryTransaction> transactions = inventory.getTransferredTransactions();
        String[] row = new String[MonthlyReport.COLUMNS];
        row[0] = "4b. To Where? (MOH/GMRA/PPAG/CC/Private)";


        for (InventoryTransaction tx : transactions) {
            String itemName = tx.getItem().getName();

            for (int i = 2; i < MonthlyReport.COLUMNS; i++) {
                if (itemName.equals(categories[i])) 
                    row[i] = tx.getTransferredTo();
            }
        }

        return row;
	}


    /**
     * @return - Row 5 of the report in array form
     */
    private static String[] rowFive() {
    	return transactionRow("5. Loss/Expired", inventory.getExpiredTransactions());
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
    
    private static String[] rowSixB(Map<String, Integer> data) {
    	
    	String[] r6 = new String[16];
    	String[] header = {"STOCK", "", "LO-FEM", "Overette", "Male Condom", "Female Condom", 
                "Copper T", "Micro G", "Micr - N", "Postinor 2", "Sampoo", "Depo", "Vasectomy", "LAM", "Natural", "Norigynon"};
    	
    	r6[0] = "6b. Physical Stock (Manually Enterred)";
    	r6[1] = "";
    	
    	for(int i = 2; i < MonthlyReport.COLUMNS; i++) {
    		r6[i] = data.get(header[i]).toString();
    	}
    	
    	return r6;
	}

    
    
    /**
     * @return - Row 7 of the report in array form
     */
    private static String[] rowSeven() {
    	String[] r7 = {"7. Number of months required", "", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12", "12"}; 
    	// Hard coded in -- one year requirement for now.
    	
    	
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
    
    
    private static String[] transactionRow(String label, List<InventoryTransaction> transactions) {
        String[] row = new String[MonthlyReport.COLUMNS];
        row[0] = label;

        // Initialize numeric values for summing amounts per category
        int[] categorySums = new int[MonthlyReport.COLUMNS];

        for (InventoryTransaction tx : transactions) {
            Item item = tx.getItem();
            String itemName = item.getName();

            for (int i = 2; i < MonthlyReport.COLUMNS; i++) {
                if (itemName.equalsIgnoreCase(categories[i])) {
                    categorySums[i] += tx.getStockChange();  // Amount might be negative (e.g., issuance)
                    break;
                }
            }
        }

        // Populate the row with stringified values
        for (int i = 2; i < MonthlyReport.COLUMNS; i++) {
            row[i] = categorySums[i] == 0 ? "" : String.valueOf(categorySums[i]);
        }

        return row;
    }

}
