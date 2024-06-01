package lk.ijse.gdse.hello_shoes.controller;

import lk.ijse.gdse.hello_shoes.dto.InventoryDTO;
import lk.ijse.gdse.hello_shoes.entity.GenderType;
import lk.ijse.gdse.hello_shoes.service.InventoryService;
import lk.ijse.gdse.hello_shoes.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Utilities;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/health")
    public String healthCheck(){
        System.out.println("Inventory Health Test");
        return "Inventory Health Test";
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveInventory(
            @RequestPart("item_des")String item_des,
            @RequestPart("item_pic")String item_pic,
            @RequestPart("category")String category,
            @RequestPart("status")String status,
            @RequestPart("genderType")String genderType,
            @RequestPart("occasion")String occasion,
            @RequestPart("sup_code")String sup_code

    ){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(inventoryService.generateId(occasion,genderType));
        inventoryDTO.setItem_des(item_des);
        inventoryDTO.setItem_pic(item_pic);
        inventoryDTO.setCategory(category);
        inventoryDTO.setGenderType(GenderType.valueOf(genderType));
        inventoryDTO.setOccasion(occasion);

        return inventoryService.saveInventory(inventoryDTO,sup_code);
    }


    @PutMapping("/update")
    public boolean updateInventory(
            @RequestPart("item_code")String item_code,
            @RequestPart("item_des")String item_des,
            @RequestPart("item_qty")String item_qty,
            @RequestPart("item_pic")String item_pic,
            @RequestPart("category")String category,
            @RequestPart("size")String size,
            @RequestPart("unit_price_sale")String unit_price_sale,
            @RequestPart("unit_price_buy")String unit_price_buy,
            @RequestPart("expected_profit")String expected_profit,
            @RequestPart("profit_margin")String profit_margin,
            @RequestPart("status")String status
    ){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(item_code);
        inventoryDTO.setItem_des(item_des);
        inventoryDTO.setItem_qty(Integer.parseInt(item_qty));
        inventoryDTO.setItem_pic(UtilMatters.convertBase64(item_pic));
        inventoryDTO.setCategory(category);
        inventoryDTO.setUnit_price_sale(Double.parseDouble(unit_price_sale));
        inventoryDTO.setUnit_price_buy(Double.parseDouble(unit_price_buy));
        inventoryDTO.setExpected_profit(Double.parseDouble(expected_profit));
        inventoryDTO.setProfit_margin(Double.parseDouble(profit_margin));

        return inventoryService.updateInventory(item_code,inventoryDTO);
    }


    @DeleteMapping("/delete")
    public boolean deleteInventory(@RequestPart("id") String id){
        return inventoryService.deleteInventory(id);
    }

    @GetMapping("/getAllInvent")
    public List<InventoryDTO> getAllInventory(){
        return inventoryService.getAllInventory();
    }

    @PostMapping("/updateImg")
    public boolean updateImg(
            @RequestPart("item_code") String item_code,
            @RequestPart("item_code") String item_pic
    ){
        return inventoryService.updateImg(item_code,UtilMatters.convertBase64(item_pic));
    }

}
