package bw5.energyservices.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bw5.energyservices.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    //
    // Campi invoice
    //
    boolean existsByInvoiceNumber(String invoiceNumber);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    List<Invoice> findByInvoiceStatus_Id(Long statusId);

    List<Invoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);

    //
    // Campi client
    //
    // boolean existsByClientId(Long clientId);

    // boolean existsByClientVatNumber(String vatNumber);

    // List<Invoice> findByClientId(Long clientId);

    // List<Invoice> findByClientIdAndInvoiceStatus_Id(Long clientId, Long
    // statusId);

    // List<Invoice> findByClientVatNumber(String vatNumber);

    // Long countByClientId(Long clientId);
}