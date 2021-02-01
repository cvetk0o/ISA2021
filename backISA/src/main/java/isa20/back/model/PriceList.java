package isa20.back.model;

import java.util.HashMap;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PriceList
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	private HashMap< Long, Integer > drugPriceList = new HashMap< Long, Integer >();
	
	public PriceList() {
		
		
	}
}
