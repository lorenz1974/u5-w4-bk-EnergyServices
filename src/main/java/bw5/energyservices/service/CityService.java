package bw5.energyservices.service;


import bw5.energyservices.model.CityComplete;
import bw5.energyservices.repository.CityCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityCompleteRepository cityCompleteRepository;

    private Page<CityComplete> getAllCities(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return cityCompleteRepository.findAll(pageable);
    }

    CityComplete findById(long id) {
        return cityCompleteRepository.findById(id).orElse(null);
    }

    private CityComplete saveCity(CityComplete city) {
        return cityCompleteRepository.save(city);
    }
}

