package bw5.energyservices.controller;

import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientNoInvoiceDetailsResponse;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.PaginatedClientResponse;
import bw5.energyservices.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedClientResponse<ClientResponse> getAllClients(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "25") int size, @RequestParam(defaultValue = "companyName") String sortBy,
            @RequestParam(required = false) String q, @RequestParam(required = false) boolean exactSerach) {
        return clientService.getAllClients(currentPage, size, sortBy, q, exactSerach);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@Valid @RequestBody ClientRequest clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable Long id,
            @Valid @RequestBody ClientRequest clientRequestDTO) {
        return clientService.updateClient(id, clientRequestDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Rispondo con client in modo da avere anche tutti i dati delle fatture
    public ClientNoInvoiceDetailsResponse getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

}
