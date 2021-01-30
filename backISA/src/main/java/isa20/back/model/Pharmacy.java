package isa20.back.model;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
public class Pharmacy
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	private Address address;
	
	@OneToMany
	private Set< Pharmacist > pharmacists;
	
	@ManyToMany
	private Set< Dermatologist > dermatologists;
	
	
	public Pharmacy() {
		
	}
	
}
