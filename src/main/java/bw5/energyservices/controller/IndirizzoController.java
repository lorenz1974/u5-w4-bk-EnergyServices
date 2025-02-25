package bw5.energyservices.controller;

import bw5.energyservices.model.Indirizzo;
import bw5.energyservices.request.IndirizzoRequest;
import bw5.energyservices.response.IndirizzoResponse;
import bw5.energyservices.service.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    // Metodo per ottenere tutti gli indirizzi con paginazione
    @GetMapping
    public Page<Indirizzo> getAllIndirizzi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order) {

        return indirizzoService.getAllIndirizzi(page, size, order);
    }
    // Endpoint per ottenere indirizzi per città e provincia
    @GetMapping("/city-province")
    public List<Indirizzo> getIndirizziByCityAndProvince(
            @RequestParam Long cityId,
            @RequestParam Long provinceId) {
        return indirizzoService.findIndirizziByCityAndProvince(cityId, provinceId);
    }

    // Metodo per trovare un indirizzo per ID
    @GetMapping("/{id}")
    public Indirizzo findById(@PathVariable long id) throws ChangeSetPersister.NotFoundException {
        return indirizzoService.findById(id);
    }

    // Metodo per salvare un nuovo indirizzo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndirizzoResponse saveNewAddress(@RequestBody IndirizzoRequest payload) throws ChangeSetPersister.NotFoundException {
        Indirizzo indirizzo = indirizzoService.saveIndirizzo(payload);
        return new IndirizzoResponse(indirizzo.getId(), indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getCap(), indirizzo.getLocalita());
    }

    // Metodo per eliminare un indirizzo
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAddress(@PathVariable long id) {
        indirizzoService.findByIdAndDelete(id);
    }
}
