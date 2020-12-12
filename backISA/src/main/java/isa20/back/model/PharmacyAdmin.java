package isa20.back.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.ManyToAny;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PharmacyAdmin extends User
{
	
	
	@ManyToOne( fetch = FetchType.EAGER )
	private Pharmacy pharmacy;
	
	public PharmacyAdmin() {
		
	}

}
