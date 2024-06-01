package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.EmployeeRepo;
import lk.ijse.gdse.hello_shoes.dao.UserRepo;
import lk.ijse.gdse.hello_shoes.dto.EmployeeDTO;
import lk.ijse.gdse.hello_shoes.entity.Employee;
import lk.ijse.gdse.hello_shoes.entity.User;
import lk.ijse.gdse.hello_shoes.reqAndrsp.secure.SignUp;
import lk.ijse.gdse.hello_shoes.service.AuthenticationService;
import lk.ijse.gdse.hello_shoes.service.EmployeeService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lk.ijse.gdse.hello_shoes.util.UtilMatters;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    private EmployeeRepo employeeRepo;
    private final UserRepo userRepo;
    private final Mapping mapping;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO, String password) {
        Employee employee = mapping.toEmployeeEntity(employeeDTO);
        EmployeeDTO saveEmployee = mapping.toEmployeeDTO(employeeRepo.save(employee));

        SignUp signUp = new SignUp();
        signUp.setEmail(employeeDTO.getEmail());
        signUp.setPassword(password);
        signUp.setRole(String.valueOf(employeeDTO.getRole()));

        authenticationService.signUp(signUp,employeeDTO);
        return saveEmployee != null;
    }

    @Override
    public boolean updateEmployee(String emp_code, EmployeeDTO employeeDTO, String password) {
        Optional<Employee> employee = employeeRepo.findById(emp_code);
        String email = employee.get().getEmail();


        if (employee.isPresent()){
            employee.get().setEmp_name(employeeDTO.getEmp_name());
            employee.get().setEmp_pro_pic(UtilMatters.convertBase64(employeeDTO.getEmp_pro_pic()));
            employee.get().setGender(employeeDTO.getGender());
            employee.get().setStatus(employeeDTO.getStatus());
            employee.get().setDesignation(employeeDTO.getDesignation());
            employee.get().setRole(employeeDTO.getRole());
            employee.get().setDob(employeeDTO.getDob());
            employee.get().setJoinDate(employeeDTO.getJoinDate());
            employee.get().setAttached_branch(employeeDTO.getAttached_branch());
            employee.get().setAddress_line_01(employeeDTO.getAddress_line_01());
            employee.get().setAddress_line_02(employeeDTO.getAddress_line_02());
            employee.get().setAddress_line_03(employeeDTO.getAddress_line_03());
            employee.get().setAddress_line_04(employeeDTO.getAddress_line_04());
            employee.get().setAddress_line_05(employeeDTO.getAddress_line_05());
            employee.get().setContact_no(employeeDTO.getContact_no());
            employee.get().setEmail(employeeDTO.getEmail());
            employee.get().setInformInCaseOfEmergency(employeeDTO.getInformInCaseOfEmergency());
            employee.get().setEmergencyContact(employeeDTO.getEmergencyContact());

            Optional<User> user = userRepo.findByEmail(email);
            if (user.isPresent()){
                user.get().setEmail(employeeDTO.getEmail());
                user.get().setPassword(passwordEncoder.encode(password));
                user.get().setRole(employeeDTO.getRole());
            }
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String email) {
        Optional<Employee> employee = employeeRepo.findByEmail(email);
        Optional<User> user = userRepo.findByEmail(email);
        if (employee.isPresent() && user.isPresent()){
            userRepo.delete(user.get());
            employeeRepo.delete(employee.get());
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return mapping.toEmployeeList(employeeRepo.findAll());
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        return null;
    }
}
