package bw5.energyservices.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
    private Long id;

    private String name;

    private String province;

    private String provinceCode;

    private String region;

}