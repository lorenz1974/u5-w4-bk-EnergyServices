package bw5.energyservices.auth;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@Order(2)
@RequiredArgsConstructor
public class AuthRunner implements ApplicationRunner {

   Faker faker = new Faker();

   final private AppUserService appUserService;

   final private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

       AppUser superAdmin = new AppUser();

       superAdmin.setFirstName("Super");
       superAdmin.setLastName("admin");
       superAdmin.setUsername("superadmin");
       superAdmin.setEmail("superadmin@esempio.it");
       superAdmin.setPassword("password123");
       appUserService.registerUser(superAdmin);


        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                AppUser appUser = new AppUser();
                appUser.setEmail(faker.internet().emailAddress());
                appUser.setPassword(passwordEncoder.encode(faker.internet().password()));
                appUser.setFirstName(faker.name().firstName());
                appUser.setLastName(faker.name().lastName());
                appUser.setUsername(faker.name().lastName());
                appUser.setRoles(Set.of(Role.ROLE_ADMIN));

                appUserService.registerUser(appUser);
            }
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> user = appUserService.findByUsername("user");
        if (user.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                AppUser appUser = new AppUser();
                appUser.setEmail(faker.internet().emailAddress());
                appUser.setPassword(passwordEncoder.encode(faker.internet().password()));
                appUser.setFirstName(faker.name().firstName());
                appUser.setLastName(faker.name().lastName());
                appUser.setUsername(faker.name().lastName());
                appUser.setRoles(Set.of(Role.ROLE_USER));

                appUserService.registerUser(appUser);
            }
        }
    }
}
