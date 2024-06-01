package lk.ijse.gdse.hello_shoes.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.hello_shoes.dao.*;
import lk.ijse.gdse.hello_shoes.dto.OrderItemDTO;
import lk.ijse.gdse.hello_shoes.dto.PlaceOrderDTO;
import lk.ijse.gdse.hello_shoes.entity.*;
import lk.ijse.gdse.hello_shoes.projectInterface.MostSoldItemProjection;
import lk.ijse.gdse.hello_shoes.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SaleServiceIMPL implements SaleService {
    private InventoryRepo inventoryRepo;
    private SaleRepo saleRepo;
    private SaleDetailsRepo saleDetailsRepo;
    private UserRepo userRepo;
    private CustomerRepo customerRepo;
    @Override
    public List<String> getItemIds() {
        return inventoryRepo.getItemIds();
    }

    @Override
    public Inventory getItem(String itemCode) {
        Optional<Inventory> inventory = inventoryRepo.findById(itemCode);
        if(inventory.isPresent()){
            return inventory.get();
        }
        return null;
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO placeOrderDTO) {
        Optional<User> user = userRepo.findByEmail(placeOrderDTO.getUserEmail());
        Optional<Customer> customer = customerRepo.findById(placeOrderDTO.getCus_code());

        if (!user.isPresent()) {
            return false;
        }

        System.out.println(">>>>>>>>>>>>>>>> : "+user.get().getEmployee().getEmp_name());

        Sale sale = new Sale();
        sale.setOrder_id(UUID.randomUUID().toString());
        sale.setUser(user.get());
        sale.setCashier_name(user.get().getEmployee().getEmp_name());

        if (customer.isPresent()) {
            sale.setCustomer(customer.get());

            if (placeOrderDTO.getNet_total() > 800){
                sale.setAdded_points(1);
                customer.get().setTotal_points(customer.get().getTotal_points() + 1);

                if (customer.get().getTotal_points() < 50) {
                    customer.get().setLevel(Level.NEW);
                }

                if (customer.get().getTotal_points() >= 50 & customer.get().getTotal_points() <= 99) {
                    customer.get().setLevel(Level.BRONZE);
                }

                if (customer.get().getTotal_points() >= 100 & customer.get().getTotal_points() <= 199) {
                    customer.get().setLevel(Level.SILVER);
                }

                if (customer.get().getTotal_points() >= 200 & customer.get().getTotal_points() <= 299) {
                    customer.get().setLevel(Level.GOLD);
                }
            }
        } else {
            sale.setCustomer(null);
        }

        sale.setCus_name(placeOrderDTO.getCus_name());
        sale.setTotal_price(placeOrderDTO.getNet_total());
        sale.setPurchase_date(new Date());
        sale.setPayment_method(placeOrderDTO.getPayment_type());

        Sale savedSale = saleRepo.save(sale);

        List<SalesDetails> saleDetails = new ArrayList<>();
        for (OrderItemDTO item : placeOrderDTO.getItems()) {
            Optional<Inventory> inventory = inventoryRepo.findById(item.getItem_code());

            SalesDetails saleDetail = new SalesDetails();
            saleDetail.setOrder_detail_id(UUID.randomUUID().toString());
            saleDetail.setInventory(inventory.get());
            saleDetail.setSale(savedSale);
            saleDetail.setItem_name(item.getItem_name());
            saleDetail.setQty(item.getQuantity());
            saleDetail.setSize(item.getItem_size());
            saleDetail.setUnit_price(item.getUnit_price());
            saleDetail.setTotal(item.getTotal_price());

            saleDetails.add(saleDetail);
        }
        saleDetailsRepo.saveAll(saleDetails);

        for (SalesDetails saleDetail : saleDetails) {
            Inventory inventory = saleDetail.getInventory();
            int soldQuantity = saleDetail.getQty();
            int sizeToReduce = saleDetail.getSize();

            for (Size size : inventory.getSizes()) {
                if (size.getSize() == sizeToReduce) {
                    int currentQuantity = size.getQty();
                    size.setQty(currentQuantity - soldQuantity);
                    break;
                }
            }
        }

        inventoryRepo.saveAll(saleDetails.stream()
                .map(SalesDetails::getInventory)
                .collect(Collectors.toSet()));


        return true;

    }

    @Override
    public Double getTotalSale(String date) {
        return saleRepo.getTotalSale(date);
    }

    @Override
    public Double getTotalProfit(String date) {
        return saleRepo.getTotalProfit(date);
    }

    @Override
    public MostSoldItemProjection getMostSaleItem(String date) {
        return saleRepo.getMostSaleItem(date);
    }
}
