package glsi.api.tp_jee_egab.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
