package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Agent;
import glsi.api.tp_jee_egab.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface ClientRepository extends JpaRepository<Client, Long> {
Optional<Client> findByFirstName(String name);
    Optional<Client> findByEmail(String email);

}
