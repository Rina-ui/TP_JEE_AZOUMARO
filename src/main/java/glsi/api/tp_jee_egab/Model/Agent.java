package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agent")
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends User {

    private String matricule;

}
