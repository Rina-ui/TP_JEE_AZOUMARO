package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByEmail(String email);
    List<Agent> findByMatriculeContaining(String matricule);

}
