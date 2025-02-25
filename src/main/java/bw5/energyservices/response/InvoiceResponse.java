package bw5.energyservices.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceResponse {
    private Long invoiceId;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String invoiceStatus;
    private Long clientId;
}