package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    TransactionDTO versement(String numeroCompte, BigDecimal montant, String description);
    TransactionDTO retrait(String numeroCompte, BigDecimal montant, String description);
    TransactionDTO virement(String numeroCompteSource, String numeroCompteDestination,
                            BigDecimal montant, String description);

    List<TransactionDTO> getTransactionsByPeriode(String numeroCompte,
                                                  LocalDateTime dateDebut,
                                                  LocalDateTime dateFin);

    List<TransactionDTO> getAllTransactions(String numeroCompte);

    byte[] genererReleve(String numeroCompte, LocalDateTime dateDebut, LocalDateTime dateFin);

}
