package edu.usm.healthsystem.client.inventory;

import lombok.*;

/**
 * Represents an inventory item in the health system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    /**
     * The name of the item (e.g., medication, equipment)
     */
    private String name;

    /**
     * The quantity of the item in inventory
     */
    private int amount;
}