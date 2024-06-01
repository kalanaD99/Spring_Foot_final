package lk.ijse.gdse.hello_shoes.service;

import lk.ijse.gdse.hello_shoes.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    boolean saveInventory(InventoryDTO inventoryDTO,String sup_code);
    boolean updateInventory(String id, InventoryDTO inventoryDTO);
    boolean deleteInventory(String  id);
    List<InventoryDTO> getAllInventory();
    String generateId(String occupation, String gender);
    boolean updateImg(String itemCode, String pic);
    List<String> getSize(String itemCode);
}
