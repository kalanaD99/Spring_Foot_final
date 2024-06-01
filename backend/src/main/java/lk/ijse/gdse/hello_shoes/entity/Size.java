package lk.ijse.gdse.hello_shoes.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "size")
@IdClass(SizeId.class)
public class Size {
    @Id
    private int size;

    @JsonManagedReference
    @ManyToOne
    @Id
    private Inventory inventory;
    private int qty;
    private double unit_price_sale;
    private double unit_price_buy;
    private double expected_profit;
    private String status;
    private double profit_margin;
}
