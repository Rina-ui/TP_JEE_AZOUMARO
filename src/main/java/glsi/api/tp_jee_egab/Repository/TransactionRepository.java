package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Compte;
import glsi.api.tp_jee_egab.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.compteSource = :compte OR t.compteDestination = :compte ORDER BY t.dateTransaction DESC")
    List<Transaction> findByCompte(@Param("compte") Compte compte);

    // Transactions d'un compte par période
    @Query("SELECT t FROM Transaction t WHERE (t.compteSource = :compte OR t.compteDestination = :compte) " +
            "AND t.dateTransaction BETWEEN :dateDebut AND :dateFin ORDER BY t.dateTransaction DESC")
    List<Transaction> findByCompteBetweenDates(
            @Param("compte") Compte compte,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin
    );

}
