package isa20.back.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
	
	@Autowired
	private AuthService authService;

	
	@PostMapping("/signUp")
	public ResponseEntity< ApiResponse > newUser( @RequestBody  @Valid final SignUpRequest request) throws MessagingException {
		
		return this.authService.registerUser(request);
	}
	
	@PostMapping( "/login")
	public ResponseEntity< ? > login( @RequestBody final LogInRequest request) {
		
		return this.authService.logInUser(request);
	}
	
	
	@PutMapping("/activate/{id}")
	public ResponseEntity< ApiResponse > activateUser ( @RequestParam Long id) {
		
		return this.authService.activateUser(id);
	}
	
}
