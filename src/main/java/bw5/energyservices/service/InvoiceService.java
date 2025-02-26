package bw5.energyservices.service;

import bw5.energyservices.model.Invoice;
import bw5.energyservices.repository.InvoiceRepository;
import bw5.energyservices.request.InvoiceRequest;
import bw5.energyservices.response.InvoiceResponse;
import bw5.energyservices.response.PaginatedClientResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

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

    public PaginatedClientResponse<InvoiceResponse> getAllInvoices(int currentPage, int size, String sortBy, String q) {
        Page<Invoice> invoices;
        PageRequest pageRequest = PageRequest.of(currentPage, size, Sort.by(sortBy));

        invoices = invoiceRepository.findAll(pageRequest);

        // if (q == null || q.isEmpty()) {
        // invoices = invoiceRepository.findAll(pageRequest);
        // } else {
        // String qLower = q.toLowerCase();
        // invoices = invoiceRepository.omniSearch(qLower, pageRequest);
        // }

        List<InvoiceResponse> invoiceResponses = invoices.getContent().stream()
                .map(this::responseFromEntity)
                .collect(Collectors.toList());

        return new PaginatedClientResponse<>(invoiceResponses, invoices.getTotalPages(), invoices.getTotalElements(),
                currentPage, size);
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