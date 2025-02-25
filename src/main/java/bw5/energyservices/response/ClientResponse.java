package bw5.energyservices.response;

import bw5.energyservices.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private Long id;
    private String companyName;
    private String vatNumber;
    private String email;
    private LocalDateTime lastContactDate;
    private BigDecimal annualRevenue;
    private String phone;
    private String contactEmail;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhone;
    private String companyLogo;

    public ClientResponse(Client client) {
        this.id = client.getId();
        this.companyName = client.getCompanyName();
        this.vatNumber = client.getVatNumber();
        this.email = client.getEmail();
        this.lastContactDate = client.getLastContactDate();
        this.annualRevenue = client.getAnnualRevenue();
        this.phone = client.getPhone();
        this.contactEmail = client.getContactEmail();
        this.contactFirstName = client.getContactFirstName();
        this.contactLastName = client.getContactLastName();
        this.contactPhone = client.getContactPhone();
        this.companyLogo = client.getCompanyLogo();
    }
}
