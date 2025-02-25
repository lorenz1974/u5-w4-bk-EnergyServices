package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatusResponse {
    private Long id;
    private String status;
    private String description;

}
