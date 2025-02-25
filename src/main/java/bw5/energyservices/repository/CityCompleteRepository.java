package bw5.energyservices.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bw5.energyservices.model.CityComplete;

@Repository
public interface CityCompleteRepository extends JpaRepository<CityComplete, Long> {

    //
    // Search by a CONTAINED string in the name of the city or in the province
    //
    List<CityComplete> findByNameContainingIgnoreCase(String name);

    List<CityComplete> findByProvinceContainingIgnoreCase(String province);

    List<CityComplete> findByNameContainingIgnoreCaseOrProvinceContainingIgnoreCase(String name, String province);

    //
    // Search by an EXACT string in the name of the city or in the province
    //
    List<CityComplete> findByNameIgnoreCaseOrProvinceIgnoreCase(String name, String province);

    List<CityComplete> findByNameIgnoreCase(String name);

    List<CityComplete> findByProvinceIgnoreCase(String province);
}