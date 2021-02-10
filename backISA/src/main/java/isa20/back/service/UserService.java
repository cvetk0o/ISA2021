package isa20.back.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import isa20.back.dto.response.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import isa20.back.security.*;



import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.RatingRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.exception.AppException;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Address;
import isa20.back.model.Consulting;
import isa20.back.model.Dermatologist;
import isa20.back.model.DrugReservation;
import isa20.back.model.Examination;
import isa20.back.model.Item;
import isa20.back.model.Patient;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.model.Rating;
import isa20.back.model.User;
import isa20.back.repository.AddressRepository;
import isa20.back.repository.ConsultingRepo;
import isa20.back.repository.DermatologistRepository;
import isa20.back.repository.DrugReservationRepository;
import isa20.back.repository.ExaminationRepository;
import isa20.back.repository.ItemRepository;
import isa20.back.repository.PatientRepository;
import isa20.back.repository.PharmacistRepository;
import isa20.back.repository.PharmacyRepository;
import isa20.back.repository.RatingRepository;
import isa20.back.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	private DrugReservationRepository drugReservationRepo;
	
	@Autowired
	PatientRepository patientRepo;
	
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	PharmacistRepository pharmacistRepo;
	
	@Autowired
	PharmacyRepository pharmacyRepo;
	
	@Autowired
	ConsultingRepo consultingRepo;
	
	@Autowired 
	ExaminationRepository examRepo;
	
	
	@Autowired
	DermatologistRepository dermRepo;
	
	
	@Autowired
	RatingRepository ratingRepo;
	
	
	
	
	
	public ResponseEntity< ApiResponse > registerUser( SignUpRequest request) {
		
		User user  = new User();
		
		user.setPassword( passwordEncoder.encode( request.getPassword1()) );
		user.setEmail( request.getEmail() );
		
		userRepository.save( user );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "uspesno kreiran korisnik" ), HttpStatus.OK);
		
	}
	
	public ResponseEntity< ? > logInUser( LogInRequest request ) {

		Authentication authentication =
				authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() ) );

		SecurityContextHolder.getContext().setAuthentication( authentication );

		String jwt = tokenProvider.generateToken( authentication );

		Optional< User > checkUserOpt = userRepository.findByEmail( request.getEmail() );
		
		
		return ResponseEntity.ok( new JwtAuthenticationResponse( jwt ) );
			
	}
	
	
	public ResponseEntity<?> updateUserInfo(SignUpRequest request) {
		
		User user = userRepository.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "User not found" ) );
		
		user.Update( request );
		
		Address address = addressRepository.findById( user.getAddress().getId() ).orElseThrow( ( ) -> new ResourceNotFoundException( "address not found" ));
		
		address.update(request);
		
		addressRepository.save( address );
		
		userRepository.save( user );
		
		return ResponseEntity.ok().body( "Successful update" );
		
	}
	
	
	public List<ConsultingDTO> getMyReservedConsultings() {
		
		Patient patient = patientRepo.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "Patient not found" ) );
		
		if(patient.getConsultings().isEmpty())
			throw new AppException( "you dont have any reservations" );
		
		List<Consulting> consultings = patient.getConsultings().stream().filter( consulting -> consulting.getStatus() ==0 ).collect( Collectors.toList() );
		
		List<ConsultingDTO> zaSlanje = new ArrayList< ConsultingDTO >();
		for(Consulting c : consultings) {
			
			Pharmacist pharmacist = pharmacistRepo.findByConsultings( c ).orElseThrow( () -> new ResourceNotFoundException( "not found" ) );
			
			Pharmacy pharmacy = pharmacyRepo.findByPharmacists( pharmacist ).orElseThrow( () -> new ResourceNotFoundException( "not found 1" ) );
			
			ConsultingDTO cons = new ConsultingDTO( c, pharmacy.getName(), pharmacist );
			
			zaSlanje.add( cons );
		}
		
		return zaSlanje;
		
	}
	
	
	public ResponseEntity< ? > cancelConsulting( Long consultingId) {
		
		Consulting consultig = consultingRepo.findById( consultingId ).orElseThrow( () -> new ResourceNotFoundException( "consulting not found" ) );
		
		Patient patient = patientRepo.findByConsultings( consultig ).orElseThrow( ()-> new ResourceNotFoundException( "patient not found" ) );
		
		Pharmacist pharmacist = pharmacistRepo.findByConsultings( consultig ).orElseThrow( () -> new ResourceNotFoundException( "Pharmacist not found" ) );
		
		//proveri da li je vise od 24 casa ?
		LocalDateTime trenutno = LocalDateTime.now();
		
		if(!trenutno.plusHours( 24 ).isBefore( consultig.getStartTime() ) ) 
			throw new AppException( "It's less than 24 hours before appointment" );
			
		
		patient.getConsultings().remove( consultig );
		
		pharmacist.getConsultings().remove( consultig );
		
		patientRepo.save( patient );
		
		pharmacistRepo.save( pharmacist );
		
		consultingRepo.delete( consultig );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true,"Consulting cancelled" ), HttpStatus.OK);
		
	}
	
	
	public ResponseEntity< User > getMyInfo() {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		User user = userRepository.findByEmail(currentUser.getName()).orElseThrow( () -> new ResourceNotFoundException( "User not found" ) );
		
		
		
		return ResponseEntity.ok().body( user );
		
	}
	
	
	public List<DrugReservation> getMyReservations() {
		
		Patient patient = patientRepo.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "Patient not found 1" ) );
		
		List<DrugReservation> reservations = patient.getDrugReservations().stream().filter( reservation -> reservation.getReservedAt().isBefore( LocalDateTime.now() ) ).collect( Collectors.toList() );
		
		return reservations;
		
	}
	
	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
	public ResponseEntity< ApiResponse > cancelReservation(Long reservationId) {
		
		DrugReservation reservation = drugReservationRepo.findById( reservationId ).orElseThrow( () -> new ResourceNotFoundException( "Reservation with this id not found" ) );
		
		Date trenutno = new  Date();
		
		Date reservationTime =  java.sql.Timestamp.valueOf( reservation.getReservedAt() );
		
		boolean moreThanDay = Math.abs(trenutno.getTime() - reservationTime.getTime()) > MILLIS_PER_DAY;
		
		if( !moreThanDay)
			throw new  ResourceNotFoundException( "MANJE JE OD 24h ne moze da se otkaze " );
		
		Item item = itemRepo.findById( reservation.getItem().getId() ).orElseThrow( ()-> new ResourceNotFoundException( "item with this id doesnt exist" ));
		
		item.setQuantity( item.getQuantity() +1 );
		
		itemRepo.save( item );
		
		Patient patient  = patientRepo.findByDrugReservations( reservation);
		
		patient.getDrugReservations().remove( reservation );
		
		patientRepo.save( patient );
		
		drugReservationRepo.delete( reservation );

		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "successfuly deleted" ) , HttpStatus.OK);
	}
	
	
	public Long getMyId() {
		
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		
		
		User user = userRepository.findByEmail(currentUser.getName()).orElseThrow( () -> new ResourceNotFoundException( "User not found" ) );
		
		
		return user.getId();
		
		
		
	}	
	
	
	public ResponseEntity< ApiResponse >  updatePassword( SignUpRequest request) {
		
		System.out.println( "pogodio service" );
		
		User user = userRepository.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "User with this id doesnt exist" ) );
		
		if(passwordEncoder.encode( request.getPassword1() ).equals( user.getPassword() ))
			throw new AppException( "You entered previous password.Please enter new " );
		
		user.setPassword( passwordEncoder.encode( request.getPassword1() ) );
		
		userRepository.save( user );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "password successfuly updated" ) , HttpStatus.OK);
	}
	
	
	
	
	public ResponseEntity< ApiResponse > makeExamReservation(Long examinationId) {
		
		
			Examination exam = examRepo.findById( examinationId ).orElseThrow( () -> new ResourceNotFoundException( "Exam with this id doesn't exist" ));
			
			Patient patient = patientRepo.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "User with this id doesn't exist" ) );
			
			exam.setPatient( patient );
			
			exam.setStatus( 1 );
			
			examRepo.save( exam );
			
			patientRepo.save(patient);
			
			return new ResponseEntity< ApiResponse >( new ApiResponse( true, "Reservation successfully created" ) ,  HttpStatus.OK);
		
	}
	
	public List<Examination> getMyReservedExaminations() {
		
		List<Examination> examinations = examRepo.findByPatientId(getMyId());
				
		return examinations;
	}
	
	
	public ResponseEntity< ApiResponse > cancelExamination(Long examinationId) {
		
		Examination exam = examRepo.findById( examinationId ).orElseThrow( () -> new ResourceNotFoundException( "Examination with this id not found" ) );
		
		exam.setPatient( null );
		
		exam.setStatus( 0 );
		
		examRepo.save( exam );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "Examination successfuly canceled" ), HttpStatus.OK);
		
	}
	
	
	public List< ConsultingDTO > getMyConsultings() {
		
		Patient patient = patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		List< Consulting > lista =  patient.getConsultings().stream().filter( c -> c.getStatus() == 1 ).collect( Collectors.toList() );
		
		List<ConsultingDTO> zaSlanje = new ArrayList< ConsultingDTO >();
		for(Consulting c : lista) {
			
			Pharmacist pharmacist = pharmacistRepo.findByConsultings( c ).orElseThrow( () -> new ResourceNotFoundException( "not found" ) );
			
			Pharmacy pharmacy = pharmacyRepo.findByPharmacists( pharmacist ).orElseThrow( () -> new ResourceNotFoundException( "not found 1" ) );
			
			ConsultingDTO cons = new ConsultingDTO( c, pharmacy.getName(), pharmacist );
			
			zaSlanje.add( cons );
		}
		
		return zaSlanje;
		
	}
	
	public List< Pharmacist > getMyPharmacists() {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		if(patient.getConsultings().isEmpty())
			throw new ResourceNotFoundException( "No finished consultings" );
		
		List< Consulting > consultings = patient.getConsultings().stream().filter( c -> c.getStatus() == 1 ).collect( Collectors.toList() );
		
		List<Pharmacist> pharmacists = pharmacistRepo.findByConsultingsIdIn( consultings.stream().map(Consulting::getId).collect( Collectors.toList() ) );
		
		//remove duplicates
		List< Pharmacist > unique = new ArrayList< Pharmacist >();
		for(Pharmacist c : pharmacists)
			if(!unique.contains( c ))
				unique.add( c );
		
		
		return unique;
		
		
	}
	
	
	
	public ResponseEntity< ApiResponse > ratePharmacist(RatingRequest request) {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		Pharmacist pharmacist = pharmacistRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		//proveri da li je prazno
		
		if(pharmacist.getRatings().isEmpty() || ratings.isEmpty()) {
			Rating newRate = new Rating(request.getRating() , patient);
			
			pharmacist.getRatings().add( newRate );
			
			ratingRepo.save( newRate );
			
			pharmacist.calculateAvg();
			pharmacistRepo.save( pharmacist );
			
			return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated pharmacist" ),HttpStatus.OK);
			
		}
		//proveri u pharmacist da li postoji rating sa ovim pacijentom
		
		for(Rating r : ratings) 
			if(pharmacist.getRatings().contains( r ))
				throw new ResourceNotFoundException( "You have already rated this pharmacist" );
		
		Rating newRate = new Rating(request.getRating() , patient);
		
		pharmacist.getRatings().add( newRate );
		
		ratingRepo.save( newRate );
		
		pharmacist.calculateAvg();
		pharmacistRepo.save( pharmacist );
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated pharmacist" ),HttpStatus.OK);
		
	}
	
	
	public ResponseEntity< ApiResponse > overrideRatePharmacist( RatingRequest request) {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		Pharmacist pharmacist = pharmacistRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		for(Rating r : ratings) 
			if(pharmacist.getRatings().contains( r )) {
				r.setGrade( request.getRating() );
				r.setVotedAt( LocalDateTime.now() );
				
				ratingRepo.save( r );
				pharmacist.getRatings().set( pharmacist.getRatings().indexOf( r ), r );
				
				pharmacist.calculateAvg();
				pharmacistRepo.save( pharmacist );
				break;
			}
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully submited new grade for pharmacist" ),HttpStatus.OK);
		
	}
	
	public List< Dermatologist > getMyDermatologists() {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		if(patient.getConsultings().isEmpty())
			throw new ResourceNotFoundException( "No finished Examinations" );
		
		List< Examination > examinations = examRepo.findByPatientId( getMyId() );
		
		List< Examination > examinations1 = examinations.stream().filter( c -> c.getStatus() == 1 ).collect( Collectors.toList() );
		
		
		//remove duplicates
		List< Dermatologist> unique = new ArrayList< Dermatologist >();
		for(Examination e : examinations1 )
			if(!unique.contains( e.getDermatologist() ))
				unique.add( e.getDermatologist() );
		
		
		return unique;
		
		
	}
	
	
	
	
	public ResponseEntity< ApiResponse > rateDermatologist(RatingRequest request) {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		Dermatologist dermatologist = dermRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Dermatologist with this id not found" ) );
		
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		//proveri da li je prazno
		
		if(dermatologist.getRatings().isEmpty() || ratings.isEmpty()) {
			Rating newRate = new Rating(request.getRating() , patient);
			
			dermatologist.getRatings().add( newRate );
			
			ratingRepo.save( newRate );
			
			dermatologist.calculateAvg();
			dermRepo.save(dermatologist);
			
			return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated dermatologist" ),HttpStatus.OK);
			
		}
		//proveri u pharmacist da li postoji rating sa ovim pacijentom
		
		for(Rating r : ratings) 
			if(dermatologist.getRatings().contains( r ))
				throw new ResourceNotFoundException( "You have already rated this dermatologist" );
		
		
		Rating newRate = new Rating(request.getRating() , patient);
		
		dermatologist.getRatings().add( newRate );
		
		ratingRepo.save( newRate );
		
		dermatologist.calculateAvg();
		dermRepo.save(dermatologist);
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully rated pharmacist" ),HttpStatus.OK);
		
	}
	
	
	
	public ResponseEntity< ApiResponse > overrideRateDermatologist( RatingRequest request) {
		
		Patient patient =  patientRepo.findById( getMyId() ).orElseThrow( ()-> new ResourceNotFoundException( "Patient with this id not found" ) );
		
		Dermatologist dermatologist = dermRepo.findById( request.getId() ).orElseThrow( ()-> new ResourceNotFoundException( "Dermatologist with this id not found" ) );
		
		List< Rating  > ratings = ratingRepo.findByPatientId( patient.getId() );
		
		for(Rating r : ratings) 
			if(dermatologist.getRatings().contains( r )) {
				r.setGrade( request.getRating() );
				r.setVotedAt( LocalDateTime.now() );
				
				ratingRepo.save( r );
				dermatologist.getRatings().set( dermatologist.getRatings().indexOf( r ), r );
				dermatologist.calculateAvg();
				dermRepo.save( dermatologist );
				break;
			}
		
		return new ResponseEntity< ApiResponse >(new ApiResponse( true,"You successfully submited new grade for dermatologist" ),HttpStatus.OK);
		
	}
	
	
	
}
