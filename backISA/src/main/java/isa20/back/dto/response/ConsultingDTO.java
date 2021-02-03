package isa20.back.dto.response;

import isa20.back.model.Consulting;
import isa20.back.model.Pharmacist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultingDTO
{

	private Long id;
	
	private String startTime,endTime , date , pharmacy;
	
	private Pharmacist pharmacist;
	
	
	public ConsultingDTO() {}
	
	public ConsultingDTO( Consulting consulting , String pharmacy , Pharmacist pharmacist) {
		
		this.id = consulting.getId();
		this.startTime = consulting.getStartTime().toString();
		this.endTime = consulting.getEndTime().toString();
		this.date = consulting.getStartTime().toLocalDate().toString();
		this.pharmacy = pharmacy;
		this.pharmacist = pharmacist;
	}
 
}
