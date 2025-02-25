package bw5.energyservices.controller;

import bw5.energyservices.model.Address;
import bw5.energyservices.request.AddressRequest;
import bw5.energyservices.service.AddressService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{id}")
    public Address findById(@PathVariable long id) {
        return addressService.findById(id);
    }

    @PostMapping
    public Address createAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.createAddress(addressRequest);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable long id, @RequestBody AddressRequest addressRequest) {
        return addressService.updateAddress(id, addressRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
    }
}
