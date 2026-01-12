package glsi.api.tp_jee_egab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest {

    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateNaissance;
    private String city;
    private String nationality;
    private Integer numberNationality;

}
