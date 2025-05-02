package edu.usm.healthsystem.model.familyplanning;

import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, name);
	}
    
    
    
}