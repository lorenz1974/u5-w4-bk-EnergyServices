package bw5.energyservices.service;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Province;
import bw5.energyservices.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    public Page<Province> getAllProvinces(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return provinceRepository.findAll(pageable);
    }

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Province findById(long id) throws ChangeSetPersister.NotFoundException {
        return provinceRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    public Province findByProvinceCode(int prov_code) throws ChangeSetPersister.NotFoundException {
        Province found = provinceRepository.findByProvinceCode(prov_code);
        if (found != null) {
            return found;
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public List<CityComplete> getCityList(int prov_code) {
        return provinceRepository.getCityCompleteList(prov_code);
    }
}

