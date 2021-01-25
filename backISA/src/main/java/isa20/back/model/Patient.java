package isa20.back.model;

import java.util.Set;


import javax.persistence.Entity;


import isa20.back.dto.request.SignUpRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient extends User
{

	
	public Patient() {
	
	}
	
	public Patient(SignUpRequest signUpRequest) {
		super(signUpRequest);
	}
}
