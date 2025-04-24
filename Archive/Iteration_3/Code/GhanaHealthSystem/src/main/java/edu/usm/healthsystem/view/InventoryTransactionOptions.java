package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

public class InventoryTransactionOptions extends JDialog {

    private boolean confirmed = false;
    private final JComboBox<String> itemDropdown;
    private final JTextField quantityField;
    private final JComboBox<String> reasonDropdown;
    private final JComboBox<String> transferPartnerDropdown;
    private final JButton submitButton;
    private final JButton cancelButton;

    private static final String[] categories = {
        "LO-FEM", "Overette", "Male Condom", "Female Condom",
        "Copper T", "Micro G", "Micr - N", "Postinor 2",
        "Sampoo", "Depo", "Vasectomy", "LAM",
        "Natural", "Norigynon"
    };

    private static final String[] transactionReasons = {
        "Issued", "Expired/Loss", "Transferred", "Received"
    };

    private static final String[] transferPartners = {
        "MOH", "GMRA", "PPAG", "CC", "Private"
    };

    public InventoryTransactionOptions(Frame owner) {
        super(owner, "Inventory Transaction", true);

        setSize(FamilyPlanningUI.WINDOW_WIDTH, FamilyPlanningUI.WINDOW_HEIGHT);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Background image
        ImageIcon backgroundImage = new ImageIcon(FamilyPlanningUI.BACKGROUND_PATH);
        Image img = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(img);
        JLabel background = new JLabel(backgroundImage);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);

        // Title
        JLabel title = new JLabel("Inventory Transaction Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.BLACK);
        inputPanel.add(title);
        inputPanel.add(Box.createVerticalStrut(30));

        // Dropdown for category
        JLabel itemLabel = new JLabel("Select Item:");
        itemLabel.setFont(labelFont);
        itemLabel.setForeground(Color.BLACK);
        inputPanel.add(itemLabel);

        itemDropdown = new JComboBox<>(categories);
        itemDropdown.setFont(fieldFont);
        inputPanel.add(itemDropdown);
        inputPanel.add(Box.createVerticalStrut(15));

     // Reason dropdown (moved above quantity)
        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setFont(labelFont);
        reasonLabel.setForeground(Color.BLACK);
        inputPanel.add(reasonLabel);

        reasonDropdown = new JComboBox<>(transactionReasons);
        reasonDropdown.setFont(fieldFont);
        inputPanel.add(reasonDropdown);
        inputPanel.add(Box.createVerticalStrut(15));

        // Transfer partner dropdown (immediately after reason)
        JLabel partnerLabel = new JLabel("From Where?:");
        partnerLabel.setFont(labelFont);
        partnerLabel.setForeground(Color.BLACK);
        partnerLabel.setVisible(false);
        inputPanel.add(partnerLabel);

        transferPartnerDropdown = new JComboBox<>(transferPartners);
        transferPartnerDropdown.setFont(fieldFont);
        transferPartnerDropdown.setVisible(false);
        inputPanel.add(transferPartnerDropdown);
        inputPanel.add(Box.createVerticalStrut(15));

        // Quantity field (now below reason)
        JLabel qtyLabel = new JLabel("Enter Quantity:");
        qtyLabel.setFont(labelFont);
        qtyLabel.setForeground(Color.BLACK);
        inputPanel.add(qtyLabel);

        quantityField = new JTextField();
        quantityField.setFont(fieldFont);
        quantityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        inputPanel.add(quantityField);
        inputPanel.add(Box.createVerticalStrut(15));

        reasonDropdown.addItemListener(e -> {
            boolean showPartner = "Transferred".equals(reasonDropdown.getSelectedItem());
            partnerLabel.setVisible(showPartner);
            transferPartnerDropdown.setVisible(showPartner);
            inputPanel.revalidate();
            inputPanel.repaint();
        });

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        submitButton = new JButton("Submit");
        submitButton.setFont(labelFont);
        submitButton.addActionListener((ActionEvent e) -> {
            confirmed = true;
            dispose();
        });
        buttonPanel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(labelFont);
        cancelButton.addActionListener((ActionEvent e) -> {
            confirmed = false;
            dispose();
        });
        buttonPanel.add(cancelButton);

        inputPanel.add(buttonPanel);
        background.add(inputPanel, BorderLayout.CENTER);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedItem() {
        return (String) itemDropdown.getSelectedItem();
    }

    public String getQuantity() {
        return quantityField.getText().trim();
    }

    public String getTransactionReason() {
        return (String) reasonDropdown.getSelectedItem();
    }

    public String getTransferPartner() {
        return (String) transferPartnerDropdown.getSelectedItem();
    }
}
