package bw5.energyservices.repository;

import bw5.energyservices.model.Client;

import java.util.Optional;

import bw5.energyservices.response.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByCompanyName(String companyName);

    boolean existsByVatNumber(String vatNumber);

    Optional<Client> findByCompanyName(String companyName);

    Optional<Client> findByVatNumber(String vatNumber);

    void deleteByCompanyName(String companyName);

    void deleteByVatNumber(String vatNumber);

    @Query("SELECT c FROM Client c WHERE LOWER(c.companyName) LIKE %:q% OR LOWER(c.vatNumber) LIKE %:q% OR LOWER(c.email) LIKE %:q% OR LOWER(c.certifiedEmail) LIKE %:q% OR LOWER(c.phone) LIKE %:q% OR LOWER(c.contactEmail) LIKE %:q% OR LOWER(c.contactFirstName) LIKE %:q% OR LOWER(c.contactLastName) LIKE %:q% OR LOWER(c.contactPhone) LIKE %:q%")
    Page<Client> omniSearch(@Param("q") String q, Pageable pageable);

}
