package isa20.back.dto.request;

import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import isa20.back.validation.PasswordMatches;
import lombok.Getter;

@Getter
@Setter
@PasswordMatches
public class SignUpRequest
{
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password1;
	
	@NotBlank
	private String password2;
	
	@NotBlank
	@Size( min = 2, max = 100 )
	private String name;
	
	@NotBlank
	@Size( min = 2, max = 100 )
	private String lastname;
	
	@NotBlank
	private String country;

	@NotBlank
	private String city;

	@NotBlank
	private String street;
	
	@NotBlank
	private String phoneNumber;
	
}
