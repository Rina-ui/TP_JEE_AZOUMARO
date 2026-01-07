package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByMatricule(String name);
    boolean existsByMatricule(String matricule);
}
