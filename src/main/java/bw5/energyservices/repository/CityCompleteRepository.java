import bw5.energyservices.model.CityComplete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

package bw5.energyservices.repository;

@Repository
public interface CityCompleteRepository extends JpaRepository<CityComplete, Long> {

}
