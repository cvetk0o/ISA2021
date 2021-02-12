package isa20.back.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.querydsl.core.types.Predicate;


import isa20.back.dto.request.DrugReservationRequest;
import isa20.back.dto.request.RatingRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.dto.response.DrugSearchDTO;
import isa20.back.email.EmailServiceImpl;
import isa20.back.exception.AppException;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Drug;
import isa20.back.model.DrugReservation;
import isa20.back.model.Item;
import isa20.back.model.Patient;
import isa20.back.model.Pharmacy;
import isa20.back.model.Rating;
import isa20.back.repository.DrugRepository;
import isa20.back.repository.DrugReservationRepository;
import isa20.back.repository.ItemRepository;
import isa20.back.repository.PatientRepository;
import isa20.back.repository.PharmacyRepository;
import isa20.back.repository.RatingRepository;
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
	
	
	@Autowired
	RatingRepository ratingRepo;
	
	
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
		
		if(patient.getPenal() >= 3)
			throw new ResourceNotFoundException( "You can't make reservations" );
		
		Item item = itemRepo.findById( request.getItemId() ).orElseThrow( () -> new ResourceNotFoundException( "item with this id dont exists" ) );
		
		if(item.getQuantity() == 0 )
			throw new ResourceNotFoundException("there is not enough ");
		
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
	
	
	public List< Drug > addDrugToAlergieList(Long drugId) {
		
		
		Drug drug = drugRepo.findById( drugId ).orElseThrow( ()-> new ResourceNotFoundException( "Drug with this id not found" ) );
		
		Patient patient = patientRepo.findById( userService.getMyId() ). orElseThrow( () -> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		if(patient.getAlergies().contains( drug )) 
			throw new AppException( "Drug allready in list" );
		
		patient.getAlergies().add( drug );
		
		patientRepo.save( patient );
		
		
		return  patient.getAlergies();
		
		
	}
	
	public List< Drug > getMyAlergies() {
		
		Patient patient = patientRepo.findById( userService.getMyId() ). orElseThrow( () -> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		if(patient.getAlergies().isEmpty())
			throw new AppException( "Drug list is empty" );
		
		return patient.getAlergies();
		
	}
	
	public List<Drug> removeDrugFromAlergies(Long drugId) {
		Drug drug = drugRepo.findById( drugId ).orElseThrow( ()-> new ResourceNotFoundException( "Drug with this id not found" ) );
		
		Patient patient = patientRepo.findById( userService.getMyId() ). orElseThrow( () -> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		patient.getAlergies().remove( drug );
		
		patientRepo.save( patient );
		
		return patient.getAlergies();
	}
	
	
	public List<Drug> getMyDrugs() {
		
		
		Patient patient = patientRepo.findById( userService.getMyId() ). orElseThrow( () -> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		List<DrugReservation> reservations  = new ArrayList< DrugReservation >();
		if(!patient.getDrugReservations().isEmpty()) {
			
			
			reservations = patient.getDrugReservations().stream().filter( reservation -> reservation.getReservedAt().isBefore( LocalDateTime.now() ) ).collect( Collectors.toList() );
			
			
			List<Item> unique = new ArrayList< Item >();
			
			for(DrugReservation p : reservations)
				if(!unique.contains( p.getItem() ))
					unique.add( p.getItem() );
			
			
			List<Drug> drugs = new ArrayList< Drug >();
			
			for(Item i : unique)
				if(!drugs.contains( i.getDrug() ))
						drugs.add( i.getDrug() );
			
			
			return drugs;
		}
		
		
		throw new ResourceNotFoundException( "You didn't had any drug reservations" );
		
	}
	
	public ResponseEntity< ApiResponse > rateDrug(RatingRequest request) {
		
		Patient patient = patientRepo.findById( userService.getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient not found" ) );
		
		Drug drug  = drugRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Drug not found" ) );
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		
		
		if(drug.getRatings().isEmpty() || ratings.isEmpty()) {
			Rating newRate = new Rating(request.getRating() , patient);
			
			drug.getRatings().add( newRate );
			
			ratingRepo.save( newRate );
			drug.calculateAvg();
			drugRepo.save( drug);
			
			return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated drug" ),HttpStatus.OK);
			
		}
		
		for(Rating r : ratings) 
			if(drug.getRatings().contains( r ))
				throw new ResourceNotFoundException( "You have already rated this drug" );
		
		Rating newRate = new Rating(request.getRating() , patient);
		
		drug.getRatings().add( newRate );
		
		ratingRepo.save( newRate );
		
		
		drug.calculateAvg();
		drugRepo.save(drug);
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated drug" ),HttpStatus.OK);
		
		
	}
	
public ResponseEntity< ApiResponse > overrideRateDrug(RatingRequest request) {
		
		Patient patient = patientRepo.findById( userService.getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient not found" ) );
		
		Drug drug  = drugRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Drug not found" ) );
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		for(Rating r : ratings) 
			if(drug.getRatings().contains( r )) {
				r.setGrade( request.getRating() );
				r.setVotedAt( LocalDateTime.now() );
				
				ratingRepo.save( r );
				drug.getRatings().set( drug.getRatings().indexOf( r ), r );
				
				drug.calculateAvg();
				drugRepo.save( drug);
				break;
			}
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully submited new grade for drug" ),HttpStatus.OK);
		
	}
	
	
		
	
}
