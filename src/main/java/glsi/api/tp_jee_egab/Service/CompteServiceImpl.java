package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.CompteDTO;
import glsi.api.tp_jee_egab.DTO.UpdateCompteRequest;
import glsi.api.tp_jee_egab.Repository.ClientRepository;
import glsi.api.tp_jee_egab.Repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;


    @Override
    public CompteDTO createCompte(Long clientId, CompteDTO compteDTO) {
        return null;
    }

    @Override
    public CompteDTO getCompteById(Long id) {
        return null;
    }

    @Override
    public CompteDTO getCompteByNumero(String numeroCompte) {
        return null;
    }

    @Override
    public List<CompteDTO> getAllComptes() {
        return List.of();
    }

    @Override
    public List<CompteDTO> getComptesByClient(Long clientId) {
        return List.of();
    }

    @Override
    public List<CompteDTO> getComptesActifsByClient(Long clientId) {
        return List.of();
    }

    @Override
    public CompteDTO updateCompte(Long id, UpdateCompteRequest request) {
        return null;
    }

    @Override
    public void deleteCompte(Long id) {

    }

    @Override
    public CompteDTO activerCompte(Long id) {
        return null;
    }

    @Override
    public CompteDTO desactiverCompte(Long id) {
        return null;
    }
}
