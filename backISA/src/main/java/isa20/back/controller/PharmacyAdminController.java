package isa20.back.controller;

import isa20.back.dto.PharmacistDTO;
import isa20.back.dto.PharmacyDTO;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.service.PharmacistService;
import isa20.back.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pharmacyAdminController")
public class PharmacyAdminController {

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private PharmacistService pharmacistService;

    @PostMapping("/editPharmacy")
    public Pharmacy editPharmacy(@RequestBody PharmacyDTO pharmacy) {

        return pharmacyService.editPharmacy(pharmacy);
    }

    @PostMapping("/addPharmacist")
    public Pharmacist createPharmacist(@RequestBody PharmacistDTO pharmacist) {
        return pharmacistService.createPharmacist(pharmacist);
    }

}
