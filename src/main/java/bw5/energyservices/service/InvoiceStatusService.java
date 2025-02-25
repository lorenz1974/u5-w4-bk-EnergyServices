package bw5.energyservices.service;

import bw5.energyservices.model.InvoiceStatus;
import bw5.energyservices.repository.InvoiceStatusRepository;
import bw5.energyservices.request.InvoiceStatusRequest;
import bw5.energyservices.response.InvoiceStatusResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class InvoiceStatusService {

    private final InvoiceStatusRepository invoiceStatusRepository;

    public InvoiceStatusResponse createInvoiceStatus(@Valid InvoiceStatusRequest invoiceStatusRequest) {

        // Verifica che non esista lo status
        if (invoiceStatusRepository.existsByStatus(invoiceStatusRequest.getStatus())) {
            throw new IllegalArgumentException("Status already exists");
        }

        InvoiceStatus invoiceStatus = new InvoiceStatus();
        BeanUtils.copyProperties(invoiceStatusRequest, invoiceStatus);
        invoiceStatus = invoiceStatusRepository.save(invoiceStatus);

        return responseFromEntity(invoiceStatus);
    }

    public InvoiceStatusResponse updateInvoiceStatus(Long id, InvoiceStatusRequest invoiceStatusRequest) {
        if (!invoiceStatusRepository.existsById(id)) {
            throw new IllegalArgumentException("Invoice status not found (update)");
        }
        InvoiceStatus invoiceStatus = invoiceStatusRepository.findById(id).get();
        BeanUtils.copyProperties(invoiceStatusRequest, invoiceStatus);
        invoiceStatus = invoiceStatusRepository.save(invoiceStatus);
        return responseFromEntity(invoiceStatus);
    }

    public void deleteInvoiceStatus(Long id) {
        if (!invoiceStatusRepository.existsById(id)) {
            throw new IllegalArgumentException("Invoice status not found (delete)");
        }
        invoiceStatusRepository.deleteById(id);
    }

    // metodo GET per tutte le invoiceStatusResponse
    public List<InvoiceStatusResponse> getAllInvoiceStatuses() {
        return responseFromEntityList(invoiceStatusRepository.findAll());
    }

    // metodi aggiuntivi
    public InvoiceStatusResponse responseFromEntity(InvoiceStatus invoiceStatus) {
        InvoiceStatusResponse response = new InvoiceStatusResponse();
        BeanUtils.copyProperties(invoiceStatus, response);
        return response;
    }

    public List<InvoiceStatusResponse> responseFromEntityList(List<InvoiceStatus> invoiceStatuses) {
        return invoiceStatuses.stream().map(this::responseFromEntity).toList();
    }

    public InvoiceStatusResponse getInvoiceStatus(Long id) {
        return invoiceStatusRepository.findById(id)
                .map(this::responseFromEntity)
                .orElseThrow(() -> new IllegalArgumentException("Invoice status not found (get)"));
    }
}