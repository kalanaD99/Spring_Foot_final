package lk.ijse.gdse.hello_shoes.service;

import lk.ijse.gdse.hello_shoes.dto.PlaceOrderDTO;
import lk.ijse.gdse.hello_shoes.entity.Inventory;
import lk.ijse.gdse.hello_shoes.projectInterface.MostSoldItemProjection;

import java.util.List;

public interface SaleService {
    List<String> getItemIds();
    Inventory getItem(String itemCode);

    boolean placeOrder(PlaceOrderDTO placeOrderDTO) ;
    Double getTotalSale(String date);

    Double getTotalProfit(String date);

    MostSoldItemProjection getMostSaleItem(String date);
}
