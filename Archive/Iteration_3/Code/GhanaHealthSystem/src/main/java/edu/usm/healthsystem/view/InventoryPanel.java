package edu.usm.healthsystem.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InventoryPanel extends JPanel {
    public InventoryPanel(FamilyPlanningUI parent) {
        setLayout(new BorderLayout());

        JLabel backgroundLabel = createBackgroundLabel();
        backgroundLabel.setLayout(new GridBagLayout());

        ImageIcon addIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/add_icon.png", 70, 80);
        ImageIcon reportIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/report_icon.png", 80, 80);
        ImageIcon previewIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/preview_icon.png", 90, 80);
        ImageIcon setStockIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/set_stock_icon.png", 80, 80);
        ImageIcon backIcon = FamilyPlanningUI.createResizedIcon("src/main/resources/images/back_icon.png", 80, 80);

        JButton addTransactionButton = createButton("      Add Transaction", addIcon, new Color(62, 181, 62));
        JButton generateReportButton = createButton("  Generate Report", reportIcon, new Color(71, 163, 255));
        JButton previewReportButton = createButton("   Preview Report", previewIcon, new Color(255, 193, 7));
        JButton setStockButton = createButton("   Set Stock", setStockIcon, new Color(224, 67, 255));
        JButton backButton = createButton("    Back", backIcon, new Color(232, 60, 60));


		addTransactionButton.addActionListener(e -> MonthlyReportController.handleInventoryTransaction());
		generateReportButton.addActionListener(e -> MonthlyReportController.handleGenerateReport());
        setStockButton.addActionListener(e -> MonthlyReportController.handleSetStock());
        previewReportButton.addActionListener(e -> previewReport());
        backButton.addActionListener(e -> parent.showView("Menu"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;
        backgroundLabel.add(addTransactionButton, gbc);
        gbc.gridy = 1;
        backgroundLabel.add(generateReportButton, gbc);
        gbc.gridy = 2;
        backgroundLabel.add(previewReportButton, gbc);
        gbc.gridy = 3;
        backgroundLabel.add(setStockButton, gbc);
        gbc.gridy = 4;
        backgroundLabel.add(backButton, gbc);

        add(backgroundLabel, BorderLayout.CENTER);
    }

    private JLabel createBackgroundLabel() {
        ImageIcon bg = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = bg.getImage().getScaledInstance(FamilyPlanningUI.WINDOW_WIDTH, FamilyPlanningUI.WINDOW_HEIGHT, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private JButton createButton(String text, ImageIcon icon, Color color) {
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(400, 100));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        return button;
    }
    
    private void previewReport() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM_yyyy"));
        String filepath = "test-output/monthly_report_" + date + ".csv";
        File file = new File(filepath);

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "Report not found:\n" + filepath,
                    "File Not Found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Preview Report", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(1800,800);
        dialog.setLocationRelativeTo(this);

        CSVViewerPanel viewer = new CSVViewerPanel();
        viewer.loadCSV(file);
        dialog.setContentPane(viewer);
        dialog.setVisible(true);
    }
}
