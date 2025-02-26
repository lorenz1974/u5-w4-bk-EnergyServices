package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import bw5.energyservices.model.Address;
import bw5.energyservices.model.Client;
import bw5.energyservices.model.Invoice;
import bw5.energyservices.model.InvoiceSummary;

@Data
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

    private Address mainAddress;
    private Address operationalAddress;

    @JsonIgnoreProperties(value = { "client" })
    private List<Invoice> invoices;

    private Map<String, Double> invoiceSummary;

    public ClientNoInvoiceDetailsResponse(Client client) {
        this.id = client.getId();
        this.companyName = client.getCompanyName();
        this.vatNumber = client.getVatNumber();
        this.email = client.getEmail();
        this.createdAt = client.getCreatedAt();
        this.lastContactDate = client.getLastContactDate();
        this.annualRevenue = client.getAnnualRevenue();
        this.certifiedEmail = client.getCertifiedEmail();
        this.phone = client.getPhone();
        this.contactEmail = client.getContactEmail();
        this.contactFirstName = client.getContactFirstName();
        this.contactLastName = client.getContactLastName();
        this.contactPhone = client.getContactPhone();
        this.companyLogo = client.getCompanyLogo();
        this.invoices = client.getInvoices();
        this.mainAddress = client.getMainAddress();
        this.operationalAddress = client.getOperationalAddress();
        this.invoiceSummary = new InvoiceSummary(this.invoices).getInvoiceSummary();
    }
}
