package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Agent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private Long id;
    private String email;
    private String matricule;
    private String department;

    public static AgentDTO fromAgent(Agent agent) {
        AgentDTO dto = new AgentDTO();
        dto.setId(agent.getId());
        dto.setEmail(agent.getEmail());
        dto.setMatricule(agent.getMatricule());
        dto.setDepartment(agent.getDepartment());
        return dto;
    }

}
