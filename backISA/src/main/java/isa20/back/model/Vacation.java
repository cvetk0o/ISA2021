package isa20.back.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vacation
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime vacationFrom;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime vacationTo;
	
	public Vacation() {
		
	}
}
