package lk.ijse.gdse.hello_shoes.controller;

import lk.ijse.gdse.hello_shoes.dto.CustomerDTO;
import lk.ijse.gdse.hello_shoes.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/health")
    public String healthCheck(){
        System.out.println("Customer Health Test");
        return "Customer Health Test";
    }

    @PostMapping("/save")
    public boolean saveCustomer(@RequestBody CustomerDTO customerDTO){
        System.out.println(customerDTO);
        customerDTO.setCus_code(customerService.genarateteId());
        return customerService.saveCustomer(customerDTO);

    }

    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(customerDTO.getCus_code(),customerDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteCustomer(@RequestPart("cus_code") String id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }
}

