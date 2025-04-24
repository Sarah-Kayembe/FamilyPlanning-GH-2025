package edu.usm.healthsystem.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.usm.healthsystem.model.familyplanning.Item;
import edu.usm.healthsystem.model.report.MonthlyReport;

public class MonthlyReportController {

    private static JFrame parent;

    public MonthlyReportController(JFrame parent) {
        this.parent = parent;
    }

    public static void handleGenerateReport() {
        MonthlyReportOptions dialog = new MonthlyReportOptions(parent);
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
            
            
            MonthlyReport report = new MonthlyReport();
            report.generateReport(employeeName, data);

            JOptionPane.showMessageDialog(parent, "Report generated successfully!");
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter valid numbers for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to generate report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void handleInventoryTransaction() {
        InventoryTransactionOptions dialog = new InventoryTransactionOptions(parent);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) return;

        try {
            String selectedItem = dialog.getSelectedItem();
            String quantityStr = dialog.getQuantity();
            String reason = dialog.getTransactionReason();
            String transferPartner = dialog.getTransferPartner();

            if (quantityStr.isEmpty() || !quantityStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(
                    parent,
                    "You must enter a valid numeric quantity.",
                    "Invalid Quantity",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            int quantity = Integer.parseInt(quantityStr.trim());
            Item item = new Item(selectedItem, quantity);
            		
            switch (reason) {
            case "Issued":
            	// enterIssuedItem(item, quantity)
                break;
            case "Expired/Loss":
            	// enterExpiredItem(item, quantity)
                break;
            case "Transferred":
            	// enterTransferredItem(item, quantity, transferPartner)
                break;
            case "Received":
            	// enterReceivedItem(item, quantity)
                break;
            default:
                break;
            }

            // Build transaction message
            StringBuilder message = new StringBuilder();
            message.append("Transaction Recorded:\n");
            message.append("Item: ").append(selectedItem).append("\n");
            message.append("Quantity: ").append(quantityStr).append("\n");
            message.append("Reason: ").append(reason).append("\n");

            if ("transferred".equals(reason)) {
                message.append("Transfer Partner: ").append(transferPartner).append("\n");
            }
            JOptionPane.showMessageDialog(parent, message.toString(), "Transaction Summary", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter valid inputs for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to generate transaction: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void handleSetStock() {
        SetInventoryOptions dialog = new SetInventoryOptions(parent);
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) return;

        try {
            Map<String, Integer> data = dialog.getData();
            
            List<Item> items = data.entrySet()
                    .stream()
                    .map(entry -> new Item(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
            
           // some sort of set stock method in the inventory service

            JOptionPane.showMessageDialog(parent, "Inventory stock was set successfully!");
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Please enter valid numbers for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Failed to set the inventory's stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}