package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Client;
import glsi.api.tp_jee_egab.Model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {

    Optional<Compte> findByAccountNumber(String accountNumber);
    List<Compte> findByClient(Client client);
    List<Compte> findByClientAndActifTrue(Client client);
    List<Compte> findByActifTrue();

}
