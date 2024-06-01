package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.InventoryRepo;
import lk.ijse.gdse.hello_shoes.dao.SizeRepo;
import lk.ijse.gdse.hello_shoes.dto.SizeDTO;
import lk.ijse.gdse.hello_shoes.entity.Inventory;
import lk.ijse.gdse.hello_shoes.entity.Size;
import lk.ijse.gdse.hello_shoes.service.SizeService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SizeServiceIMPL implements SizeService {
    private SizeRepo sizeRepo;
    private final Mapping mapping;
    private InventoryRepo inventoryRepo;

    @Override
    public boolean saveSize(SizeDTO sizeDTO) {
        Optional<Inventory> inventory = inventoryRepo.findById(sizeDTO.getItem_code());

        if (isExitId(String.valueOf(sizeDTO.getSize()),sizeDTO.getItem_code())){
            if (inventory.isPresent()){
                Inventory inventory1 = inventory.get();
                Size size = mapping.toSize(sizeDTO);
                try {
                    sizeRepo.save(size);
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isExitId(String sizeId, String itemCode) {
        if (sizeRepo.countBySizeIdAndItemCode(sizeId,itemCode) !=0){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public boolean updateSize(String id, SizeDTO sizeDTO) {
        return false;
    }

    @Override
    public boolean deleteSize(String item_code, String sizeId) {
        int isDelete = sizeRepo.deleteByItemCodeAndSizeId(item_code, sizeId);

        if (isDelete !=0){
            return true;
        }
        return false;
    }

    @Override
    public List<SizeDTO> getAllSize() {
        return mapping.getSizeDTOList(sizeRepo.findAll());
    }

    @Override
    public List<String> getItemIds() {
        return inventoryRepo.getItemIds();
    }

    @Override
    public Size getDataWithSizeAndItemID(String itemCode, String size) {
        return sizeRepo.getDataWithSizeAndItemID(itemCode,size);
    }
}
