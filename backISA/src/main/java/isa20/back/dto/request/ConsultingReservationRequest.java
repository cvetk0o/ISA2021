package isa20.back.dto.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultingReservationRequest
{
	
	private String date;
	
	private String time;
	
	private Long pharmacyId;
	
	private Long pharmacistId;
	
	
	public ConsultingReservationRequest() {
		
	}
	
	
	public LocalDateTime getVreme() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(this.date+" "+this.time, formatter);
		
		return dateTime;
	}
	
}
