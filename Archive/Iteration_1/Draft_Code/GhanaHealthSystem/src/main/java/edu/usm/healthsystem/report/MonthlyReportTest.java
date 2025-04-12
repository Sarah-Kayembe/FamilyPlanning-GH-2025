package edu.usm.healthsystem.report;
import edu.usm.healthsystem.model.*;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

class MonthlyReportTest {

	@Test
	void testColumnOne() {
		MonthlyReport report = new MonthlyReport();
		Client client = new Employee();
		List<String[]> reportData = report.generateReportData();
		
		// ADD TESTS HERE
		
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
		reportData.get(13)[2] = "100";
		
		// mess with report to make it correct
		StockDataGenerator.regenStockData(reportData);
		for(int i = 0; i < 16; i++)
			reportData.removeLast();
		FundsCEDISGenerator.addFundsCEDIS(reportData, reportData.get(8));
		AcceptorsGenerator.addAcceptors(reportData);
		report.setReportData(reportData);
		
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "monthly_report_" + date + ".csv";

        //generate the report
        report.generateCSV(filepath, reportData);
	}

}
