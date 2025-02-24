package bw5.energyservices.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @CsvBindByName(column = "Codice Provincia (Storico)(1)")
    private String provinceCode;

    @CsvBindByName(column = "Progressivo del Comune (2)")
    private String cityCode;

    @CsvBindByName(column = "Denominazione in italiano")
    private String name;

    @CsvBindByName(column = "Provincia")
    private String provinceName;
}
