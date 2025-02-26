package bw5.energyservices.controller;

import bw5.energyservices.model.Invoice;
import bw5.energyservices.request.InvoiceRequest;
import bw5.energyservices.response.InvoiceResponse;
import bw5.energyservices.response.PaginatedClientResponse;
import bw5.energyservices.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@Validated
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginatedClientResponse<InvoiceResponse> getAllInvoices(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "25") int size, @RequestParam(defaultValue = "invoiceNumber") String sortBy,
            @RequestParam(required = false) String q) {
        return invoiceService.getAllInvoices(currentPage, size, sortBy, q);
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
    @ResponseStatus(HttpStatus.OK)
    public Invoice Lombardia(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}