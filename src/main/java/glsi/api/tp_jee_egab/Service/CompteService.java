package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.CompteDTO;
import glsi.api.tp_jee_egab.DTO.UpdateCompteRequest;

import java.util.List;

public interface CompteService {

    CompteDTO createCompte(Long clientId, CompteDTO compteDTO);
    CompteDTO getCompteById(Long id);
    CompteDTO getCompteByNumero(String numeroCompte);
    List<CompteDTO> getAllComptes();
    List<CompteDTO> getComptesByClient(Long clientId);
    List<CompteDTO> getComptesActifsByClient(Long clientId);
    CompteDTO updateCompte(Long id, UpdateCompteRequest request);
    void deleteCompte(Long id);
    CompteDTO activerCompte(Long id);
    CompteDTO desactiverCompte(Long id);

}
