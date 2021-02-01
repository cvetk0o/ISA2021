package isa20.back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	

	
	@OneToOne
	private Drug drug;
	
	private long price;
	
	private int quantity;
	
	public Item() {
		
	}

}
