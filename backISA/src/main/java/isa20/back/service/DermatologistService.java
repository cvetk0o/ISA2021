package isa20.back.service;

import isa20.back.dto.DermatologistDTO;
import isa20.back.model.Address;
import isa20.back.model.Dermatologist;
import isa20.back.repository.AddressRepository;
import isa20.back.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Dermatologist createDermatologist(DermatologistDTO dermatologistDTODTO) {

        Dermatologist newDermatologist = new Dermatologist();

        Address address = new Address();
        address.setCountry(dermatologistDTODTO.getCountry());
        address.setCity(dermatologistDTODTO.getCity());
        address.setStreet(dermatologistDTODTO.getStreet());
        address.setNumber(dermatologistDTODTO.getNumber());

        Address savedAddress = addressRepository.save(address);

        newDermatologist.setName(dermatologistDTODTO.getName());
        newDermatologist.setLastname(dermatologistDTODTO.getLastname());
        newDermatologist.setEmail(dermatologistDTODTO.getEmail());
        newDermatologist.setPassword(dermatologistDTODTO.getPassword());
        newDermatologist.setAddress(address);
        newDermatologist.setPhoneNumber(dermatologistDTODTO.getPhoneNumber());
        newDermatologist.setActivated(false);
        //avgRate is null

        return dermatologistRepository.save(newDermatologist);

    }

    public void deleteDermatologist(DermatologistDTO dermatologistDTO) {
        dermatologistRepository.deleteById(dermatologistDTO.getId());
    }
}
