package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.SupplierDetailsRepo;
import lk.ijse.gdse.hello_shoes.entity.SupplierDetails;
import lk.ijse.gdse.hello_shoes.service.SupplierDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SupplierDetailsServiceIMPL implements SupplierDetailsService {
    private SupplierDetailsRepo supplierDetailsRepo;
    @Override
    public boolean save(SupplierDetails supplierDetails) {
        SupplierDetails save = supplierDetailsRepo.save(supplierDetails);
        return save != null;
    }
}
