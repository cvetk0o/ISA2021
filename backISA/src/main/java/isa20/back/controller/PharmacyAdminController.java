package isa20.back.controller;

import isa20.back.dto.DermatologistDTO;
import isa20.back.dto.PharmacistDTO;
import isa20.back.dto.PharmacyDTO;
import isa20.back.model.Dermatologist;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.service.DermatologistService;
import isa20.back.service.PharmacistService;
import isa20.back.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pharmacyAdminController")
public class PharmacyAdminController {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private DermatologistService dermatologistService;

    @PostMapping("/editPharmacy")
    public Pharmacy editPharmacy(@RequestBody PharmacyDTO pharmacy) {

        return pharmacyService.editPharmacy(pharmacy);
    }

    @PostMapping("/{pharmacyID}/addPharmacist")
    public Pharmacist createPharmacist(@PathVariable Long pharmacyID, @RequestBody PharmacistDTO pharmacist) {

        Pharmacist newPharacist = pharmacistService.createPharmacist(pharmacist);

        pharmacyService.addPharmacist(pharmacyID, newPharacist);

        return newPharacist;
    }

    @PostMapping("/{pharmacyID}/addDermatologist")
    public Dermatologist createDermatologist(@PathVariable Long pharmacyID, @RequestBody DermatologistDTO dermatologist) {

        Dermatologist newDermatologies = dermatologistService.createDermatologist(dermatologist);

        pharmacyService.addDermatologist(pharmacyID, newDermatologies);

        return newDermatologies;
    }

    @DeleteMapping("/{pharmacyID}/deletePharmacist")
    public void deletePharmacist(@PathVariable Long pharmacyID, @RequestBody PharmacistDTO pharmacistDTO) {

        pharmacistService.deletePharmacist(pharmacistDTO);

    }

}
