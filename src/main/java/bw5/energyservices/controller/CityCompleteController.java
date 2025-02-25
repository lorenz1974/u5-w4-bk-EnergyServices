package bw5.energyservices.controller;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.service.CityCompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityCompleteController {

    private final CityCompleteService cityCompleteService;

    @GetMapping("/{id}")
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
    public List<CityComplete> searchCities(
            @RequestParam(required = false) String cityName,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) boolean exactSearch) {

        if (cityName != null && province != null && exactSearch == false) {
            return cityCompleteService.findByNameContainingIgnoreCaseOrProvinceContainingIgnoreCase(cityName,
                    province);
        } else if (cityName != null && exactSearch == false) {
            return cityCompleteService.findByNameContainingIgnoreCase(cityName);
        } else if (province != null && exactSearch == false) {
            return cityCompleteService.findByProvinceContainingIgnoreCase(province);
        } else if (cityName != null && province != null && exactSearch == true) {
            return cityCompleteService.findByNameIgnoreCaseOrProvinceIgnoreCase(cityName,
                    province);
        } else if (cityName != null && exactSearch == true) {
            return cityCompleteService.findByNameIgnoreCase(cityName);
        } else if (province != null && exactSearch == true) {
            return cityCompleteService.findByProvinceIgnoreCase(province);
        } else {
            return cityCompleteService.findAll();
        }
    }
}
