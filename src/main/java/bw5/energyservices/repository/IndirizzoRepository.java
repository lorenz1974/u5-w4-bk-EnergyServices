package bw5.energyservices.repository;

import bw5.energyservices.model.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
    @Query("SELECT i FROM Indirizzo i JOIN i.city c JOIN i.province p WHERE c.id = :cityId AND p.id = :provinceId")
    List<Indirizzo> findIndirizzoByCityAndProvince(Long cityId, Long provinceId);
}