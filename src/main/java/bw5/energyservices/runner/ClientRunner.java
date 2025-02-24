package bw5.energyservices.runner;

import bw5.energyservices.model.Client;
import bw5.energyservices.repository.ClientRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.time.LocalDate;

@Order(3)
@Component
@RequiredArgsConstructor
public class ClientRunner implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final Faker faker;



   @Override
    public void run(String...args) throws Exception {

       System.out.println("---Creazione Client---");
       for (int i=0; i<10; i++) {

           Client client = new Client();
           client.setCompanyName(faker.company().name());
           client.setVatNumber(faker.number().digits(11));
           client.setEmail(faker.internet().emailAddress());
           client.setLastContactDate(LocalDate.now().minusDays(30));
           client.setPhone(faker.phoneNumber().phoneNumber());
           client.setContactEmail(faker.internet().emailAddress());
           client.setAnnualRevenue(BigDecimal.valueOf(faker.number().randomDouble(2,10000, 500000)));
           client.setContactPhone(faker.phoneNumber().phoneNumber());
           client.setContactFirstName(faker.name().firstName());
           client.setContactLastName(faker.name().lastName());
           client.setCompanyLogo(faker.internet().avatar());

           clientRepository.save(client);

       }
       System.out.println("-----Clients creati e salvati nel DB!-----");


   }

}
