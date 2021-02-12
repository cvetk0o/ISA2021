package isa20.back.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.querydsl.core.types.Predicate;


import isa20.back.dto.request.ConsultingReservationRequest;
import isa20.back.dto.request.RatingRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Consulting;
import isa20.back.model.ConsultingReservation;
import isa20.back.model.Examination;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.repository.ConsultingRepo;
import isa20.back.repository.PharmacistRepository;
import isa20.back.repository.PharmacyRepository;
import isa20.back.service.PharmacyService;

@RestController
@RequestMapping("/api/pharmacyController")
public class PharmacyController
{
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	ConsultingRepo consultingRepo;

	
	@GetMapping("/pharmacies")
	public ResponseEntity< List<Pharmacy> > getAllPharmacies () {
		
		return pharmacyService.getAllPharmacies();
		
	}
	
	
	@GetMapping("/searchPharm")
	 public Iterable<Pharmacy> findAllByPharm(
	      @QuerydslPredicate(root = Pharmacy.class) Predicate predicate) {
	
	        return pharmacyService.searchPharm(predicate);
	    }
	    
	
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/availablePharmacies")
	public List<Pharmacy> getAvailablePharmacies( @RequestBody ConsultingReservationRequest request) {
		
		return this.pharmacyService.getAvailablePharmacies(request);
	
		
	}
	
	
	@PostMapping("/getPharmacistsTest")
	public List<Consulting> getPharmacists1( @RequestBody ConsultingReservationRequest request) {
		
		return this.consultingRepo.findAll();
	
		
	}
	
	@GetMapping("/sort/{propertie}/{order}")
	public List<Pharmacy> sortPharmacie(@PathVariable String propertie , @PathVariable String order) {
		
		return this.pharmacyService.sortPharmacies( propertie, order );
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/showPharmacists")
	public List< Pharmacist > getAvailablePharmacists( @RequestBody ConsultingReservationRequest request) {
		
		return this.pharmacyService.getAvailablePharmacists(request) ;
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/availablePharmacies/sort/{propertie}/{order}")
	public List<Pharmacy> getSortedAvailablePharmacies(@PathVariable String propertie , @PathVariable String order , @RequestBody ConsultingReservationRequest request) {
		
		return this.pharmacyService.getSortedAvailablePharmacies(propertie , order, request);
		
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/makeReservation")
	public ResponseEntity< ApiResponse > makeReservation(@RequestBody ConsultingReservationRequest request) throws MessagingException {
		
		return this.pharmacyService.makeReservation(request);
	}

	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/examinations/{pharmacyId}")
	public List<Examination> availableExaminations( @PathVariable Long pharmacyId) {
		
		return this.pharmacyService.getAvailableExaminations(pharmacyId);
	}
	
	@PreAuthorize("hasRole('PATIENT')")
	@GetMapping("/getMyPharmacies")
	public List<Pharmacy> getMyPharmacies() {
		return this.pharmacyService.getMyPharmacies();
	}
	
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/ratePharmacy")
	public ResponseEntity< ApiResponse > ratePharmacist(@RequestBody RatingRequest request) {
		return this.pharmacyService.ratePharmacy( request );
	}
	
	
	@PreAuthorize("hasRole('PATIENT')")
	@PostMapping("/overrideRatePharmacy")
	public ResponseEntity< ApiResponse > overrideRatePharmacist(@RequestBody RatingRequest request) {
		return this.pharmacyService.overrideRatePharmacy( request );
	}
	
	
}
