package edu.usm.healthsystem.model.service.inventory;

import edu.usm.healthsystem.report.MonthlyReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Service handling inventory management operations.
 */
public class InventoryService {

    private final List<Item> itemList = new ArrayList<>();

    public InventoryService() {
        // Initialize inventory if needed
    }
    
    /**
     * Adds a new item to the inventory, if it does not yet exist in the system
     * @param item The item to add
     * @return true if operation was successful, false otherwise
     */
    public boolean addInventoryItem(Item item) {
    	for (Item otherItem : itemList)
    	{
    		if (otherItem.getName().equals(item.getName())) {
    			System.out.printf("ERROR: item (%s) already exists in inventory\n", item.getName());
    			return false;
    		}
    	}
    	if (!(itemList.add(item))) {
    		System.out.printf("ERROR: failed to add item (%s) to inventory\n", item.getName());
    		return false;
    	}
    	else return true;
    }
    
    /**
     * Returns a "snapshot" of the current state of the InventoryService, which
     * copies all Items in the Inventory and their counts to a new List object.
     * @return a List containing the InvertoryService's state when this method
     * was invoked
     */
    public List<Item> getInventorySnapshot() {
    	ArrayList<Item> snapshot = new ArrayList<>();
    	snapshot.addAll(itemList);
    	return snapshot;
    }

    /**
     * Displays current inventory state.
     */
    public void viewInventory() {
    	for (Item item : itemList) {
    		System.out.printf("%s: %d\n", item.getName(), item.getAmount());
    	}
    }
    
    /**
     * Increases the current stock of an item in the inventory
     * 
     * In order to succeed, the item must exist in the inventory, and the
     * amount to increase by must be greater than zero
     * 
     * @param item The item to increase stock of
     * @param amount The quantity to increase by
     * @return true if operation was successful, false otherwise
     */
    private boolean addAmount(Item item, int amount) {
    	if (!(itemList.contains(item))) {
    		System.err.printf("ERROR: item not found in inventory (%s)\n", item.getName());
    		return false;
    	}
    	if (amount <= 0) {
    		System.err.printf("ERROR: non-positive amount (%d)\n", amount);
    		return false;
    	}
    	item.setAmount(item.getAmount() + amount);
    	return true;
    }
    
    /**
     * Reduces the current stock of an item in the inventory
     * 
     * In order to succeed, the item must exist in the inventory, and the
     * amount to reduce by must be greater than zero and less than or equal to
     * the item's current stock
     * 
     * @param item The item to reduce stock of
     * @param amount The quantity to reduce by
     * @return true if operation was successful, false otherwise
     */
    private boolean subtractAmount(Item item, int amount) {
    	if (!(itemList.contains(item))) {
    		System.err.printf("ERROR: item (%s) not found in inventory\n", item.getName());
    		return false;
    	}
    	if (amount <= 0) {
    		System.err.printf("ERROR: non-positive amount (%d)\n", amount);
    		return false;
    	}
    	if (amount > item.getAmount())
    	{
    		System.err.printf("ERROR: amount (%d) greater than current stock of item (%s)\n", amount, item.getName());
    		return false;
    	}
    	item.setAmount(item.getAmount() - amount);
    	return true;
    }

    /**
     * Records expired items in inventory.
     * @param item The expired item
     * @param amount The quantity expired
     * @return true if operation was successful, false otherwise
     */
    public boolean enterExpiredItem(Item item, int amount) {
        // TODO: Implement expired item logging
        return subtractAmount(item, amount);
    }

    /**
     * Records items transferred to another facility.
     * @param item The transferred item
     * @param amount The quantity transferred
     * @return true if operation was successful, false otherwise
     */
    public boolean enterTransferredItems(Item item, int amount) {
        // TODO: Implement transferred items logging
    	return subtractAmount(item, amount);
    }

    /**
     * Records items received into inventory.
     * @param item The received item
     * @param amount The quantity received
     * @return true if operation was successful, false otherwise
     */
    public boolean enterReceivedItems(Item item, int amount) {
        // TODO: Implement received items recording
        return addAmount(item, amount);
    }

    /**
     * Records a patient visit that may consume inventory items.
     */
    public void enterVisit() {
        // TODO: Implement visit recording
    }

    /**
     * Generates a monthly inventory report.
     * @return The generated monthly report
     */
    public MonthlyReport generateMonthlyReport() {
        // TODO: Implement monthly report generation
        return new MonthlyReport();
    }
}