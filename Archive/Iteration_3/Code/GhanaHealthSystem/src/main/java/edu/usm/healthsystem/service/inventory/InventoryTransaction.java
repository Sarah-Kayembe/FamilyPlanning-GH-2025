package edu.usm.healthsystem.service.inventory;

import edu.usm.healthsystem.model.familyplanning.Item;
import lombok.*;
import java.time.*;

@Data
@AllArgsConstructor
public class InventoryTransaction {
	
	/*
	 * The timestamp of this transaction
	 */
	private LocalDate timestamp;
	
	/*
	 * The Item represented by this transaction
	 */
	private Item item;
	
	/*
	 * The type of transaction performed (issuance, transfer, expiration, etc.)
	 */
	private String transactionType;
	
	/*
	 * The net change of the Item's stock count as a result of this transaction
	 */
	private int stockChange;
	
	

}
