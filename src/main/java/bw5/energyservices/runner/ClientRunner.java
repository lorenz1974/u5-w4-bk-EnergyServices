package bw5.energyservices.runner;

import bw5.energyservices.model.Address;
import bw5.energyservices.model.Client;
import bw5.energyservices.repository.CityCompleteRepository;
import bw5.energyservices.request.AddressRequest;
import bw5.energyservices.service.AddressService;
import bw5.energyservices.service.ClientService;
import jakarta.transaction.Transactional;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Order(3)
@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientRunner implements CommandLineRunner {

    private final Faker faker;
    private final ClientService clientService;
    private final CityCompleteRepository cityCompleteRepository;
    private final AddressService addressService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Override
    public void run(String... args) throws Exception {

        // Check if table creation is needed
        log.info("*** spring.jpa.hibernate.ddl-auto set to '{}'. {}", ddlAuto,
                ddlAuto.equals("none") ? "Skipping records creation..." : "Creating records...");
        if (ddlAuto.equals("none")) {
            return;
        }

        long cityCount = cityCompleteRepository.count();
        log.debug("Number of Cities in the DB: {}", cityCount);

        log.info("Creation of 150 Clients...");
        for (int i = 0; i < 150; i++) {

            Client client = new Client();
            client.setCompanyName(faker.company().name());
            client.setVatNumber(faker.number().digits(11));
            client.setEmail(faker.internet().emailAddress());

            // Mette una data di contatto se il booleano è vero
            if (faker.bool().bool()) {
                client.setLastContactDate(LocalDateTime.now().minusDays(30));
            }
            client.setPhone(faker.phoneNumber().phoneNumber());
            client.setContactEmail(faker.internet().emailAddress());
            client.setAnnualRevenue(BigDecimal.valueOf(faker.number().randomDouble(2, 10000, 500000)));
            client.setContactPhone(faker.phoneNumber().phoneNumber());
            client.setContactFirstName(faker.name().firstName());
            client.setContactLastName(faker.name().lastName());
            client.setCompanyLogo(
                    "https://eu.ui-avatars.com/api/?size=250&name=" +
                            client.getCompanyName()
                                    .replace(" ", "+"));

            for (int j = 0; j < faker.number().numberBetween(1, 3); j++) {
                AddressRequest officeAddress = new AddressRequest();
                officeAddress.setStreet(faker.address().streetAddress());
                officeAddress.setCityId(faker.number().numberBetween(1, cityCount + 1));
                officeAddress.setZipCode(faker.address().zipCode());

                // Se è il primo ufficio creato, lo imposta come indirizzo principale
                officeAddress.setMainAddress(j == 0);

                try {
                    Address createdOfficeAddress = addressService.createAddress(officeAddress);
                    log.debug("Office Address[{}]: {}", j, createdOfficeAddress);

                    if (j == 0) {
                        client.setMainAddress(createdOfficeAddress);
                    } else {
                        client.setOperationalAddress(createdOfficeAddress);
                    }
                } catch (IllegalArgumentException e) {
                    log.error("IllegalArgumentException: {}", e.getMessage());
                } catch (Exception e) {
                    log.error("Error creating address for client {}: {}", client.getCompanyName(), e.getMessage());
                }
            }

            // Salvo il cliente
            try {
                Client clientCreated = clientService.createClient(client);
                log.debug("Client created successfully: {}", clientCreated);
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgumentException: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Error creating the client {}: {}", client, e.getMessage());
            }

        }
        log.info("Clients created and saved in the DB!");

    }

}
