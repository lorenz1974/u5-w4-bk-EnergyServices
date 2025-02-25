package bw5.energyservices.controller;

import bw5.energyservices.dto.ClientRequestDTO;
import bw5.energyservices.model.Client;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.IdResponse;
import bw5.energyservices.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ClientResponse createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponse response = clientService.updateClient(id, clientRequestDTO);
        return response;
    }

    @GetMapping("/{id}")
    // Rispondo con client in modo da avere anche tutti i dati delle fatture
    public Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
