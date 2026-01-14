package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.TransactionDTO;
import glsi.api.tp_jee_egab.Model.Compte;
import glsi.api.tp_jee_egab.Model.Transaction;
import glsi.api.tp_jee_egab.Model.TypeTransaction;
import glsi.api.tp_jee_egab.Repository.CompteRepository;
import glsi.api.tp_jee_egab.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CompteRepository compteRepository;


    @Override
    public TransactionDTO versement(String numeroCompte, BigDecimal montant, String description) {

        // Validation du montant
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        // Récupérer le compte
        Compte compte = compteRepository.findByAccountNumber(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte " + numeroCompte + " non trouvé"));

        if (!compte.getActif()) {
            throw new RuntimeException("Le compte est inactif");
        }

        // Mettre à jour le solde
        BigDecimal nouveauSolde = compte.getSold().add(montant);
        compte.setSold(nouveauSolde);
        compteRepository.save(compte);

        // Créer la transaction
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.DEPOT);
        transaction.setAmount(montant);
        transaction.setDescription(description != null ? description : "Versement");
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setCompteDestination(compte);
        transaction.setSoldeApres(nouveauSolde);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionDTO.fromTransaction(savedTransaction);
    }

    @Override
    public TransactionDTO retrait(String numeroCompte, BigDecimal montant, String description) {
        // Validation du montant
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        // Récupérer le compte
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte " + numeroCompte + " non trouvé"));

        // Vérifier que le compte est actif
        if (!compte.isActif()) {
            throw new RuntimeException("Le compte est inactif");
        }

        // Vérifier le solde
        if (compte.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant. Solde actuel: " + compte.getSolde());
        }

        // Mettre à jour le solde
        BigDecimal nouveauSolde = compte.getSolde().subtract(montant);
        compte.setSolde(nouveauSolde);
        compteRepository.save(compte);

        // Créer la transaction
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.RETRAIT);
        transaction.setMontant(montant);
        transaction.setDescription(description != null ? description : "Retrait");
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setCompteSource(compte);  // Le compte qui retire
        transaction.setSoldeApres(nouveauSolde);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionDTO.fromTransaction(savedTransaction);
    }

    @Override
    public TransactionDTO virement(String numeroCompteSource, String numeroCompteDestination, BigDecimal montant, String description) {
        // Validation du montant
        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }

        // Récupérer les comptes
        Compte compteSource = compteRepository.findByNumeroCompte(numeroCompteSource)
                .orElseThrow(() -> new RuntimeException("Compte source " + numeroCompteSource + " non trouvé"));

        Compte compteDestination = compteRepository.findByNumeroCompte(numeroCompteDestination)
                .orElseThrow(() -> new RuntimeException("Compte destination " + numeroCompteDestination + " non trouvé"));

        // Vérifications
        if (!compteSource.isActif()) {
            throw new RuntimeException("Le compte source est inactif");
        }

        if (!compteDestination.isActif()) {
            throw new RuntimeException("Le compte destination est inactif");
        }

        if (compteSource.getId().equals(compteDestination.getId())) {
            throw new RuntimeException("Impossible de faire un virement vers le même compte");
        }

        // Vérifier le solde
        if (compteSource.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Solde insuffisant. Solde actuel: " + compteSource.getSolde());
        }

        // Débiter le compte source
        BigDecimal nouveauSoldeSource = compteSource.getSolde().subtract(montant);
        compteSource.setSolde(nouveauSoldeSource);
        compteRepository.save(compteSource);

        // Créditer le compte destination
        BigDecimal nouveauSoldeDestination = compteDestination.getSolde().add(montant);
        compteDestination.setSolde(nouveauSoldeDestination);
        compteRepository.save(compteDestination);

        // Créer la transaction
        Transaction transaction = new Transaction();
        transaction.setType(TypeTransaction.VIREMENT);
        transaction.setMontant(montant);
        transaction.setDescription(description != null ? description :
                "Virement de " + numeroCompteSource + " vers " + numeroCompteDestination);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setCompteSource(compteSource);
        transaction.setCompteDestination(compteDestination);
        transaction.setSoldeApres(nouveauSoldeSource);  // Solde du compte source après virement

        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionDTO.fromTransaction(savedTransaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByPeriode(String numeroCompte, LocalDateTime dateDebut, LocalDateTime dateFin) {
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte " + numeroCompte + " non trouvé"));

        List<Transaction> transactions = transactionRepository.findByCompteBetweenDates(
                compte, dateDebut, dateFin
        );

        return transactions.stream()
                .map(TransactionDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getAllTransactions(String numeroCompte) {
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte " + numeroCompte + " non trouvé"));

        List<Transaction> transactions = transactionRepository.findByCompte(compte);

        return transactions.stream()
                .map(TransactionDTO::fromTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] genererReleve(String numeroCompte, LocalDateTime dateDebut, LocalDateTime dateFin) {
        //juste une logique metier pour l'instant
        Compte compte = compteRepository.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte " + numeroCompte + " non trouvé"));

        List<Transaction> transactions = transactionRepository.findByCompteBetweenDates(
                compte, dateDebut, dateFin
        );

        // TODO: Implémenter la génération PDF avec iText ou Apache PDFBox
        String releve = "Relevé de compte pour " + numeroCompte + "\n";
        releve += "Période: " + dateDebut + " au " + dateFin + "\n";
        releve += "Nombre de transactions: " + transactions.size() + "\n";

        return releve.getBytes();
    }
    }
}
