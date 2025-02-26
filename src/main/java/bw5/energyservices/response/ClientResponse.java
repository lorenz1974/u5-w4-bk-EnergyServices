package bw5.energyservices.response;

import bw5.energyservices.model.Address;
import bw5.energyservices.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClientResponse is a Data Transfer Object (DTO) that encapsulates client
 * information.
 * This class is used to return client data WITHOUT including invoice details.
 *
 * Fields included in this response:
 * - id: Unique identifier of the client
 * - companyName: Name of the client's company
 * - vatNumber: VAT number of the client's company
 * - email: Email address of the client
 * - lastContactDate: The date and time of the last contact with the client
 * - annualRevenue: Annual revenue of the client's company
 * - phone: Phone number of the client
 * - contactEmail: Email address of the primary contact person
 * - contactFirstName: First name of the primary contact person
 * - contactLastName: Last name of the primary contact person
 * - contactPhone: Phone number of the primary contact person
 * - companyLogo: URL or path to the company's logo
 *
 * Note: This class returns only client data WITHOUT including invoice details.
 *
 * @see Client
 */
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
    private Address mainAddress;
    private Address operationalAddress; // Optional

    //
    // Constructor to create a ClientResponse object from a Client entity
    // This constructor is used to convert a Client entity to a ClientResponse
    // object
    // when returning client data WITHOUT including invoice details.
    //
    // ********* IT'S NOT A LOMBOK STANDARD CONSTRUCTOR *********
    //
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
        this.mainAddress = client.getMainAddress();
        this.operationalAddress = client.getOperationalAddress() != null
                ? client.getOperationalAddress()
                : null;
    }
}
