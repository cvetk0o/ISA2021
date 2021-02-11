package isa20.back.service;

import isa20.back.dto.PharmacistDTO;
import isa20.back.model.Address;
import isa20.back.model.Pharmacist;
import isa20.back.repository.AddressRepository;
import isa20.back.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Pharmacist createPharmacist(PharmacistDTO pharmacistDTO) {

        Pharmacist newPharmacist = new Pharmacist();

        Address address = new Address();
        address.setCountry(pharmacistDTO.getCountry());
        address.setCity(pharmacistDTO.getCity());
        address.setStreet(pharmacistDTO.getStreet());
        address.setNumber(pharmacistDTO.getNumber());

        Address savedAddress = addressRepository.save(address);

        newPharmacist.setName(pharmacistDTO.getName());
        newPharmacist.setLastname(pharmacistDTO.getLastname());
        newPharmacist.setEmail(pharmacistDTO.getEmail());
        newPharmacist.setPassword(pharmacistDTO.getPassword());
        newPharmacist.setAddress(address);
        newPharmacist.setPhoneNumber(pharmacistDTO.getPhoneNumber());
        newPharmacist.setActivated(false);
        //avgRate is null
        newPharmacist.setWorkingHoursFrom(pharmacistDTO.getWorkingHoursFrom());
        newPharmacist.setWorkingHoursTo(pharmacistDTO.getWorkingHoursTo());

        Pharmacist savedPharmacist = pharmacistRepository.save(newPharmacist);

        return savedPharmacist;

    }
}
