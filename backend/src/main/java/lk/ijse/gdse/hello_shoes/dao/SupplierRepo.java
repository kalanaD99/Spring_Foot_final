package lk.ijse.gdse.hello_shoes.dao;

import lk.ijse.gdse.hello_shoes.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Supplier,String> {
    Optional<Supplier> findByEmail(String email);
    @Query(value = "SELECT sup_code FROM supplier ORDER BY sup_code DESC LIMIT 1", nativeQuery = true)
    String findLastId();


}
