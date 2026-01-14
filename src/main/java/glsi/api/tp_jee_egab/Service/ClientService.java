package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.ClientDTO;
import glsi.api.tp_jee_egab.DTO.UpdateClientRequest;

import java.util.List;

public interface ClientService {

    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long id);
    ClientDTO updateClient(Long id, UpdateClientRequest request);
    boolean deleteClient(Long id);

}
