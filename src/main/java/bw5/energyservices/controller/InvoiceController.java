package bw5.energyservices.controller;

import bw5.energyservices.model.Invoice;
import bw5.energyservices.request.InvoiceRequest;
import bw5.energyservices.response.InvoiceResponse;
import bw5.energyservices.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Validated
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceResponse> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.createInvoice(invoiceRequest);
    }

    @PutMapping("/{id}")
    public InvoiceResponse updateInvoice(@PathVariable Long id,
            @Valid @RequestBody InvoiceRequest invoiceRequest) {
        InvoiceResponse response = invoiceService.updateInvoice(id, invoiceRequest);
        return response;
    }

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}