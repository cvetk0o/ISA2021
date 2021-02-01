package isa20.back.dto.request;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugReservationRequest
{

	private long drugId;
	
	private long itemId;
	
	private long pharmacyId;
	
	private String reservedAt;	
	
	public DrugReservationRequest() {
		
	}
}
