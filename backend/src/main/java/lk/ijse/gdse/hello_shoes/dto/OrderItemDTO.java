package lk.ijse.gdse.hello_shoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private String item_code;
    private String item_name;
    private int item_size;
    private int quantity;
    private double unit_price;
    private double total_price;
}
