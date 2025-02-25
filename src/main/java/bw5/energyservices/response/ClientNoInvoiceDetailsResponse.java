package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bw5.energyservices.model.Invoice;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientNoInvoiceDetailsResponse {
    private Long id;

    private String companyName;

    private String vatNumber;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastContactDate;

    private BigDecimal annualRevenue;

    private String certifiedEmail;

    private String phone;

    private String contactEmail;

    private String contactFirstName;

    private String contactLastName;

    private String contactPhone;

    private String companyLogo;

    @JsonIgnoreProperties(value = { "client" })
    private List<Invoice> invoices;
}
