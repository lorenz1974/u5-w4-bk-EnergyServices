package bw5.energyservices.controller;

import bw5.energyservices.request.InvoiceStatusRequest;
import bw5.energyservices.response.InvoiceStatusResponse;
import bw5.energyservices.service.InvoiceStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice-statuses")
@RequiredArgsConstructor
@Validated
public class InvoiceStatusController {

    private final InvoiceStatusService invoiceStatusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceStatusResponse> getAllInvoiceStatuses() {
        return invoiceStatusService.getAllInvoiceStatuses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceStatusResponse createInvoiceStatus(@Valid @RequestBody InvoiceStatusRequest invoiceStatusRequest) {
        return invoiceStatusService.createInvoiceStatus(invoiceStatusRequest);
    }

    @PutMapping("/{id}")
    public InvoiceStatusResponse updateInvoiceStatus(@PathVariable Long id,
            @Valid @RequestBody InvoiceStatusRequest invoiceStatusRequest) {
        InvoiceStatusResponse response = invoiceStatusService.updateInvoiceStatus(id, invoiceStatusRequest);
        return response;
    }

    @GetMapping("/{id}")
    public InvoiceStatusResponse getInvoiceStatus(@PathVariable Long id) {
        return invoiceStatusService.getInvoiceStatus(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceStatus(@PathVariable Long id) {
        invoiceStatusService.deleteInvoiceStatus(id);
    }
}