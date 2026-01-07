package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("client_type")
@Data
public class Client extends User{

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private LocalDate dateNaissance;
    @NotBlank
    private String city;
    @NotBlank
    private String natinality;
    @NotBlank
    private int numberNationlity;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;
}
