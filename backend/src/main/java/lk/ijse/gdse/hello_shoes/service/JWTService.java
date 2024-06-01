package lk.ijse.gdse.hello_shoes.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean validToken(String token, UserDetails userDetails);
}
