package bw5.energyservices.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class InvoiceRequest {

    @NotNull(message = "Invoice number cannot be null")
    @Size(min = 1, message = "Invoice number must be at least 1 character long")
    private String invoiceNumber;

    @NotNull(message = "Invoice date cannot be null")
    @PastOrPresent(message = "Invoice date must be in the past or present")
    private LocalDate invoiceDate;

    @NotNull(message = "Invoice status cannot be null")
    private String invoiceStatus;

    @NotNull(message = "Client ID cannot be null")
    private Long clientId;

}