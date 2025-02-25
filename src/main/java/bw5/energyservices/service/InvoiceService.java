package bw5.energyservices.service;

import bw5.energyservices.model.Invoice;
import bw5.energyservices.repository.InvoiceRepository;
import bw5.energyservices.request.InvoiceRequest;
import bw5.energyservices.response.InvoiceResponse;
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
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponse createInvoice(@Valid InvoiceRequest invoiceRequest) {

        if (invoiceRepository.existsByInvoiceNumber(invoiceRequest.getInvoiceNumber())) {
            throw new EntityExistsException("Invoice number already exists");
        }

        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceRequest, invoice);
        invoice = invoiceRepository.save(invoice);
        return responseFromEntity(invoice);
    }

    public InvoiceResponse updateInvoice(Long id, InvoiceRequest invoiceRequest) {
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice not found (update)");
        }
        Invoice invoice = invoiceRepository.findById(id).get();
        BeanUtils.copyProperties(invoiceRequest, invoice);
        invoice = invoiceRepository.save(invoice);
        return responseFromEntity(invoice);
    }

    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice not found (delete)");
        }
        invoiceRepository.deleteById(id);
    }

    public List<InvoiceResponse> getAllInvoices() {
        return responseFromEntityList(invoiceRepository.findAll());
    }

    public InvoiceResponse responseFromEntity(Invoice invoice) {
        InvoiceResponse response = new InvoiceResponse();
        BeanUtils.copyProperties(invoice, response);
        return response;
    }

    public List<InvoiceResponse> responseFromEntityList(List<Invoice> invoices) {
        return invoices.stream().map(this::responseFromEntity).toList();
    }

    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found (get)"));
    }
}