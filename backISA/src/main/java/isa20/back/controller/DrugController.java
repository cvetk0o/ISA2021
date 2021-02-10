package isa20.back.controller;

import java.text.ParseException;
import java.util.List;


import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import isa20.back.dto.request.DrugReservationRequest;
import isa20.back.dto.response.DrugSearchDTO;
import isa20.back.model.Drug;
import isa20.back.service.DrugService;

@RestController
@RequestMapping( "/api/drugController")
public class DrugController
{

	@Autowired
	private DrugService drugService;
	
	
	
	@GetMapping("/drugs")
	public ResponseEntity< List< Drug > > getAllDrugs() {
		
		return this.drugService.getAllDrugs();
	}
	
	@GetMapping("/drugs/{id}/pharmacies")
	public List<DrugSearchDTO> getPharmacies(@PathVariable Long id) {
		
		return drugService.getPharmacies( id );
	}
	
	// napravi klasu itemdto koja ce imati id apoteke naziv apoteke cenu leka, ... vidi sta ti jos treba
	
	
	
	@PostMapping("/drugs/reservation")
	public void makeDrugReservation(@RequestBody DrugReservationRequest request) throws ParseException, MessagingException {

		
		this.drugService.makeDrugReservation(request);
		
	}
	
	
	@PostMapping("/drugs/alergie")
	public List<Drug> addDrugToAlergieList(@RequestBody Long drugId) {
		
		return this.drugService.addDrugToAlergieList(drugId);
	}
	
	@GetMapping("/drugs/alergies")
	public List< Drug > getMyAlergies() {
		
		return this.drugService.getMyAlergies();
	}
	
	@GetMapping("/drugs/alergies/{drugId}")
	public List<Drug> removeDrugFromAlergieList(@PathVariable Long drugId){
		
		return this.drugService.removeDrugFromAlergies( drugId );
	}
	
}


