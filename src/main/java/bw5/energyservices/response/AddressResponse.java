package bw5.energyservices.response;

import bw5.energyservices.model.CityComplete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private long id;
    private String street;
    private String zipCode;
    private CityComplete city;
}
