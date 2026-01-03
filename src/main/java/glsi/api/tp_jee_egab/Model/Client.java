package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User{

    private String firstName;
    private String lastName;
    private LocalDate dateNaissance;
    private String city;
    private String natinality;
    private int numberNationlity;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;
}
