package lk.ijse.gdse.hello_shoes.dto;

import lk.ijse.gdse.hello_shoes.entity.Customer;
import lk.ijse.gdse.hello_shoes.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDTO {
    private String order_id;
    private String cus_name;
    private double total_price;
    private Date purchase_date;
    private String payment_method;
    private double added_points;
    private String cashier_name;
    private User user;
    private Customer customer;
}
