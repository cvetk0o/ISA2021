package isa20.back.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonFormat;


import isa20.back.dto.request.DrugReservationRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DrugReservation
{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime createdAt;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime reservedAt;
	
	@ManyToOne
	@JoinColumn(name="itemId")
	private Item item;
	
	
	
	public DrugReservation() {
		
	}
	
	public DrugReservation( DrugReservationRequest request) throws ParseException {
		this.createdAt = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		this.reservedAt = LocalDateTime.parse(request.getReservedAt(), formatter);
		
		

		
	}

}
