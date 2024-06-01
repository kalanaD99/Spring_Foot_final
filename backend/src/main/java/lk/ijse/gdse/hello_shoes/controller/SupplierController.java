package lk.ijse.gdse.hello_shoes.controller;

import lk.ijse.gdse.hello_shoes.dto.SupplierDTO;
import lk.ijse.gdse.hello_shoes.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @GetMapping("/health")
    public String healthCheck(){
        System.out.println("Supplier Health Test");
        return "Supplier Health Test";
    }

    @PostMapping("/save")
    public boolean saveSupplier(@RequestBody SupplierDTO supplierDTO){
        System.out.println(supplierDTO);
        supplierDTO.setSup_code(supplierService.generateId());
        return supplierService.saveSupplier(supplierDTO);
    }

    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateSupplier(@RequestBody SupplierDTO supplierDTO){
        return supplierService.updateSupplier(supplierDTO.getSup_code(),supplierDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteSupplier(@RequestPart("sup_code") String id){
        return supplierService.deleteSupplier(id);
    }

    @GetMapping(produces = "application/json")
    public List<SupplierDTO> getAllSupplier(){
        return supplierService.getAllSupplier();
    }
}
