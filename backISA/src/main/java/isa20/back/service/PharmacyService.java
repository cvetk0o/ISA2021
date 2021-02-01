package isa20.back.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.querydsl.core.types.Predicate;


import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Pharmacy;
import isa20.back.repository.PharmacyRepository;

@Service
public class PharmacyService
{
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	
	public ResponseEntity< List<Pharmacy> > getAllPharmacies() {
		
		
		List< Pharmacy > pharmacies = pharmacyRepo.findAll();
		
		if(pharmacies.isEmpty())
			throw new ResourceNotFoundException( "Pharmacy list is empty" );
		
		return  ResponseEntity.ok().body( pharmacies );
	}
	
	
	public  Iterable< Pharmacy > searchPharm(Predicate predicate ) {
		
		
	//	pharmacyRepo.findAll(PageRequest.of( page, size, Sort.by( "id" ).ascending()  ))
		
	//	pharmacyRepo.findAll( Sort.by( properties ). );
		
		return this.pharmacyRepo.findAll(predicate);
	}
	
	
	

	
	
}