package lk.ijse.gdse.hello_shoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "inventory")
public class Inventory implements SuperEntity{
    @Id
    private String item_code;
    private String item_des;
    private int item_qty;
    @Column(columnDefinition = "LONGTEXT")
    private String item_pic;
    private String category;
    @Enumerated(EnumType.STRING)
    private GenderType genderType;
    private String occasion;

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SalesDetails> saleDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private Set<SupplierDetails> supplierDetails = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "inventory")
    private List<Size> sizes = new ArrayList<>();
}
