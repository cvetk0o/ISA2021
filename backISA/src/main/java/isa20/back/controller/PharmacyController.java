package isa20.back.controller;

import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.querydsl.core.types.Predicate;


import isa20.back.model.Pharmacy;
import isa20.back.repository.PharmacyRepository;
import isa20.back.service.PharmacyService;

@RestController
@RequestMapping("/api/pharmacyController")
public class PharmacyController
{
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@GetMapping("/pharmacies")
	public ResponseEntity< List<Pharmacy> > getAllPharmacies () {
		
		return pharmacyService.getAllPharmacies();
		
	}
	
	
	@GetMapping("/searchPharm")
	 public Iterable<Pharmacy> findAllByPharm(
	      @QuerydslPredicate(root = Pharmacy.class) Predicate predicate) {
	
	        return pharmacyService.searchPharm(predicate);
	    }
	    
	
	

}
