package bw5.energyservices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndirizzoResponse {
    private long id;
    private String via;
    private int civico;
    private int cap;
    private String localita;
}
