package isa20.back.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	@Min(1)
	@Max(5)
	private int grade;
	
	private LocalDateTime votedAt;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	
	
	public Rating() {
		
	}

}
