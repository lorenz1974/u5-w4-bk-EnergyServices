package bw5.energyservices.response;

import bw5.energyservices.model.InvoiceStatus;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponseNoClientDetails {

    private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;

    @JsonIncludeProperties(value = { "status" })
    private InvoiceStatus invoiceStatus;

    private BigDecimal amount;
}
