package bw5.energyservices.controller;

import bw5.energyservices.model.CityComplete;
import bw5.energyservices.model.Province;
import bw5.energyservices.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("{prov_id}")
    public List<CityComplete> getCityList(@PathVariable int prov_code) {
        return provinceService.getCityList(prov_code);
    }
    @GetMapping("/All")
    public  List<Province> getAllProvinces() {
        return provinceService.getAllProvinces();
    }
}
