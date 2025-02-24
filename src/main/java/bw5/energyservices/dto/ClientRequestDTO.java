package bw5.energyservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    @NotNull
    @Size(min = 2, max = 100)
    private String companyName;

    @NotNull
    @Size(min = 11, max = 11)
    private String vatNumber;

    @Email
    @NotNull
    @Size(min = 5, max = 100)
    private String email;

    @NotNull
    private LocalDate lastContactDate;

    @NotNull
    private BigDecimal annualRevenue;

    @NotNull
    private String phone;

    @NotNull
    private String contactEmail;

    @NotNull
    private String contactFirstName;

    @NotNull
    private String contactLastName;

    @NotNull
    private String contactPhone;

    @NotNull
    private String companyLogo;

}
