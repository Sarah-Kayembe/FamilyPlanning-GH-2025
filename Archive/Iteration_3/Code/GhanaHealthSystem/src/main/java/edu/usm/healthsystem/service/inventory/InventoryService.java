package edu.usm.healthsystem.service.inventory;

import edu.usm.healthsystem.model.familyplanning.Item;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service handling inventory management operations.
 */
public class InventoryService { 

	private static InventoryService instance = null;
	private final List<Item> beginningList = new ArrayList<>();
    private final List<Item> itemList = new ArrayList<>();
    private final List<InventoryTransaction> transactionLog = new ArrayList<>();


    /**
     * Creates a new InventoryService with default items, each initialized to zero stock.
     */
    private InventoryService() {
    	this.addInventoryItem(new Item("LO-FEM", 0));
    	this.addInventoryItem(new Item("Overette", 0));
    	this.addInventoryItem(new Item("Male Condom", 0));
    	this.addInventoryItem(new Item("Female Condom", 0));
    	this.addInventoryItem(new Item("Copper T", 0));
    	this.addInventoryItem(new Item("Micro G", 0));
    	this.addInventoryItem(new Item("Micr - N", 0));
    	this.addInventoryItem(new Item("Postinor 2", 0));
    	this.addInventoryItem(new Item("Sampoo", 0));
    	this.addInventoryItem(new Item("Depo", 0));
    	this.addInventoryItem(new Item("Vasectomy", 0));
    	this.addInventoryItem(new Item("LAM", 0));
    	this.addInventoryItem(new Item("Natural", 0));
    	this.addInventoryItem(new Item("Norigynon", 0));
    }
    
    /**
     * Returns the Singleton instance of the InventoryService.
     * 
     * @return the InventoryService instance
     */
    public static InventoryService getInstance() {
    	if (instance == null) {
    		instance = new InventoryService();
    	}
    	return instance;
    }

    /**
     * Replaces the current item list with a new one.
     * 
     * @param items The new list of items to set
     */
    public void setInventoryItems(List<Item> items) {
        synchronized (itemList) {
            itemList.clear();
            itemList.addAll(items);
        }
        synchronized (beginningList) {
        	beginningList.clear();
        	beginningList.addAll(items);
        }
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
     * Returns a "snapshot" of the beginning state of the InventoryService, which
     * copies all Items in the Inventory and their counts to a new List object.
     *
     * @return a List containing the InvertoryService's state when this method
     * was invoked
     */
    public List<Item> getBeginningSnapshot() {
        ArrayList<Item> snapshot = new ArrayList<>();
        snapshot.addAll(beginningList);
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
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "manual adjustment", (amount - oldAmount), null)));
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
    public boolean enterIssuedItems(Item item, int amount) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "issuance", amount, null)))
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
    public boolean enterExpiredItems(Item item, int amount) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "expiration", amount, null)))
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
     * @param transferLocation The location transferred to
     * 
     * @return true if operation was successful, false otherwise
     */
    public boolean enterTransferredItems(Item item, int amount, String transferLocation) {
    	if (subtractAmount(item, amount)) {
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "transferred", amount, transferLocation)))
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
    		if (transactionLog.add(new InventoryTransaction(LocalDate.now(), item, "received", amount, null)))
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
     * Returns all transactions of type "received".
     *
     * @return a list of received transactions
     */
    public List<InventoryTransaction> getReceivedTransactions() {
        return getTransactionsByType("received");
    }

    /**
     * Returns all transactions of type "transferred".
     *
     * @return a list of transferred transactions
     */
    public List<InventoryTransaction> getTransferredTransactions() {
        return getTransactionsByType("transferred");
    }

    /**
     * Returns all transactions of type "expiration".
     *
     * @return a list of expired transactions
     */
    public List<InventoryTransaction> getExpiredTransactions() {
        return getTransactionsByType("expiration");
    }

    /**
     * Returns all transactions of type "issuance".
     *
     * @return a list of issued transactions
     */
    public List<InventoryTransaction> getIssuedTransactions() {
        return getTransactionsByType("issuance");
    }

    /**
     * Generic method for filtering transactions by type.
     *
     * @param type the transaction type to filter by
     * @return a list of matching transactions
     */
    private List<InventoryTransaction> getTransactionsByType(String type) {
        List<InventoryTransaction> filtered = new ArrayList<>();
        for (InventoryTransaction transaction : transactionLog) {
            if (transaction.getTransactionType().equals(type)) 
                filtered.add(transaction);
        }
        
        return filtered;
    }
}