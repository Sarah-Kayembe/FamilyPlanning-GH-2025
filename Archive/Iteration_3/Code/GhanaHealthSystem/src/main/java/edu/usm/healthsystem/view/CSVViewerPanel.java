package edu.usm.healthsystem.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CSVViewerPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public CSVViewerPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton openButton = new JButton("Open CSV");
        openButton.addActionListener(e -> openCSV());
        JPanel controlPanel = new JPanel();
        controlPanel.add(openButton);
        add(controlPanel, BorderLayout.NORTH);
    }

    private void openCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File csvFile = fileChooser.getSelectedFile();
            loadCSV(csvFile);
        }
    }
    
    private ArrayList<String> parseCSVLine(String line) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString().trim());

        return result;
    }

    public void loadCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);

            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                ArrayList<String> fields = parseCSVLine(line);

                if (isFirstLine) {
                    for (String column : fields) {
                        tableModel.addColumn(column);
                    }
                    isFirstLine = false;
                } else {
                    tableModel.addRow(fields.toArray());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to load file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

}
