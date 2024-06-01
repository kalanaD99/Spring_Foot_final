package lk.ijse.gdse.hello_shoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeId {
    private int size;
    private Inventory inventory;
}
