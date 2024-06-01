package lk.ijse.gdse.hello_shoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "saleDetails")
public class SalesDetails {
    @Id
    private String order_detail_id;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private Inventory inventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Sale sale;

    private String item_name;
    private int qty;
    private int size;
    private double unit_price;
    private double total;
}
