package bw5.energyservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
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

    // @Embedded
    // private Address address;
    //
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @OrderBy("invoiceNumber ASC")
    private List<Invoice> invoices;
}