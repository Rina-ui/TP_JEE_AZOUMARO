package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@DiscriminatorValue("CLIENT")
@Data
public class Client extends User{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private Integer numberNationality;

    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;

    public Client() {
        super();
        this.setRole(Role.CLIENT);
    }

}
