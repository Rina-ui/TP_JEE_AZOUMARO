package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.CompteDTO;
import glsi.api.tp_jee_egab.DTO.UpdateCompteRequest;
import glsi.api.tp_jee_egab.Model.Client;
import glsi.api.tp_jee_egab.Model.Compte;
import glsi.api.tp_jee_egab.Repository.ClientRepository;
import glsi.api.tp_jee_egab.Repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;


    @Override
    public CompteDTO createCompte(Long clientId, CompteDTO compteDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Compte compte = new Compte();
        compte.setAccountNumber(compteDTO.getAccountNumber());
        compte.setSold(compteDTO.getSold());
        compte.setAccount(compteDTO.getAccount());
        compte.setClient(client);

        Compte saved = compteRepository.save(compte);
        return CompteDTO.fromCompte(saved);
    }

    @Override
    public CompteDTO getCompteById(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("account not found"));
        return CompteDTO.fromCompte(compte);
    }

    @Override
    public CompteDTO getCompteByNumero(String numeroCompte) {
        Compte compte = compteRepository.findByAccountNumber(numeroCompte)
                .orElseThrow(() -> new RuntimeException("account not found"));
        return CompteDTO.fromCompte(compte);
    }

    @Override
    public List<CompteDTO> getAllComptes() {
        return compteRepository.findAll().stream()
                .map(CompteDTO::fromCompte)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompteDTO> getComptesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return compteRepository.findByClient(client).stream()
                .map(CompteDTO::fromCompte)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompteDTO> getComptesActifsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return compteRepository.findByClientAndActifTrue(client).stream()
                .map(CompteDTO::fromCompte)
                .collect(Collectors.toList());
    }

    @Override
    public CompteDTO updateCompte(Long id, UpdateCompteRequest request) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("account not found"));

        if (request.getAccountNumber() != null) {
            compte.setAccountNumber(request.getAccountNumber());
        }
        if (request.getSold() != null) {
            compte.setSold(request.getSold());
        }
        if (request.getAccount() != null) {
            compte.setAccount(request.getAccount());
        }

        Compte updated = compteRepository.save(compte);
        return CompteDTO.fromCompte(updated);
    }

    @Override
    public void deleteCompte(Long id) {
        if (!compteRepository.existsById(id)) {
            throw new RuntimeException("account not found");
        }
        compteRepository.deleteById(id);
    }

    @Override
    public CompteDTO activerCompte(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("account not found"));
        compte.setActif(true);
        Compte saved = compteRepository.save(compte);
        return CompteDTO.fromCompte(saved);
    }

    @Override
    public CompteDTO desactiverCompte(Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("account not found"));
        compte.setActif(false);
        Compte saved = compteRepository.save(compte);
        return CompteDTO.fromCompte(saved);
    }
}
