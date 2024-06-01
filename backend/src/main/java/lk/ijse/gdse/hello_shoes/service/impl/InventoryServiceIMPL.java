package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.InventoryRepo;
import lk.ijse.gdse.hello_shoes.dao.SizeRepo;
import lk.ijse.gdse.hello_shoes.dao.SupplierRepo;
import lk.ijse.gdse.hello_shoes.dto.InventoryDTO;
import lk.ijse.gdse.hello_shoes.entity.Inventory;
import lk.ijse.gdse.hello_shoes.entity.Supplier;
import lk.ijse.gdse.hello_shoes.entity.SupplierDetails;
import lk.ijse.gdse.hello_shoes.service.InventoryService;
import lk.ijse.gdse.hello_shoes.service.SupplierDetailsService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class InventoryServiceIMPL implements InventoryService {
    private final InventoryRepo inventoryRepo;
    private final Mapping mapping;
    private SupplierRepo supplierRepo;
    private SupplierDetailsService supplierDetailsService;
    private final SizeRepo sizeRepo;


    @Override
    public boolean saveInventory(InventoryDTO inventoryDTO, String sup_code) {
        Inventory save = inventoryRepo.save(mapping.toInventoryEntity(inventoryDTO));
        Optional<Supplier> supplier = supplierRepo.findById(sup_code);
        if (supplier.isPresent()){
            SupplierDetails supplierDetails = new SupplierDetails();
            supplierDetails.setSupplier_details_id(UUID.randomUUID().toString());
            supplierDetails.setInventory(save);
            supplierDetails.setSupplier(supplier.get());
            supplierDetailsService.save(supplierDetails);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateInventory(String id, InventoryDTO inventoryDTO) {
        Optional<Inventory> inventory = inventoryRepo.findById(id);
        if (inventory.isPresent()){
            inventory.get().setItem_des(inventoryDTO.getItem_des());
            inventory.get().setItem_pic(inventoryDTO.getItem_pic());
            inventory.get().setCategory(inventoryDTO.getCategory());

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteInventory(String id) {
        Optional<Inventory> inventory = inventoryRepo.findById(id);
        if (inventory.isPresent()){
            inventoryRepo.delete(inventory.get());
            return true;
        }
        return false;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return mapping.toInventoryList(inventoryRepo.findAll());
    }

    @Override
    public String generateId(String occupation, String gender) {
        String occ="";
        switch (occupation){
            case "Casual": occ = "CS";break;
            case "Formal": occ = "FS";break;
            case "Sport": occ = "SS";break;
            case "Industrial": occ = "IS";break;
        }
        switch (gender){
            case "MAN": occ = occ + "M";break;
            case "WOMEN": occ = occ + "W";break;
        }

        String lastId = inventoryRepo.findLastId(occ);
        if (lastId == null){
            return occ + "001";
        }else {
            int id = Integer.parseInt(lastId.substring(3))+1;
            return occ + String.format("%04d",id);
        }
    }

    @Override
    public boolean updateImg(String itemCode, String pic) {
        Optional<Inventory> inventory = inventoryRepo.findById(itemCode);
        if (inventory.isPresent()){
            inventory.get().setItem_pic(pic);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<String> getSize(String itemCode) {
        return inventoryRepo.getSize(itemCode);
    }
}
