package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.ClientDTO;
import glsi.api.tp_jee_egab.DTO.UpdateClientRequest;
import glsi.api.tp_jee_egab.Model.Client;
import glsi.api.tp_jee_egab.Repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client with id " + id + " not found"));

        return ClientDTO.fromClient(client);
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, UpdateClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client with id " + id + " not found"));

        //mise a jour des champs
        if (request.getEmail() != null) {
            client.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) {
            client.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            client.setLastName(request.getLastName());
        }
        if (request.getDateNaissance() != null) {
            client.setDateNaissance(request.getDateNaissance());
        }
        if (request.getCity() != null) {
            client.setCity(request.getCity());
        }
        if (request.getNationality() != null) {
            client.setNationality(request.getNationality());
        }
        if (request.getNumberNationality() != null) {
            client.setNumberNationality(request.getNumberNationality());
        }

        Client updatedClient = clientRepository.save(client);
        return ClientDTO.fromClient(updatedClient);
    }

    @Override
    @Transactional
    public boolean deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client with id " + id + " not found");
        }
        clientRepository.deleteById(id);
        return true;
    }

}
