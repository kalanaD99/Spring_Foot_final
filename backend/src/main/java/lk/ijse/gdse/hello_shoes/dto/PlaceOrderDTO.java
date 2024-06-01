package lk.ijse.gdse.hello_shoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceOrderDTO {
    private String cus_code;
    private String cus_name;
    private double net_total;
    private Date purchase_date;
    private String payment_type;
    private String userEmail;
    private List<OrderItemDTO> items;
}
