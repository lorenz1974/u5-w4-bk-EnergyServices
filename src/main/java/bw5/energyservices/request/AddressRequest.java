package bw5.energyservices.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

        @NotNull(message = "Street cannot be null")
        @Size(min = 3, max = 50, message = "Street must be between 3 and 50 characters")
        private String street;

        @NotNull(message = "CityId cannot be null")
        @Min(value = 1, message = "CityId must be greater than 0")
        private Long cityId;

        @NotNull(message = "Zip code cannot be null")
        @Size(min = 5, max = 5, message = "Zip code must be 5 characters")
        private String zipCode;

        private boolean mainAddress;
}