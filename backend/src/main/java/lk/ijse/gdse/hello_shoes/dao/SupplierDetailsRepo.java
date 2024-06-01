package lk.ijse.gdse.hello_shoes.dao;

import lk.ijse.gdse.hello_shoes.entity.SupplierDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDetailsRepo extends JpaRepository<SupplierDetails,String > {
}
