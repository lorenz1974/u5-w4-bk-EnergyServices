package bw5.energyservices.runner;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import bw5.energyservices.model.City;
import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Province;
import bw5.energyservices.repository.CityCompleteRepository;
import bw5.energyservices.service.CsvImporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Order(1)
//@Component
@RequiredArgsConstructor
@Slf4j
public class ImportProvicesAndCities implements CommandLineRunner {

        private final CsvImporter csvImporter;
        private final CityCompleteRepository cityCompleteRepository;

        @Override
        public void run(String... args) throws Exception {
                List<Province> provinces = csvImporter.importCsv(
                                "src\\main\\java\\bw5\\energyservices\\comuni-province\\province-italiane.csv",
                                Province.class);
                List<City> cities = csvImporter
                                .importCsv("src\\main\\java\\bw5\\energyservices\\comuni-province\\comuni-italiani.csv",
                                                City.class);

                log.info("Import provinces and cities...");

                log.info("Provinces: {}", provinces.size());
                log.debug("Provinces: {}", provinces.toString());

                log.info("Cities: {}", cities.size());
                log.debug("Cities: {}", cities.toString());

                for (City city : cities) {

                        CityComplete cityComplete = new CityComplete();
                        cityComplete.setProvinceProgressive(city.getProvinceCode());
                        cityComplete.setCityProgressive(city.getCityCode());
                        cityComplete.setName(city.getName());
                        cityComplete.setProvince(city.getProvinceName());

                        cityComplete.setProvinceCode(
                                        provinces.stream().filter(p -> p.getName().equals(city.getProvinceName()))
                                                        .findFirst().map(Province::getCode).orElse(null));

                        cityComplete.setRegion(
                                        provinces.stream().filter(p -> p.getName().equals(city.getProvinceName()))
                                                        .findFirst().map(Province::getRegion).orElse(null));

                        cityCompleteRepository.save(cityComplete);
                }

                log.info("Done!");

        }
}
