package isa20.back.dto.response;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class DrugSearchDTO
{
	
	private long pharmacyId;
	
	private String pharmacyName;
	
	private long itemPrice;
	
	private long itemId;
	
	
	
	public DrugSearchDTO() {}
	
	public DrugSearchDTO(long pharmacyId, String pharmacyName ,long itemId, long itemPrice) {
		
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.itemPrice = itemPrice;
		this.itemId = itemId;
		
	}

}
