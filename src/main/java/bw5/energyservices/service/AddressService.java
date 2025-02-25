package bw5.energyservices.service;

import bw5.energyservices.model.Address;
import bw5.energyservices.model.CityComplete;
import bw5.energyservices.repository.AddressRepository;
import bw5.energyservices.request.AddressRequest;
import bw5.energyservices.response.AddressResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityCompleteService cityCompleteService;

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found (get)"));
    }

    public List<AddressResponse> getAllAddresses() {
        return responseFromEntityList(addressRepository.findAll());
    }

    public Address createAddress(@Valid AddressRequest addressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest, address);
        Long cityId = addressRequest.getCityId();
        CityComplete city = cityCompleteService.findById(cityId);
        address.setCity(city);

        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, @Valid AddressRequest addressRequest) {

        Address existingAddress = findById(id);

        existingAddress.setStreet(addressRequest.getStreet());
        existingAddress.setZipCode(addressRequest.getZipCode());

        Long cityId = addressRequest.getCityId();
        CityComplete city = cityCompleteService.findById(cityId);
        existingAddress.setCity(city);

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address not found (delete)");
        }
        addressRepository.deleteById(id);
    }

    public AddressResponse responseFromEntity(Address address) {
        AddressResponse response = new AddressResponse();
        BeanUtils.copyProperties(address, response);
        return response;
    }

    public List<AddressResponse> responseFromEntityList(List<Address> addresses) {
        return addresses.stream().map(this::responseFromEntity).toList();
    }
}
