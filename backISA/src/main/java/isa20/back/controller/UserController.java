package isa20.back.controller;

import isa20.back.dto.*;
import isa20.back.dto.request.LogInRequest;
import isa20.back.dto.request.SignUpRequest;
import isa20.back.dto.response.ApiResponse;
import isa20.back.model.User;
import isa20.back.service.UserService;


import javax.validation.Valid;


import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('PHARMACY_ADMIN')")
@RequestMapping("/api/userController")
public class UserController
{
	
	@Autowired
	private UserService UserService;

	
	@GetMapping("/test")
	public ResponseEntity< ? > test () {
		
		return new ResponseEntity< String >( " keru ", HttpStatus.ACCEPTED );
	}
	
	
	@PostMapping("/test2")
	public void test2 (@RequestBody final String string) {
		
		System.out.println( " stigla je  :::: " + string );
	}
	
	
	
	@GetMapping("/getMyInfo")
	public ResponseEntity< User > getMyInfo() {
		return UserService.getMyInfo();
		
	}
	
	@PutMapping("/user")
	public void updateUserInfo() {
		
	}

	
	
}
