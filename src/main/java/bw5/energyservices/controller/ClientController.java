package bw5.energyservices.controller;

import bw5.energyservices.model.Client;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.InvoiceResponseNoClient;
import bw5.energyservices.response.PaginatedClientResponse;
import bw5.energyservices.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @GetMapping("/{currentPage}/{size}/{sortBy}")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedClientResponse<ClientResponse> getAllClients(@RequestParam (defaultValue = "0")int currentPage, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        Page<Client> clients = clientRepository.findAll(PageRequest.of(currentPage, size, Sort.by(sortBy)));

        List<ClientResponse> clientResponses = clients.getContent().stream()
                .map(client -> new ClientResponse(client)).collect(Collectors.toList());

        return new PaginatedClientResponse<>(clientResponses, clients.getTotalPages(), clients.getTotalElements(), currentPage, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ClientResponse createClient(@Valid @RequestBody ClientRequest clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse updateClient(@PathVariable Long id,
            @Valid @RequestBody ClientRequest clientRequestDTO) {
        ClientResponse response = clientService.updateClient(id, clientRequestDTO);
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    // Rispondo con client in modo da avere anche tutti i dati delle fatture
    public Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @GetMapping("/{id}/invoices")
    @ResponseStatus(HttpStatus.OK)
    // Rispondo con le fatture del client per avere tutti i dati
    public List<InvoiceResponseNoClient> getClientInvoices(@PathVariable Long id) {
        return clientService.getClientInvoices(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
