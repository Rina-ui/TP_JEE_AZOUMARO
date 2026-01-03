package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateNaissance;
    private Role role;
    private String city;
    private String natinality;
    private int numberNationlity;
}
