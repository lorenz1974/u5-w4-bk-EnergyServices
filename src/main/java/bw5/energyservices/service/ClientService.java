package bw5.energyservices.service;

import bw5.energyservices.model.Client;
import bw5.energyservices.model.Invoice;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.IdResponse;
import bw5.energyservices.response.InvoiceResponseNoClient;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientResponse createClient(@Valid ClientRequest clientRequestDTO) {

        // Verifica che non esista il nome della compagnia
        if (clientRepository.existsByCompanyName(clientRequestDTO.getCompanyName())) {
            throw new EntityExistsException("Company name already exists");
        }

        // Verifica che non esista il numero di partita IVA
        if (clientRepository.existsByVatNumber(clientRequestDTO.getVatNumber())) {
            throw new EntityExistsException("Vat number already exists");
        }

        Client client = new Client();
        BeanUtils.copyProperties(clientRequestDTO, client);
        client = clientRepository.save(client);

        return responseFromEntity(client);

    }

    public ClientResponse updateClient(Long id, ClientRequest clientRequestDTO) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found (update)");
        }
        Client client = clientRepository.findById(id).get();
        BeanUtils.copyProperties(clientRequestDTO, client);
        client = clientRepository.save(client);
        return responseFromEntity(client);
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found (delete)");
        }
        clientRepository.deleteById(id);
    }

    // metodo GET per tutte le clientResponse
    public List<ClientResponse> getAllClients() {
        return responseFromEntityList(clientRepository.findAll());
    }

    public List<InvoiceResponseNoClient> getClientInvoices(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found (get invoices)");
        }
        // Cancello le informazioni del cliente sulle fatture perch*é è ridondante in
        // questo caso
        return getClient(id).getInvoices().stream()
                .map(invoice -> new InvoiceResponseNoClient(invoice.getId(), invoice.getInvoiceNumber(),
                        invoice.getInvoiceDate(), invoice.getInvoiceStatus()))
                .toList();
    }

    //
    // Deve essere creato un DTO che non ridondi le informazioni del cliente dentro
    // ogni fattura
    //
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found (get)"));
    }

    // metodi aggiuntivi
    public ClientResponse responseFromEntity(Client client) {
        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(client, response);
        return response;

    }

    public List<ClientResponse> responseFromEntityList(List<Client> clients) {
        return clients.stream().map(this::responseFromEntity).toList();
    }

}
