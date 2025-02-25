package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import bw5.energyservices.model.Client;
import bw5.energyservices.model.Invoice;
import bw5.energyservices.model.InvoiceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;

    @JsonIncludeProperties(value = { "status" })
    private InvoiceStatus invoiceStatus;

    private BigDecimal amount;

    @JsonIncludeProperties(value = { "id", "companyName", "vatNumber" })
    private Client client;

    //
    // Constructor to create a InvoiceResponse object from an Invoice entity
    // This constructor is used to convert an Invoice entity to an InvoiceResponse
    // object when returning invoice data.
    //
    // ********* IT'S NOT A LOMBOK STANDARD CONSTRUCTOR *********
    //
    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.invoiceNumber = invoice.getInvoiceNumber();
        this.invoiceDate = invoice.getInvoiceDate();
        this.invoiceStatus = invoice.getInvoiceStatus();
        this.amount = invoice.getAmount();
        this.client = invoice.getClient();
    }
}