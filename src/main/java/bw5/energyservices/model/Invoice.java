package bw5.energyservices.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false, unique = true)
    @Size(min = 6, max = 10)
    private String invoiceNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_status_id", nullable = false)
    @JsonIncludeProperties(value = { "status" })
    private InvoiceStatus invoiceStatus;

    @Column(nullable = false)
    private Double amount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIncludeProperties(value = { "id", "companyName", "vatNumber", "email" })
    private Client client;

}
