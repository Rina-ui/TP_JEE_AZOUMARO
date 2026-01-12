package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.ClientDTO;
import glsi.api.tp_jee_egab.DTO.UpdateClientRequest;
import glsi.api.tp_jee_egab.Service.ClientServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
class ClientResolver {

    private final ClientServiceImpl clientService;

    ClientResolver(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @QueryMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @QueryMapping
    public ClientDTO getClientById(@Argument Long id) {
        return clientService.getClientById(id);
    }

    @QueryMapping
    public ClientDTO updateClient(@Argument Long id, @Argument UpdateClientInput input) {
        UpdateClientRequest request = new UpdateClientRequest();
        request.setEmail(input.email);
        request.setFirstName(input.firstName);
        request.setLastName(input.lastName);

        if (input.dateNaissance() != null) {
            request.setDateNaissance(LocalDate.parse(input.dateNaissance()));
        }

        request.setCity(input.city());
        request.setNationality(input.nationality());
        request.setNumberNationality(input.numberNationality());

        return clientService.updateClient(id, request);
    }

    @QueryMapping
    public boolean deleteClient(@Argument Long id) {
        return clientService.deleteClient(id);
    }

    record UpdateClientInput(
            String email,
            String firstName,
            String lastName,
            String dateNaissance,
            String city,
            String nationality,
            Integer numberNationality
    ) {}
}
