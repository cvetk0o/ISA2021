package isa20.back.model;

import java.util.List;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import isa20.back.dto.request.SignUpRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient extends User
{

	private int penal;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name= "patientId")
	private List< DrugReservation > drugReservations;
	
	@OneToMany
	@JoinColumn(name="patientId")
	private List<Consulting> consultings;
	
	@ManyToMany
	private List< Drug > alergies;
	

	
	
	
	public Patient() {
	
	}
	
	public Patient(SignUpRequest signUpRequest) {
		super(signUpRequest);
	}
}
