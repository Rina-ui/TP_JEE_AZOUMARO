package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

interface ClientRepository extends JpaRepository<Client, Long> {
}
