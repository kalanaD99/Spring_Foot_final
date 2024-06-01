package lk.ijse.gdse.hello_shoes.service;

import lk.ijse.gdse.hello_shoes.dto.SizeDTO;
import lk.ijse.gdse.hello_shoes.entity.Size;

import java.util.List;

public interface SizeService {
    boolean saveSize(SizeDTO sizeDTO);
    boolean updateSize(String id,SizeDTO sizeDTO);
    boolean deleteSize(String item_code, String sizeId);
    List<SizeDTO> getAllSize();
    List<String> getItemIds();
    Size getDataWithSizeAndItemID(String itemCode, String size);
}
