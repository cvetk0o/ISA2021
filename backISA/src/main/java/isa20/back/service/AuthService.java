package isa20.back.service;

import java.util.Optional;


import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.dto.response.JwtAuthenticationResponse;
import isa20.back.email.EmailServiceImpl;
import isa20.back.exception.AppException;
import isa20.back.exception.ResourceNotFoundException;
import isa20.back.model.Authority;
import isa20.back.model.Patient;
import isa20.back.model.RoleName;
import isa20.back.model.User;
import isa20.back.repository.AuthorityRepository;
import isa20.back.repository.PatientRepository;
import isa20.back.repository.UserRepository;
import isa20.back.security.JwtTokenProvider;


@Service
public class AuthService
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	public ResponseEntity< ApiResponse > registerUser( SignUpRequest request) throws MessagingException {
		
		
		if( userRepository.existsByEmail( request.getEmail() )) {
			
			return new ResponseEntity< ApiResponse >( new ApiResponse( false, "Email is already taken"), HttpStatus.BAD_REQUEST);
		}
		
		Patient user  = new Patient( request );
		
		user.setPassword( passwordEncoder.encode( request.getPassword1()) );
		
		Authority authority = authorityRepository.findByName( RoleName.ROLE_PATIENT).orElseThrow( () -> new AppException( "User Role not set." ) );
		
		user.getAuthorities().add( authority );
		
		patientRepository.save( user );
		
		
		String text= "<h1>PLEASE CONFIRM YOUR ACCOUNT<h1>";
		
		text += "<a href='http://localhost:8080/api/auth/activate/" + user.getId() + "'>Accept</a><br/>";
		
		emailService.sendSimpleMessage( user.getEmail(), "ACCOUNT ACTIVATION", text );
		
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "uspesno kreiran korisnik" ), HttpStatus.OK);
		
	}
	
	
	
	public ResponseEntity< ? > logInUser( LogInRequest request ) {

		User user = userRepository.findByEmail( request.getEmail() ).orElseThrow( () -> new ResourceNotFoundException( "User with this email doesn't exist" ) );
		
		if( !user.getActivated())
			throw new AppException( "User must be activated. Check email" );
		
		
		Authentication authentication =
				authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() ) );

		SecurityContextHolder.getContext().setAuthentication( authentication );

		String jwt = tokenProvider.generateToken( authentication );

		Optional< User > checkUserOpt = userRepository.findByEmail( request.getEmail() );
		
		
		return ResponseEntity.ok( new JwtAuthenticationResponse( jwt ) );
			//
	}
	
	
	
	public ResponseEntity< ApiResponse > activateUser( Long id) {

		User user = userRepository.findById( id ).orElseThrow( () -> new ResourceNotFoundException( "User with this id doesn't exist" ) );
		
		user.setActivated( true );
		
		userRepository.save( user );
		
		return new ResponseEntity< ApiResponse >( new ApiResponse( true, "Account successfully activated"), HttpStatus.OK);
	}
	
}
