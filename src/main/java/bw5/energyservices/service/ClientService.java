package bw5.energyservices.service;

import bw5.energyservices.model.Address;
import bw5.energyservices.model.Client;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.response.ClientNoInvoiceDetailsResponse;
import bw5.energyservices.response.ClientResponse;
import bw5.energyservices.response.InvoiceResponse;
import bw5.energyservices.response.PaginatedClientResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressService addressService;

    //
    // Metodo con ClientRequest
    //
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

        // Setto gli uffici
        // Main address è obbligatorio
        Address mainAddress = addressService.findById(clientRequestDTO.getMainAddressId());
        client.setMainAddress(mainAddress);

        // Se presente, setto l'indirizzo operativo
        if (clientRequestDTO.getOperationalAddressId() != null) {
            Address operationalAddress = addressService.findById(clientRequestDTO.getOperationalAddressId());
            client.setOperationalAddress(operationalAddress);
        }
        client = clientRepository.save(client);

        return responseFromEntity(client);
    }

    //
    // Metodo con Client (per runner)
    //
    public Client createClient(Client client) {
        // Verifica che non esista il nome della compagnia
        if (clientRepository.existsByCompanyName(client.getCompanyName())) {
            throw new EntityExistsException("Company name already exists");
        }
        // Verifica che non esista il numero di partita IVA
        if (clientRepository.existsByVatNumber(client.getVatNumber())) {
            throw new EntityExistsException("Vat number already exists");
        }
        client = clientRepository.save(client);
        return client;
    }

    public ClientResponse updateClient(Long id, ClientRequest clientRequestDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found (update)"));
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

    public PaginatedClientResponse<ClientResponse> getAllClients(int currentPage, int size, String sortBy, String q) {
        Page<Client> clients;
        PageRequest pageRequest = PageRequest.of(currentPage, size, Sort.by(sortBy));
        if (q == null || q.isEmpty()) {
            clients = clientRepository.findAll(pageRequest);
        } else {
            String qLower = q.toLowerCase();
            clients = clientRepository.omniSearch(qLower, pageRequest);
        }

        List<ClientResponse> clientResponses = clients.getContent().stream()
                .map(this::responseFromEntity)
                .collect(Collectors.toList());

        return new PaginatedClientResponse<>(clientResponses, clients.getTotalPages(), clients.getTotalElements(),
                currentPage, size);
    }

    public List<InvoiceResponse> getClientInvoices(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found (get invoices)"));
        // Rimuovo le informazioni del client dalle fatture per evitare ridondanze
        return client.getInvoices().stream()
                .map(invoice -> new InvoiceResponse(invoice))
                .collect(Collectors.toList());
    }

    public ClientNoInvoiceDetailsResponse getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found (get)"));
        // Se presente, rimuovo il riferimento al client nelle fatture per evitare
        // riferimenti circolari
        if (client.getInvoices() != null) {
            client.getInvoices().forEach(invoice -> invoice.setClient(null));
        }
        ClientNoInvoiceDetailsResponse response = new ClientNoInvoiceDetailsResponse(client);
        return response;
    }

    // Metodi di conversione
    public ClientResponse responseFromEntity(Client client) {
        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(client, response);
        return response;
    }

    public List<ClientResponse> responseFromEntityList(List<Client> clients) {
        return clients.stream()
                .map(this::responseFromEntity)
                .collect(Collectors.toList());
    }
}
