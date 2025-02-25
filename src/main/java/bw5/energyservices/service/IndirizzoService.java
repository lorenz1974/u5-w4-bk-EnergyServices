package bw5.energyservices.service;

import bw5.energyservices.model.City;
import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Indirizzo;
import bw5.energyservices.model.Province;
import bw5.energyservices.repository.IndirizzoRepository;
import bw5.energyservices.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    // Metodo per ottenere tutti gli indirizzi con paginazione e ordinamento
    public Page<Indirizzo> getAllIndirizzi(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return indirizzoRepository.findAll(pageable);
    }

    // Metodo per trovare un indirizzo per ID
    public Indirizzo findById(long id) throws ChangeSetPersister.NotFoundException {
        return indirizzoRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
    // Metodo per trovare indirizzi per città e provincia
    public List<Indirizzo> findIndirizziByCityAndProvince(Long cityId, Long provinceId) {
        return indirizzoRepository.findIndirizzoByCityAndProvince(cityId, provinceId);
    }

    // Metodo per salvare un nuovo indirizzo
    public Indirizzo saveIndirizzo(IndirizzoRequest indirizzoRequest) throws ChangeSetPersister.NotFoundException {
        // Trova la provincia e la città
        Province province = provinceService.findByProvinceCode(indirizzoRequest.prov_code());
        CityComplete cityComplete = cityService.findById(indirizzoRequest.cityId());

        // Crea un nuovo oggetto Indirizzo e setta i suoi campi
        Indirizzo nuovoIndirizzo = new Indirizzo();
        nuovoIndirizzo.setCity(cityComplete);
        nuovoIndirizzo.setProvince(province);
        nuovoIndirizzo.setVia(indirizzoRequest.via());
        nuovoIndirizzo.setCivico(Integer.parseInt(indirizzoRequest.civico()));
        nuovoIndirizzo.setCap(Integer.parseInt(indirizzoRequest.cap()));
        nuovoIndirizzo.setLocalita(indirizzoRequest.localita());

        // Salva l'indirizzo nel database
        return indirizzoRepository.save(nuovoIndirizzo);
    }


    public void findByIdAndDelete(long id) {
        indirizzoRepository.deleteById(id);
    }
}
