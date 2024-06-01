package lk.ijse.gdse.hello_shoes.controller;

import lk.ijse.gdse.hello_shoes.dto.EmployeeDTO;
import lk.ijse.gdse.hello_shoes.entity.Gender;
import lk.ijse.gdse.hello_shoes.entity.Role;
import lk.ijse.gdse.hello_shoes.service.EmployeeService;
import lk.ijse.gdse.hello_shoes.util.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/health")
    public String healthCheck(){
        System.out.println("Employee Health Test");
        return "Employee Health Test";
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveEmployee(
            @RequestPart("emp_name")String emp_name,
            @RequestPart("emp_pro_pic")String emp_pro_pic,
            @RequestPart("gender")String gender,
            @RequestPart("status")String status,
            @RequestPart("designation")String designation,
            @RequestPart("role")String role,
            @RequestPart("dob")String dob,
            @RequestPart("joinDate")String joinDate,
            @RequestPart("attached_branch")String attached_branch,
            @RequestPart("address_line_01")String address_line_01,
            @RequestPart("address_line_02")String address_line_02,
            @RequestPart("address_line_03")String address_line_03,
            @RequestPart("address_line_04")String address_line_04,
            @RequestPart("address_line_05")String address_line_05,
            @RequestPart("contact_no")String contact_no,
            @RequestPart("email")String email,
            @RequestPart("informInCaseOfEmergency")String informInCaseOfEmergency,
            @RequestPart("emergencyContact")String emergencyContact,
            @RequestPart("password")String password){


        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmp_code(UUID.randomUUID().toString());
        employeeDTO.setEmp_name(emp_name);
        employeeDTO.setEmp_pro_pic(emp_pro_pic);
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setRole(Role.valueOf(role));
        employeeDTO.setDob(dob);
        employeeDTO.setJoinDate(joinDate);
        employeeDTO.setAttached_branch(attached_branch);
        employeeDTO.setAddress_line_01(address_line_01);
        employeeDTO.setAddress_line_02(address_line_02);
        employeeDTO.setAddress_line_03(address_line_03);
        employeeDTO.setAddress_line_04(address_line_04);
        employeeDTO.setAddress_line_05(address_line_05);
        employeeDTO.setContact_no(contact_no);
        employeeDTO.setEmail(email);
        employeeDTO.setInformInCaseOfEmergency(informInCaseOfEmergency);
        employeeDTO.setEmergencyContact(emergencyContact);

        return employeeService.saveEmployee(employeeDTO,password);
    }

    @PutMapping("/update")
    public boolean updateEmployee(
            @RequestPart("emp_code")String emp_code,
            @RequestPart("emp_name")String emp_name,
            @RequestPart("emp_pro_pic")String emp_pro_pic,
            @RequestPart("gender")String gender,
            @RequestPart("status")String status,
            @RequestPart("designation")String designation,
            @RequestPart("role")String role,
            @RequestPart("dob")String dob,
            @RequestPart("joinDate")String joinDate,
            @RequestPart("attached_branch")String attached_branch,
            @RequestPart("address_line_01")String address_line_01,
            @RequestPart("address_line_02")String address_line_02,
            @RequestPart("address_line_03")String address_line_03,
            @RequestPart("address_line_04")String address_line_04,
            @RequestPart("address_line_05")String address_line_05,
            @RequestPart("contact_no")String contact_no,
            @RequestPart("email")String email,
            @RequestPart("informInCaseOfEmergency")String informInCaseOfEmergency,
            @RequestPart("emergencyContact")String emergencyContact,
            @RequestPart("password")String password){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmp_code(UUID.randomUUID().toString());
        employeeDTO.setEmp_name(emp_name);
        employeeDTO.setEmp_pro_pic(UtilMatters.convertBase64(emp_pro_pic));
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setRole(Role.valueOf(role));
        employeeDTO.setDob(dob);
        employeeDTO.setJoinDate(joinDate);
        employeeDTO.setAttached_branch(attached_branch);
        employeeDTO.setAddress_line_01(address_line_01);
        employeeDTO.setAddress_line_02(address_line_02);
        employeeDTO.setAddress_line_03(address_line_03);
        employeeDTO.setAddress_line_04(address_line_04);
        employeeDTO.setAddress_line_05(address_line_05);
        employeeDTO.setContact_no(contact_no);
        employeeDTO.setEmail(email);
        employeeDTO.setInformInCaseOfEmergency(informInCaseOfEmergency);
        employeeDTO.setEmergencyContact(emergencyContact);

        return employeeService.updateEmployee(emp_code,employeeDTO,password);
    }


    @DeleteMapping("/delete")
    public boolean deleteEmployee(@RequestPart("emp_code")String id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping(produces = "application/json")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
}
