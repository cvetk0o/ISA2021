package isa20.back.dto.request;

import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInRequest
{

	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
}
