package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.CustomerRepo;
import lk.ijse.gdse.hello_shoes.dto.CustomerDTO;
import lk.ijse.gdse.hello_shoes.entity.Customer;
import lk.ijse.gdse.hello_shoes.service.CustomerService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    private final CustomerRepo customerRepo;
    private final Mapping mapping;

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
//        System.out.println("cccccc   "+ customerDTO);
        Optional<Customer> byEmail = customerRepo.findByEmail(customerDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        } else {
            customerRepo.save(mapping.toCustomerEntity(customerDTO));
            return true;
        }
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()){
            customer.get().setCus_name(customerDTO.getCus_name());
            customer.get().setGender(customerDTO.getGender());
            customer.get().setJoin_date_as_a_loyalty_customer(customerDTO.getJoin_date_as_a_loyalty_customer());
            customer.get().setLevel(customerDTO.getLevel());
            customer.get().setTotal_points(customerDTO.getTotal_points());
            customer.get().setDob(customerDTO.getDob());
            customer.get().setAddress_line_01(customerDTO.getAddress_line_01());
            customer.get().setAddress_line_02(customerDTO.getAddress_line_02());
            customer.get().setAddress_line_03(customerDTO.getAddress_line_03());
            customer.get().setAddress_line_04(customerDTO.getAddress_line_04());
            customer.get().setAddress_line_05(customerDTO.getAddress_line_05());
            customer.get().setContact_no(customerDTO.getContact_no());
            customer.get().setEmail(customerDTO.getEmail());
            customer.get().setRecent_purchase_date_and_time(customerDTO.getRecent_purchase_date_and_time());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerRepo.deleteById(id);
        return true;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return mapping.toCustomerList(customerRepo.findAll());
    }

    @Override
    public String genarateteId() {
        if (customerRepo.findById()== null){
            return "C0001";
        }

        String num = customerRepo.findById().substring(1);
        int lastNumber = Integer.parseInt(num);
        int nextNumber = lastNumber + 1;
        String nextId = "C" + String.format("%04d", nextNumber);
        return nextId;
    }

}
