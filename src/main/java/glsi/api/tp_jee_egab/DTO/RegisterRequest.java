package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    public String getDepartment;
    private String email;
    private String password;
    private Role role;

    private String firstName;
    private String lastName;

    private String matricule;

    private LocalDate dateNaissance;
    private String city;
    private String nationality;
    private Integer numberNationality;

}
