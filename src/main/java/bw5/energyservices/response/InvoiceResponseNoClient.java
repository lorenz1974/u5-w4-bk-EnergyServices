package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import bw5.energyservices.model.Client;
import bw5.energyservices.model.InvoiceStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponseNoClient {
    private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;

    @JsonIncludeProperties(value = { "status" })
    private InvoiceStatus invoiceStatus;
}