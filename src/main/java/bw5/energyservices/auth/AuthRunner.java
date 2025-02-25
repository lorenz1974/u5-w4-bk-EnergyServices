package bw5.energyservices.auth;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class AuthRunner implements ApplicationRunner {

    Faker faker = new Faker();

    final private AppUserService appUserService;
    final private PasswordEncoder passwordEncoder;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Check if table creation is needed
        log.info("*** spring.jpa.hibernate.ddl-auto set to '{}'. {}", ddlAuto,
                ddlAuto.equals("none") ? "Skipping records creation..." : "Creating records...");
        if (ddlAuto.equals("none")) {
            return;
        }
        log.info("Creazione SuperAdmin...");
        AppUser superAdmin = new AppUser();

        superAdmin.setFirstName("Super");
        superAdmin.setLastName("admin");
        superAdmin.setUsername("superadmin");
        superAdmin.setEmail("superadmin@esempio.it");
        superAdmin.setPassword("password123");
        appUserService.registerUser(superAdmin);

        // Creazione dell'utente admin se non esiste
        log.info("Creating admin users...");
        for (int i = 0; i < 3; i++) {
            AppUser appUser = new AppUser();
            appUser.setEmail(faker.internet().emailAddress());
            appUser.setPassword(passwordEncoder.encode(faker.internet().password()));
            appUser.setFirstName(faker.name().firstName());
            appUser.setLastName(faker.name().lastName());
            appUser.setUsername(faker.name().lastName());
            appUser.setRoles(Set.of(Role.ROLE_ADMIN));
            try {
                appUserService.registerUser(appUser);
                log.debug("Admin user {} created successfully.", appUser.getUsername());
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgumentException: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Exception: {}", e.getMessage());
            }

        }
        log.info("Admin users created successfully.");

        // Creazione dell'utente user se non esiste
        log.info("Creating user users...");
        for (int i = 0; i < 11; i++) {
            AppUser appUser = new AppUser();
            appUser.setEmail(faker.internet().emailAddress());
            appUser.setPassword(passwordEncoder.encode(faker.internet().password()));
            appUser.setFirstName(faker.name().firstName());
            appUser.setLastName(faker.name().lastName());
            appUser.setUsername(faker.name().lastName());
            appUser.setRoles(Set.of(Role.ROLE_USER));

            try {
                appUserService.registerUser(appUser);
                log.debug("App User {} created successfully.", appUser.getUsername());
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgumentException: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Exception: {}", e.getMessage());
            }
        }
        log.info("User users created successfully.");
    }
}
