package bw5.energyservices.repository;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<CityComplete> getCityCompleteList(int provCode);

    Province findByProvinceCode(int code);
}
