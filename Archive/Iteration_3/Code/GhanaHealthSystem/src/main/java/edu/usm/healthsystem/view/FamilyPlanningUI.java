package edu.usm.healthsystem.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class FamilyPlanningUI extends JFrame {
	public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final String BACKGROUND_PATH = "src/main/resources/images/Background.jpg";

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public FamilyPlanningUI() {
        setTitle("Nurse Application");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Views
        MainMenuPanel menuPanel = new MainMenuPanel(this);
        InventoryPanel inventoryPanel = new InventoryPanel(this);
        PatientPanel patientPanel = new PatientPanel(this);

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(inventoryPanel, "Inventory");
        mainPanel.add(patientPanel, "Patient");

        add(mainPanel);
        setVisible(true);
    }

    public void showView(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static ImageIcon createResizedIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FamilyPlanningUI::new);
    }
}