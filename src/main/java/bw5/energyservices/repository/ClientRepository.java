package bw5.energyservices.repository;

import bw5.energyservices.model.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByCompanyName(String companyName);

    boolean existsByVatNumber(String vatNumber);

    Optional<Client> findByCompanyName(String companyName);

    Optional<Client> findByVatNumber(String vatNumber);

    void deleteByCompanyName(String companyName);

    void deleteByVatNumber(String vatNumber);

}
