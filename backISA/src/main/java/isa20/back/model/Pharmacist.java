package isa20.back.model;




import java.util.List;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pharmacist extends User
{
	
	@OneToMany
	@JoinColumn(name= "pharmacistId")
	private List< Vacation > vacations;
	
	@OneToMany
	@JoinColumn(name= "pharmacistId")
	private List< Consulting > consultings;

	private String workingHoursFrom;
	
	private String workingHoursTo;
	
	
	
	
	public Pharmacist() {
		
	}
}
