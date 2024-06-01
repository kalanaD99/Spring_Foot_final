package lk.ijse.gdse.hello_shoes.dao;

import lk.ijse.gdse.hello_shoes.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    Optional<Customer> findByEmail(String email);
    @Query(value = "SELECT cus_code FROM customer ORDER BY cus_code DESC LIMIT 1", nativeQuery = true)
    String findById();
}
