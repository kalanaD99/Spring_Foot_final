package lk.ijse.gdse.hello_shoes.dto;

import lk.ijse.gdse.hello_shoes.entity.Gender;
import lk.ijse.gdse.hello_shoes.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private String emp_code;
    private String emp_name;
    private String emp_pro_pic;
    private Gender gender;
    private String status;
    private String designation;
    private Role role;
    private String dob;
    private String joinDate;
    private String attached_branch;
    private String address_line_01;
    private String address_line_02;
    private String address_line_03;
    private String address_line_04;
    private String address_line_05;
    private String contact_no;
    private String email;
    private String informInCaseOfEmergency;
    private String emergencyContact;
    private String password;
}
