package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private Long id;
    private String companyName;
    private String vatNumber;
    private String email;
    private LocalDate lastContactDate;
    private BigDecimal annualRevenue;
    private String phone;
    private String contactEmail;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhone;
    private String companyLogo;
}
