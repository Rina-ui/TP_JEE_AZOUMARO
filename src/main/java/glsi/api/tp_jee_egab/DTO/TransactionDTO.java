package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Compte;
import glsi.api.tp_jee_egab.Model.Transaction;
import glsi.api.tp_jee_egab.Model.TypeTransaction;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private TypeTransaction typeTransaction;
    private BigDecimal amount;
    private LocalDateTime dateTransaction;
    private Compte compteSource;
    private Compte compteDestination;

    public static TransactionDTO fromTransaction (Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDateTransaction(transaction.getDateTransaction());
        dto.setTypeTransaction(transaction.getTypeTransaction());
        dto.setCompteDestination(transaction.getCompteDestination());
        dto.setCompteSource(transaction.getCompteSource());

        return dto;
    }


}
