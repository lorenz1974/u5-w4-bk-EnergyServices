package bw5.energyservices.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class CsvImporter {

    public <T> List<T> importCsv(String filePath, Class<T> clazz) {
        try (BOMInputStream bomInputStream = new BOMInputStream(new FileInputStream(filePath));
                Reader reader = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8)) {

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(false) // Disabilita la rimozione automatica delle virgolette
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            log.error("Error reading CSV file", e);
            return List.of();
        }
    }
}
