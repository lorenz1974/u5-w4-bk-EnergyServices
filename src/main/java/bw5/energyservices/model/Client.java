package bw5.energyservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Imposta la data di creazione alla costruzione dell'oggetto

    private LocalDateTime lastContactDate;

    private BigDecimal annualRevenue;

    private String certifiedEmail;

    private String phone;

    private String contactEmail;

    private String contactFirstName;

    private String contactLastName;

    private String contactPhone;

    private String companyLogo;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @OrderBy("invoiceNumber ASC")
    private List<Invoice> invoices;
}
