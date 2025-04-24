package edu.usm.healthsystem.view;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.usm.healthsystem.model.report.MonthlyReport;

public class InventoryTransactionController {

	private JFrame parent;

    public InventoryTransactionController(JFrame parent) {
        this.parent = parent;
    }
    
    public void handleGenerateReport() {
    	InventoryTransactionOptions dialog = new InventoryTransactionOptions(parent);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) return;

        try {
            String employeeName = dialog.getEmployeeName();

            if (employeeName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        parent,
                        "You must enter an employee name before recording a transaction.",
                        "Missing Employee Name",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }


            JOptionPane.showMessageDialog(parent, "Transaction recorded in log!");
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter valid inputs for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to generate transaction: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
