package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Client;
import glsi.api.tp_jee_egab.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private LocalDate dateNaissance;
    private String city;
    private String nationality;
    private Integer numberNationality;

    public static ClientDTO fromClient(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setRole(client.getRole());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setDateNaissance(client.getDateNaissance());
        dto.setCity(client.getCity());
        dto.setNationality(client.getNationality());
        dto.setNumberNationality(client.getNumberNationality());
        return dto;
    }

}
