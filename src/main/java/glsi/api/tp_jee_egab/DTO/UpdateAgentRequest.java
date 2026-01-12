package glsi.api.tp_jee_egab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAgentRequest {
    private String email;
    private String matricule;
    private String department;
}

