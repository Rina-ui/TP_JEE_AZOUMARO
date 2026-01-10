package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Role;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Role role;
}
