package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.AgentDTO;
import glsi.api.tp_jee_egab.DTO.UpdateAgentRequest;

import java.util.List;

public interface AgentService {

    List<AgentDTO> getAllAgents();
    AgentDTO getAgentById(Long id);
    AgentDTO updateAgent(Long id, UpdateAgentRequest request);
    boolean deleteAgent(Long id);

}
