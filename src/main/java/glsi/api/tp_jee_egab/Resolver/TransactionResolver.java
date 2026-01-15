package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.TransactionDTO;
import glsi.api.tp_jee_egab.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
class TransactionResolver {

    private final TransactionService transactionService;

    @MutationMapping
    public TransactionDTO versement(@Argument VersementInput input) {
        return transactionService.versement(
                input.numeroCompte(),
                BigDecimal.valueOf(input.montant()),
                input.description()
        );
    }

    @MutationMapping
    public TransactionDTO retrait(@Argument RetraitInput input) {
        return transactionService.retrait(
                input.numeroCompte(),
                BigDecimal.valueOf(input.montant()),
                input.description()
        );
    }

    @MutationMapping
    public TransactionDTO virement(@Argument VirementInput input) {
        return transactionService.virement(
                input.numeroCompteSource(),
                input.numeroCompteDestination(),
                BigDecimal.valueOf(input.montant()),
                input.description()
        );
    }

    @QueryMapping
    public List<TransactionDTO> getAllTransactions(@Argument String numeroCompte) {
        return transactionService.getAllTransactions(numeroCompte);
    }

    @QueryMapping
    public List<TransactionDTO> getTransactionsByPeriode(
            @Argument String numeroCompte,
            @Argument String dateDebut,
            @Argument String dateFin) {

        LocalDateTime debut = LocalDateTime.parse(dateDebut);
        LocalDateTime fin = LocalDateTime.parse(dateFin);

        return transactionService.getTransactionsByPeriode(numeroCompte, debut, fin);
    }

    // Records pour les inputs
    record VersementInput(String numeroCompte, double montant, String description) {}
    record RetraitInput(String numeroCompte, double montant, String description) {}
    record VirementInput(String numeroCompteSource, String numeroCompteDestination,
                         double montant, String description) {}

}
