package bw5.energyservices.customers;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public class Customers {




    @Entity
    @Table(name = "clients")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String companyName;

        @Column(nullable = false, unique = true)
        private String vatNumber;

        @Column(nullable = false, unique = true)
        private String email;

        private LocalDate insertionDate;

        private LocalDate lastContactDate;

        private BigDecimal annualRevenue;

        private String certifiedEmail;

        private String phone;

        private String contactEmail;

        private String contactFirstName;

        private String contactLastName;

        private String contactPhone;

        private String companyLogo;
    }
}
