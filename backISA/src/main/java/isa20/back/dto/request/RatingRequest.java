package isa20.back.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest
{
	private Long id;
	private int rating;

	
	public RatingRequest() {
		
	}
}
