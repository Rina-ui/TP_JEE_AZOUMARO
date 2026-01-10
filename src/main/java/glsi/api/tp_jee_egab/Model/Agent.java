package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agents")
@AllArgsConstructor
@Data
@DiscriminatorValue("AGENT")
public class Agent extends User {

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String matricule;

    public Agent() {
        super();
        this.setRole(Role.AGENT);
    }

}
