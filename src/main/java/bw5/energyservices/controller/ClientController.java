package bw5.energyservices.controller;

import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientNoInvoiceDetailsResponse;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.InvoiceResponse;
import bw5.energyservices.response.PaginatedClientResponse;
import bw5.energyservices.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedClientResponse<ClientResponse> getAllClients(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String q) {
        return clientService.getAllClients(currentPage, size, sortBy, q);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse createClient(@Valid @RequestBody ClientRequest clientRequestDTO) {
        return clientService.createClient(clientRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/{id}/invoices")
    @ResponseStatus(HttpStatus.OK)
    // Rispondo con le fatture del client per avere tutti i dati
    public List<InvoiceResponse> getClientInvoices(@PathVariable Long id) {
        return clientService.getClientInvoices(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

}
