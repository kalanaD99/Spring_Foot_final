package lk.ijse.gdse.hello_shoes.reqAndrsp.response;

import lk.ijse.gdse.hello_shoes.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTAuthResponse {
    private String token;
    private UserDTO user;
}
