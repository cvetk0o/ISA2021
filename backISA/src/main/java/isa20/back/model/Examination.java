package isa20.back.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Examination
{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime startTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime endTime;
	
	private Long price;
	
	//0 kreiran 1-rezervisan 2-odradjen
	private int status;
	
	@ManyToOne
	@JoinColumn(name="dermatologistId")
	private Dermatologist dermatologist;
	
	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	
	public Examination() {
		
	}
	
}
