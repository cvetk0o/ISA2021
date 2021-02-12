package isa20.back.controller;

import java.text.ParseException;
import java.util.List;


import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.querydsl.core.types.Predicate;


import isa20.back.dto.request.DrugReservationRequest;
import isa20.back.dto.request.RatingRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.dto.response.DrugSearchDTO;
import isa20.back.model.Drug;
import isa20.back.model.Pharmacy;
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
	
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/drugs/reservation")
	public ResponseEntity< ApiResponse > makeDrugReservation(@RequestBody DrugReservationRequest request) throws ParseException, MessagingException {

		
		return this.drugService.makeDrugReservation(request);
		
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/drugs/alergie")
	public List<Drug> addDrugToAlergieList(@RequestBody Long drugId) {
		
		return this.drugService.addDrugToAlergieList(drugId);
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/drugs/alergies")
	public List< Drug > getMyAlergies() {
		
		return this.drugService.getMyAlergies();
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/drugs/alergies/{drugId}")
	public List<Drug> removeDrugFromAlergieList(@PathVariable Long drugId){
		
		return this.drugService.removeDrugFromAlergies( drugId );
	}
	
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/getMyDrugs")
	public List<Drug> getMyPharmacies() {
		return this.drugService.getMyDrugs();
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/rateDrug")
	public ResponseEntity< ApiResponse > rateDrug(@RequestBody RatingRequest request) {
		return this.drugService.rateDrug( request );
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/overrideRateDrug")
	public ResponseEntity< ApiResponse > overrideRateDrug(@RequestBody RatingRequest request) {
		return this.drugService.overrideRateDrug( request );
	}
	
	
}


