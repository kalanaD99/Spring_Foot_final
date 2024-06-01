package lk.ijse.gdse.hello_shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier_details")
public class SupplierDetails implements SuperEntity{
    @Id
    private String supplier_details_id;

    @ManyToOne
    @JoinColumn(name = "sup_code")
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "item_code")
    private Inventory inventory;
}
