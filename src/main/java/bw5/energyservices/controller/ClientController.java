package bw5.energyservices.controller;

import bw5.energyservices.dto.ClientRequestDTO;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor

public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO clientRequestDTO) {
        return clientService.updateClient(id, clientRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}
