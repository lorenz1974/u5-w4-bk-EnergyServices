package bw5.energyservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bw5.energyservices.model.InvoiceStatus;

@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Long> {

    boolean existsByStatus(String status);

    InvoiceStatus findByStatus(String status);

}
