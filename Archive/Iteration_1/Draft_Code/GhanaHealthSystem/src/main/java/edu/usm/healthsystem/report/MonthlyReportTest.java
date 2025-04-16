package edu.usm.healthsystem.report;
import edu.usm.healthsystem.model.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

class MonthlyReportTest {

	@Test
	void testColumnOneTwo() {
		MonthlyReport report = new MonthlyReport();
		Client client = new Employee();
		List<String[]> reportData = report.generateReportData(client);
		
		// ADD TESTS HERE
		
		// column 1 - STOCK INFO
		// row 1 - beginning
		reportData.get(6)[2] = "100";
		// row 2 - received
		reportData.get(7)[2] = "50";
		// row 3 - issued
		reportData.get(8)[2] = "49";
		// row 4a - transferred amount
		reportData.get(9)[2] = "20";
		// row 4b - transferred where
		reportData.get(10)[2] = "Private";
		// row 5 - expired/loss
		reportData.get(11)[2] = "5";
		// row 7 - number required per month?
		reportData.get(14)[2] = "100";
		
		
		// column 1 - ACCEPTOR INFO
		// row 13a - new acceptors
		reportData.get(27)[2] = "3";
		// row 13b - continuing acceptors
		reportData.get(28)[2] = "15";
		// row 13c is calculated
		// row 14 - total visits
		reportData.get(30)[2] = "10";
		// row 15 - CYP
		reportData.get(31)[2] = "2";
		
		
		// column 2 - STOCK INFO
		reportData.get(6)[3] = "60";
		reportData.get(7)[3] = "20";
		reportData.get(8)[3] = "12";
		reportData.get(9)[3] = "5";
		reportData.get(10)[3] = "Private";
		reportData.get(11)[3] = "2";
		reportData.get(14)[3] = "60";
		
		// column 2 - ACCEPTOR INFO
		reportData.get(27)[3] = "1";
		reportData.get(28)[3] = "8";
		reportData.get(30)[3] = "4";
		reportData.get(31)[3] = "3";
		
		// END TESTS
		
		// Regenerate the report with the new info
		StockDataGenerator.regenInfo(reportData);
		FundsCEDISGenerator.regenInfo(reportData);
		AcceptorsGenerator.regenInfo(reportData);
		report.setReportData(reportData);
		
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "monthly_report_" + date + ".csv";

        //generate the report
        report.generateCSV(filepath, reportData);
	}

}
