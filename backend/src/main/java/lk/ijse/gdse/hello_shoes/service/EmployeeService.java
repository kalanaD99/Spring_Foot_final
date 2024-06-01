package lk.ijse.gdse.hello_shoes.service;

import lk.ijse.gdse.hello_shoes.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    boolean saveEmployee(EmployeeDTO employeeDTO,String password);
    boolean updateEmployee(String emp_code,EmployeeDTO employeeDTO,String password);
    boolean deleteEmployee(String email);
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO getEmployeeByEmail(String email);
}
