package bw5.energyservices.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @CsvBindByName(column = "Sigla")
    private String code;

    @CsvBindByName(column = "Provincia")
    private String name;

    @CsvBindByName(column = "Regione")
    private String region;
}
