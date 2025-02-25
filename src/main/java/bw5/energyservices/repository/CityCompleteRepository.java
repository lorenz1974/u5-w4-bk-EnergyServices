package bw5.energyservices.repository;

import bw5.energyservices.model.CityComplete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityCompleteRepository extends JpaRepository<CityComplete, Long> {

}
