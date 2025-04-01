package edu.usm.healthsystem.client.inventory;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Item {

    private String name;
    private int amount;

}
