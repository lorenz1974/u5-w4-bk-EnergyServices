package bw5.energyservices.service;

import bw5.energyservices.model.Client;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.dto.ClientRequestDTO;
import bw5.energyservices.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<String> createClient(ClientRequestDTO clientRequestDTO) {

        Client client = new Client();
        BeanUtils.copyProperties(clientRequestDTO, client);
        clientRepository.save(client);
        return ResponseEntity.ok("Client created successfully");


    }

    public ResponseEntity<String> updateClient(Long id, ClientRequestDTO clientRequestDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientRequestDTO, client);
        clientRepository.save(client);
        return ResponseEntity.ok("Client updated successfully");
    }

    public ResponseEntity<String> deleteClient(Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Client deleted successfully");
    }

    //metodo GET per tutte le clientResponse
    public List<ClientResponse> getAllClients() {
        return responseromEntityList(clientRepository.findAll());
    }

    //metodi aggiuntivi
    public ClientResponse responseFromEntity(Client client) {
        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(client, response);
        return response;

    }

    public List<ClientResponse> responseromEntityList(List<Client> clients) {
        return clients.stream().map(this::responseFromEntity).toList();
    }
}
