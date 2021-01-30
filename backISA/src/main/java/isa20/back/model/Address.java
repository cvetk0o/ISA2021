package isa20.back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import isa20.back.dto.request.SignUpRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String number;
	
	
	public Address() {
		
	}
	
	public Address(String country, String city, String street, String number) {
		
		this.country = country;
		this.city = city;
		this.street = street;
		this.number = number;
	}

	public void update(SignUpRequest request) {
		
		this.country = request.getCountry();
		this.city = request.getCity();
		this.street = request.getStreet();
		this.number = request.getNumber();
		
	}
}
