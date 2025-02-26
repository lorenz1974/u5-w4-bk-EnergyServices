package bw5.energyservices.controller;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.service.CityCompleteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityCompleteController {

    private final CityCompleteService cityCompleteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityComplete findById(@PathVariable Long id) {
        return cityCompleteService.findById(id);
    }

    /**
     * Endpoint unificato per la ricerca.
     * Se vengono passati entrambi i parametri cityName e province,
     * esegue la ricerca combinata; altrimenti, effettua la ricerca in base al
     * parametro presente.
     * Se non vengono forniti parametri, restituisce tutte le città.
     *
     * Esempi di chiamata:
     * GET /cities?cityName=Roma
     * GET /cities?province=Milano
     * GET /cities?cityName=Roma&province=Milano
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityComplete> searchCities(
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) boolean exactSearch) {

        if (cityName != null && province != null) {
            return exactSearch
                    ? cityCompleteService.findByNameIgnoreCaseOrProvinceIgnoreCase(cityName, province)
                    : cityCompleteService.findByNameContainingIgnoreCaseOrProvinceContainingIgnoreCase(cityName,
                            province);
        } else if (cityName != null) {
            return exactSearch
                    ? cityCompleteService.findByNameIgnoreCase(cityName)
                    : cityCompleteService.findByNameContainingIgnoreCase(cityName);
        } else if (province != null) {
            return exactSearch
                    ? cityCompleteService.findByProvinceIgnoreCase(province)
                    : cityCompleteService.findByProvinceContainingIgnoreCase(province);
        } else {
            return cityCompleteService.findAll();
        }
    }
}
