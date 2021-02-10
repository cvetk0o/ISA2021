package isa20.back.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dermatologist extends User
{
	
	@OneToMany
	@JoinColumn(name="dermatologistId")
	private List< Rating > ratings;

	public Dermatologist() {
		
	}
}
