package bw5.energyservices.service;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Client;
import bw5.energyservices.repository.CityCompleteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityCompleteService {

    private final CityCompleteRepository cityCompleteRepository;

    // public List<CityComplete> findAll() {
    // return cityCompleteRepository.findAll();
    // }

    public CityComplete findById(Long id) {
        return cityCompleteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found (get)"));
    }

    public List<CityComplete> findByNameContainingIgnoreCase(String name) {
        return cityCompleteRepository.findByNameContainingIgnoreCase(name);
    }

    public List<CityComplete> findByProvinceContainingIgnoreCase(String province) {
        return cityCompleteRepository.findByProvinceContainingIgnoreCase(province);
    }

    public List<CityComplete> findByNameContainingIgnoreCaseOrProvinceContainingIgnoreCase(String name,
            String province) {
        return cityCompleteRepository.findByNameContainingIgnoreCaseOrProvinceContainingIgnoreCase(name, province);
    }

    public List<CityComplete> findByNameIgnoreCaseOrProvinceIgnoreCase(String name, String province) {
        return cityCompleteRepository.findByNameIgnoreCaseOrProvinceIgnoreCase(name, province);
    }

    public List<CityComplete> findByNameIgnoreCase(String name) {
        return cityCompleteRepository.findByNameIgnoreCase(name);
    }

    public List<CityComplete> findByProvinceIgnoreCase(String province) {
        return cityCompleteRepository.findByProvinceIgnoreCase(province);
    }

    public List<CityComplete> findAll() {
        return cityCompleteRepository.findAll();
    }

}
