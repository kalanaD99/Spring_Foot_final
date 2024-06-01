package lk.ijse.gdse.hello_shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class Supplier implements SuperEntity{
    @Id
    private String sup_code;
    private String sup_name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String address_line_06;
    private String contact_no_01;
    private String contact_no_02;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<SupplierDetails> supplierDetails = new HashSet<>();
}
