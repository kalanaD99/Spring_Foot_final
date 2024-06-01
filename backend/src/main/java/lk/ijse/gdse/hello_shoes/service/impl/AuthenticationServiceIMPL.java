package lk.ijse.gdse.hello_shoes.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.ijse.gdse.hello_shoes.dao.UserRepo;
import lk.ijse.gdse.hello_shoes.dto.EmployeeDTO;
import lk.ijse.gdse.hello_shoes.dto.UserDTO;
import lk.ijse.gdse.hello_shoes.entity.Employee;
import lk.ijse.gdse.hello_shoes.entity.Role;
import lk.ijse.gdse.hello_shoes.entity.User;
import lk.ijse.gdse.hello_shoes.reqAndrsp.response.JWTAuthResponse;
import lk.ijse.gdse.hello_shoes.reqAndrsp.secure.SignIn;
import lk.ijse.gdse.hello_shoes.reqAndrsp.secure.SignUp;
import lk.ijse.gdse.hello_shoes.service.AuthenticationService;
import lk.ijse.gdse.hello_shoes.service.JWTService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceIMPL implements AuthenticationService {
    private final UserRepo userRepo;
    private final JWTService jwtService;
    private final Mapping mapping;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        Long count = (Long) entityManager.createNativeQuery("SELECT COUNT(*) FROM user").getSingleResult();
        // if user table is empty add initial admin user
        if (count == null || count == 0) {

            UserDTO userDTO = UserDTO.builder().userId(UUID.randomUUID().toString()).
                    email("admin@shoeshop.com").
                    password(passwordEncoder.encode("1234")).
                    role(Role.valueOf("ADMIN")).build();

            User user = userRepo.save(mapping.toUserEntity(userDTO));
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        User userByEmail = userRepo.findByEmail(signIn.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String generatedToken = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(generatedToken).user(mapping.toUserDto(userByEmail)).build();
    }



    @Override
    public UserDTO signUp(SignUp signUp, EmployeeDTO employeeDTO) {
        UserDTO buildUser = UserDTO.builder().userId(UUID.randomUUID().toString()).email(signUp.getEmail()).password(passwordEncoder.encode(signUp.getPassword())).role(Role.valueOf(signUp.getRole())).build();

        User saveUser = mapping.toUserEntity(buildUser);
        Employee employee = new Employee();
        employee.setEmp_code(employeeDTO.getEmp_code());
        saveUser.setEmployee(employee);

        User user = userRepo.save(saveUser);
        String generateToken = jwtService.generateToken(user);
        return buildUser;
    }
}
