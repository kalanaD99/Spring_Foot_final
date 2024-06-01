package lk.ijse.gdse.hello_shoes.dao;

import lk.ijse.gdse.hello_shoes.entity.SalesDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDetailsRepo extends JpaRepository<SalesDetails, String > {

}
