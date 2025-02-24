package bw5.energyservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("bw5.energyservices.auth")
public class EnergyservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyservicesApplication.class, args);
	}

}
