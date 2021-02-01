package isa20.back.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


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

import isa20.back.security.*;



import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.exception.AppException;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Address;
import isa20.back.model.DrugReservation;
import isa20.back.model.Item;
import isa20.back.model.Patient;
import isa20.back.model.User;
import isa20.back.repository.AddressRepository;
import isa20.back.repository.DrugReservationRepository;
import isa20.back.repository.ItemRepository;
import isa20.back.repository.PatientRepository;
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
	
	
	
	public ResponseEntity< User > getMyInfo() {
		
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		User user = userRepository.findByEmail(currentUser.getName()).orElseThrow( () -> new ResourceNotFoundException( "User not found" ) );
		
		
		
		return ResponseEntity.ok().body( user );
		
	}
	
	
	public List<DrugReservation> getMyReservations() {
		
		Patient patient = patientRepo.findById( getMyId() ).orElseThrow( () -> new ResourceNotFoundException( "Patient not found 1" ) );
		
		return patient.getDrugReservations();
		
	}
	
	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
	public ResponseEntity< ApiResponse > cancelReservation(Long reservationId) {
		
		DrugReservation reservation = drugReservationRepo.findById( reservationId ).orElseThrow( () -> new ResourceNotFoundException( "Reservation with this id not found" ) );
		
		Date trenutno = new  Date();
		
		boolean moreThanDay = Math.abs(trenutno.getTime() - reservation.getReservedAt().getTime()) > MILLIS_PER_DAY;
		
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
	
	
	
	
}
