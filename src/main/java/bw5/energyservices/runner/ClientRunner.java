package bw5.energyservices.runner;

import bw5.energyservices.model.Client;
import bw5.energyservices.repository.ClientRepository;
import bw5.energyservices.request.ClientRequest;
import bw5.energyservices.service.ClientService;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Order(3)
@Component
@RequiredArgsConstructor
@Slf4j
public class ClientRunner implements CommandLineRunner {

    private final Faker faker;
    private final ClientService clientService;

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

        log.info("Creazione 50 Client...");
        for (int i = 0; i < 50; i++) {

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
            client.setCompanyLogo(faker.internet().avatar());

            log.debug("Client: {}", client);

            ClientRequest clientRequest = new ClientRequest();
            BeanUtils.copyProperties(client, clientRequest);

            try {
                clientService.createClient(clientRequest);
                log.debug("Client {} created successfully.", clientRequest.getCompanyName());
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgumentException: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Exception: {}", e.getMessage());
            }

        }
        log.info("Clients creati e salvati nel DB!");

    }

}
