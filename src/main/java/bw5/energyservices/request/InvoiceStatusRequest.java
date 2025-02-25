package bw5.energyservices.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatusRequest {

    @NotNull(message = "Status cannot be null")
    @Size(min = 2, max = 50, message = "Status must be between 2 and 50 characters")
    private String status;

    @NotNull(message = "Description cannot be null")
    @Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters")
    private String description;
}