package glsi.api.tp_jee_egab.Repository;

import glsi.api.tp_jee_egab.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
