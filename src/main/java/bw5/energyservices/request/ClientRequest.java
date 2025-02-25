package bw5.energyservices.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotNull(message = "Company name cannot be null")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @NotNull(message = "VAT number cannot be null")
    @Size(min = 11, max = 11, message = "VAT number must be exactly 11 characters")
    private String vatNumber;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters")
    private String email;

    @PastOrPresent(message = "Last contact date must be in the past or present")
    private LocalDateTime lastContactDate;

    @NotNull(message = "Annual revenue cannot be null")
    private BigDecimal annualRevenue;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 5, max = 20, message = "Phone number must be between 5 and 20 characters")
    private String phone;

    @Email(message = "Contact email should be valid")
    @NotNull(message = "Contact email cannot be null")
    private String contactEmail;

    @NotNull(message = "Contact first name cannot be null")
    private String contactFirstName;

    @NotNull(message = "Contact last name cannot be null")
    private String contactLastName;

    @NotNull(message = "Contact phone cannot be null")
    @Size(min = 5, max = 20, message = "Phone number must be between 5 and 20 characters")
    private String contactPhone;

    @NotNull(message = "Company logo cannot be null")
    private String companyLogo;

}
