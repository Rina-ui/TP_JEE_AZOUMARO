package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.AgentDTO;
import glsi.api.tp_jee_egab.DTO.UpdateAgentRequest;
import glsi.api.tp_jee_egab.Service.AgentService; // ✅ Utilise l'interface, pas l'implémentation
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AgentResolver {

    private final AgentService agentService;

    @QueryMapping
    public List<AgentDTO> getAllAgents() {
        return agentService.getAllAgents();
    }

    @QueryMapping
    public AgentDTO getAgentById(@Argument Long id) {
        return agentService.getAgentById(id);
    }

    @MutationMapping
    public AgentDTO updateAgent(@Argument Long id, @Argument UpdateAgentInput input) {
        UpdateAgentRequest request = new UpdateAgentRequest();
        request.setEmail(input.email());
        request.setMatricule(input.matricule());
        request.setDepartment(input.department());

        return agentService.updateAgent(id, request);
    }

    @MutationMapping
    public boolean deleteAgent(@Argument Long id) {
        return agentService.deleteAgent(id);
    }


    record UpdateAgentInput(
            String email,
            String matricule,
            String department
    ) {}
}