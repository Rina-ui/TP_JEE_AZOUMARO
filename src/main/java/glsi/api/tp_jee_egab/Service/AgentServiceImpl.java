package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.AgentDTO;
import glsi.api.tp_jee_egab.DTO.UpdateAgentRequest;
import glsi.api.tp_jee_egab.Model.Agent;
import glsi.api.tp_jee_egab.Repository.AgentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll()
                .stream()
                .map(AgentDTO::fromAgent)
                .collect(Collectors.toList());
    }

    @Override
    public AgentDTO getAgentById(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        return AgentDTO.fromAgent(agent);
    }

    @Override
    @Transactional
    public AgentDTO updateAgent(Long id, UpdateAgentRequest request) {

        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        //mise a jour des champs after le update
        if(request.getEmail() != null) {
            agent.setEmail(request.getEmail());
        }

        if(request.getMatricule() != null) {
            agent.setMatricule(request.getMatricule());
        }

        if(request.getDepartment() != null) {
            agent.setDepartment(request.getDepartment());
        }

        Agent updatedAgent = agentRepository.save(agent);
        return AgentDTO.fromAgent(updatedAgent);
    }

    @Override
    @Transactional
    public boolean deleteAgent(Long id) {

        if (!agentRepository.existsById(id)) {
            throw new RuntimeException("Agent not found");
        }

        agentRepository.deleteById(id);
        return true;
    }
}
