package lk.ijse.gdse.hello_shoes.controller;

import lk.ijse.gdse.hello_shoes.dto.PlaceOrderDTO;
import lk.ijse.gdse.hello_shoes.entity.Inventory;
import lk.ijse.gdse.hello_shoes.projectInterface.MostSoldItemProjection;
import lk.ijse.gdse.hello_shoes.service.InventoryService;
import lk.ijse.gdse.hello_shoes.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sale")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SaleController {
    private final SaleService saleService;
    private final InventoryService inventoryService;

    @GetMapping("/health")
    public String health(){
        return "OK";
    }

    @GetMapping("/getItemIds")
    public List<String> getItemIds(){
        return saleService.getItemIds();
    }

    @GetMapping("getItem/{itemCode}")
    public Inventory getItem(@PathVariable String itemCode){
        return saleService.getItem(itemCode);
    }

    @GetMapping("getItemSize/{itemCode}")
    public List<String> getSize(@PathVariable String itemCode){
        return inventoryService.getSize(itemCode);
    }

    @PostMapping("/placeOrder")
    public boolean placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO){
        System.out.println(placeOrderDTO);
        return saleService.placeOrder(placeOrderDTO);
    }

    @GetMapping("/getTotalSale/{date}")
    public Double getTotalSale(@PathVariable String date){
        return saleService.getTotalSale(date);
    }

    @GetMapping("/getTotalProfit/{date}")
    public Double getTotalProfit(@PathVariable String date){
        return saleService.getTotalProfit(date);
    }

    @GetMapping("/getMostSaleItem/{date}")
    public MostSoldItemProjection getMostSaleItem(@PathVariable String date){
        return saleService.getMostSaleItem(date);
    }
}
