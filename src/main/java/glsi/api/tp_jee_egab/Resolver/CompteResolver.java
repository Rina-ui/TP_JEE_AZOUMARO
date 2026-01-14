package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.CompteDTO;
import glsi.api.tp_jee_egab.DTO.UpdateCompteRequest;
import glsi.api.tp_jee_egab.Service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
class CompteResolver {

    private final CompteService compteService;

    @QueryMapping
    public List<CompteDTO> getAllComptes() {
        return compteService.getAllComptes();
    }

    @QueryMapping
    public CompteDTO getCompteById(@Argument Long id) {
        return compteService.getCompteById(id);
    }

    @QueryMapping
    public CompteDTO getCompteByNumero(@Argument String numeroCompte) {
        return compteService.getCompteByNumero(numeroCompte);
    }

    @QueryMapping
    public List<CompteDTO> getComptesByClient(@Argument Long clientId) {
        return compteService.getComptesByClient(clientId);
    }

    @QueryMapping
    public List<CompteDTO> getComptesActifsByClient(@Argument Long clientId) {
        return compteService.getComptesActifsByClient(clientId);
    }


    @MutationMapping
    public CompteDTO createCompte(@Argument Long clientId, @Argument CompteDTO input) {
        return compteService.createCompte(clientId, input);
    }

    @MutationMapping
    public CompteDTO updateCompte(@Argument Long id, @Argument UpdateCompteRequest input) {
        return compteService.updateCompte(id, input);
    }

    @MutationMapping
    public Boolean deleteCompte(@Argument Long id) {
        compteService.deleteCompte(id);
        return true;
    }

    @MutationMapping
    public CompteDTO activerCompte(@Argument Long id) {
        return compteService.activerCompte(id);
    }

    @MutationMapping
    public CompteDTO desactiverCompte(@Argument Long id) {
        return compteService.desactiverCompte(id);
    }

}
