package lk.ijse.gdse.hello_shoes.reqAndrsp.secure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignIn {
    private String email;
    private String password;
}
