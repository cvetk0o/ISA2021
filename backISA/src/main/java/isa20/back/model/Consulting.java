package isa20.back.model;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Setter;


import lombok.Getter;

@Entity
@Getter
@Setter
public class Consulting
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime startTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime endTime;
	
	//0-rezervisan  1- odradjen 2-otkazan?
	private int status;
	
	public Consulting() {
		
	}
	
	public Consulting(LocalDateTime start , LocalDateTime end ) {
		this.startTime = start;
		this.endTime = end;
		this.status = 0;
	}
	
	
}
