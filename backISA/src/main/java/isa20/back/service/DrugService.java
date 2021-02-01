package isa20.back.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import isa20.back.dto.request.DrugReservationRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.dto.response.DrugSearchDTO;
import isa20.back.email.EmailServiceImpl;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Drug;
import isa20.back.model.DrugReservation;
import isa20.back.model.Item;
import isa20.back.model.Patient;
import isa20.back.model.Pharmacy;
import isa20.back.repository.DrugRepository;
import isa20.back.repository.DrugReservationRepository;
import isa20.back.repository.ItemRepository;
import isa20.back.repository.PatientRepository;
import isa20.back.repository.PharmacyRepository;
import isa20.back.repository.UserRepository;

@Service
public class DrugService
{

	@Autowired
	private DrugRepository drugRepo;
	
	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private PharmacyRepository pharmacyRepo;
	
	@Autowired
	private DrugReservationRepository reservationRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	
	public ResponseEntity< List<Drug> > getAllDrugs() {
		
	List< Drug > drugs = drugRepo.findAll();
		
		if(drugs.isEmpty())
			throw new ResourceNotFoundException( "Drug list is empty" );
		
		return  ResponseEntity.ok().body( drugs ); 
	}
	
	
	
	public List<DrugSearchDTO> getPharmacies(Long drugId) {
		
		List<Item> items = itemRepo.findByDrugIdAndQuantityGreaterThan( drugId, 0 );
		
		List<DrugSearchDTO> returnList = new ArrayList< DrugSearchDTO >();
		
		for( Item item : items) {
			
			Pharmacy pharmacy = pharmacyRepo.findByItemList( item );
			DrugSearchDTO ds = new DrugSearchDTO(pharmacy.getId() , pharmacy.getName() ,item.getId() , item.getPrice());
			returnList.add( ds );
		}
		
		
		return returnList;
	}
	
	
	public ResponseEntity< ApiResponse > makeDrugReservation(DrugReservationRequest request) throws ParseException, MessagingException {
	
		// proveri datum da li je pre trenutnog
		
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getReservedAt());  
		
		Date trenutno = new  Date();
		
		if( trenutno.after( date ))
			throw new ResourceNotFoundException( "Date is not valid! it must be in future :)" );
		
		DrugReservation reservation = new DrugReservation(request);
		
		Patient patient = patientRepo.findById( userService.getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "Patiend dont exists" ) );
		
		Item item = itemRepo.findById( request.getItemId() ).orElseThrow( () -> new ResourceNotFoundException( "item with this id dont exists" ) );
		
		item.setQuantity( item.getQuantity() -1 );
		
		itemRepo.save( item );
		
		reservation.setItem( item );
		
		patient.getDrugReservations().add( reservation );
		
		reservationRepo.save( reservation );
		
		patientRepo.save( patient );
		
		
		String text= "<h1>Reservation successfuly created<h1>";
		
		text += "<p> reservation id: " + reservation.getId() + " </p>";
		
		emailService.sendSimpleMessage( patient.getEmail(), "RESERVATION CREATED", text );
		
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true , "Reservation successfuly created" ) , HttpStatus.OK);
		
	}
	
}
