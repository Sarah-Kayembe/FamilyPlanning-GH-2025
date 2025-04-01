package edu.usm.healthsystem.client.inventory;

import edu.usm.healthsystem.report.MonthlyReport;
import edu.usm.healthsystem.report.Report;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private List<Item> itemList = new ArrayList<>();

    public InventoryService() {

    }

    public void viewInventory() {

    }

    public boolean enterExpiredItem(Item item, int amount) {
        return false;
    }

    public boolean enterTransferredItems(Item item, int amount) {
        return false;
    }

    public boolean enterReceivedItems(Item item, int amount) {
        return false;
    }

    public void enterVisit() {

    }

    public MonthlyReport generateMonthlyReport() {
        return new MonthlyReport();
    }

}
