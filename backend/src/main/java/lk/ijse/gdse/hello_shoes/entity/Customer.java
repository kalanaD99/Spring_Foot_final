package lk.ijse.gdse.hello_shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "customer")
public class Customer implements SuperEntity{
    @Id
    private String cus_code;
    private String cus_name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String join_date_as_a_loyalty_customer;
    @Enumerated(EnumType.STRING)
    private Level level;
    private int total_points;
    private String dob;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact_no;
    @Column(unique = true)
    private String email;
    private String recent_purchase_date_and_time;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

}
