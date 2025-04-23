package edu.usm.healthsystem.view;

import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ReportController {

    private JFrame parent;

    public ReportController(JFrame parent) {
        this.parent = parent;
    }

    public void handleGenerateReport() {
        ReportOptionsDialog dialog = new ReportOptionsDialog(parent);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) return;

        try {
            String employeeName = dialog.getEmployeeName();

            if (employeeName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        parent,
                        "You must enter an employee name before generating the report.",
                        "Missing Employee Name",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Map<String, Integer> data = dialog.getData();

            System.out.println(data.toString());
            // MonthlyReport report = new MonthlyReport();
            // report.generate(employeeName, data);

            JOptionPane.showMessageDialog(parent, "Report generated successfully!");

        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter valid numbers for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}