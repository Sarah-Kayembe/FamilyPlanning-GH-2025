package edu.usm.healthsystem.service.inventory;

import edu.usm.healthsystem.model.familyplanning.Item;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service handling inventory management operations.
 */
public class InventoryService {

    private final List<Item> itemList = new ArrayList<>();
    private final List<InventoryTransaction> transactionLog = new ArrayList<>();

    public InventoryService() {
        // Initialize inventory if needed
    }

    /**
     * Adds a new item to the inventory, if it does not yet exist in the system
     *
     * @param item The item to add
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean addInventoryItem(Item item) {
        for (Item otherItem : itemList) {
            if (otherItem.getName().equals(item.getName())) {
                System.err.printf("ERROR: item (%s) already exists in inventory\n", item.getName());
                return false;
            }
        }
        if (!(itemList.add(item))) {
            System.err.printf("ERROR: failed to add item (%s) to inventory\n", item.getName());
            return false;
        } else return true;
    }

    /**
     * Returns a "snapshot" of the current state of the InventoryService, which
     * copies all Items in the Inventory and their counts to a new List object.
     *
     * @return a List containing the InvertoryService's state when this method
     * was invoked
     */
    public List<Item> getInventorySnapshot() {
        ArrayList<Item> snapshot = new ArrayList<>();
        snapshot.addAll(itemList);
        return snapshot;
    }

    /**
     * Displays current inventory state to the console.
     */
    public void viewInventory() {
        for (Item item : itemList) {
            System.out.printf("%s: %d\n", item.getName(), item.getAmount());
        }
    }
    
    private boolean setAmount(Item item, int amount) {
    	if (!(itemList.contains(item))) {
            System.err.printf("ERROR: item not found in inventory (%s)\n", item.getName());
            return false;
        }
    	item.setAmount(amount);
    	return true;
    }

    /**
     * Increases the current stock of an item in the inventory
     * <p>
     * In order to succeed, the item must exist in the inventory, and the
     * amount to increase by must be greater than zero
     *
     * @param item   The item to increase stock of
     * @param amount The quantity to increase by
     * 
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
     * <p>
     * In order to succeed, the item must exist in the inventory, and the
     * amount to reduce by must be greater than zero and less than or equal to
     * the item's current stock
     *
     * @param item   The item to reduce stock of
     * @param amount The quantity to reduce by
     * 
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
        if (amount > item.getAmount()) {
            System.err.printf("ERROR: amount (%d) greater than current stock of item (%s)\n", amount, item.getName());
            return false;
        }
        item.setAmount(item.getAmount() - amount);
        return true;
    }
    
    public boolean enterManuallySetItem(Item item, int amount) {
    	int oldAmount = item.getAmount();
    	if (setAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "manual adjustment", (amount - oldAmount))));
    		return true;
    	}
    	else {
			System.err.printf("WARNING: could not create transaction for log\n");
			// this code isn't quite correct. if this block is reached, then the
			// operation to subtract from the inventory still worked. this
			// essentially means that an unlogged action has occurred.
    	}
	return false;
    }
    
    /**
     * Records items issued to patients.
     * 
     * @param item   The issued item
     * @param amount The quantity issued
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean enterIssuedItem(Item item, int amount) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "issuance", -amount)))
    			return true;
    		else {
    			System.err.printf("WARNING: could not create transaction for log\n");
    			// this code isn't quite correct. if this block is reached, then the
    			// operation to subtract from the inventory still worked. this
    			// essentially means that an unlogged action has occurred.
    		}
    	}
    	return false;
    }

    /**
     * Records expired items in inventory.
     *
     * @param item   The expired item
     * @param amount The quantity expired
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean enterExpiredItem(Item item, int amount) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "expiration", -amount)))
    			return true;
    		else {
    			System.err.printf("WARNING: could not create transaction for log\n");
    			// this code isn't quite correct. if this block is reached, then the
    			// operation to subtract from the inventory still worked. this
    			// essentially means that an unlogged action has occurred.
    		}
    	}
    	return false;
    }

    /**
     * Records items transfered to another facility.
     *
     * @param item   The transferred item
     * @param amount The quantity transferred
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean enterTransferredItems(Item item, int amount) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "transfered", -amount)))
    			return true;
    		else {
    			System.err.printf("WARNING: could not create transaction for log\n");
    			// this code isn't quite correct. if this block is reached, then the
    			// operation to subtract from the inventory still worked. this
    			// essentially means that an unlogged action has occurred.
    		}
    	}
    	return false;
    }

    /**
     * Records items received from another facility.
     *
     * @param item   The received item
     * @param amount The quantity received
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean enterReceivedItems(Item item, int amount) {
    	if (addAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "received", amount)))
    			return true;
    		else {
    			System.err.printf("WARNING: could not create transaction for log\n");
    			// this code isn't quite correct. if this block is reached, then the
    			// operation to subtract from the inventory still worked. this
    			// essentially means that an unlogged action has occurred.
    		}
    	}
    	return false;
    }
}