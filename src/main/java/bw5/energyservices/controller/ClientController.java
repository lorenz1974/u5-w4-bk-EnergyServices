package bw5.energyservices.controller;

import bw5.energyservices.model.Client;
import bw5.energyservices.model.Invoice;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.IdResponse;
import bw5.energyservices.response.InvoiceResponseNoClient;
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
