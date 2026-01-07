package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agent")
@NoArgsConstructor
@AllArgsConstructor
@Data
@DiscriminatorValue("agent_type")
public class Agent extends User {

    @NotBlank
    private String department;
    @NotBlank
    private String matricule;

}
