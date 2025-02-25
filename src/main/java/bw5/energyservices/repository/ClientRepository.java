package bw5.energyservices.repository;

import bw5.energyservices.model.Client;

import java.util.List;
import java.util.Optional;

import bw5.energyservices.response.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByCompanyName(String companyName);

    boolean existsByVatNumber(String vatNumber);

    Optional<Client> findByCompanyName(String companyName);

    Optional<Client> findByVatNumber(String vatNumber);

    void deleteByCompanyName(String companyName);

    void deleteByVatNumber(String vatNumber);

    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.companyName) = LOWER(:query) OR " +
            "LOWER(c.vatNumber) = LOWER(:query) OR " +
            "LOWER(c.email) = LOWER(:query) OR " +
            "LOWER(c.phone) = LOWER(:query) OR " +
            "LOWER(c.contactFirstName) = LOWER(:query) OR " +
            "LOWER(c.contactLastName) = LOWER(:query) OR " +
            "LOWER(c.contactPhone) = LOWER(:query) OR " +
            "LOWER(c.contactEmail) = LOWER(:query)")
    Page<Client> searchByQuery(String query, Pageable pageable);
}
