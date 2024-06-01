package lk.ijse.gdse.hello_shoes.dto;

import lk.ijse.gdse.hello_shoes.entity.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventoryDTO {
    private String item_code;
    private String item_des;
    private int item_qty;
    private String item_pic;
    private String category;
    private int size;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private double profit_margin;
    private GenderType genderType;
    private String occasion;
    private String sup_code;
}
