package isa20.back.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dermatologist extends User
{
	
	private double avgRate;
	
	@OneToMany
	@JoinColumn(name="dermatologistId")
	private List< Rating > ratings;

	public Dermatologist() {
		
	}
	
	
	public void calculateAvg() {
		
		this.avgRate = this.ratings.stream().mapToInt( rating -> rating.getGrade() ).average().orElse( 0.0 );
		
		if(this.avgRate < 1.5 ) {
			this.avgRate = 1;
		
		}else if( this.avgRate>=1.5 && this.avgRate <2.5) {
			
			this.avgRate = 2;
		}else if( this.avgRate>=2.5 && this.avgRate <3.5) {
			
			this.avgRate = 3;
		}else if( this.avgRate>=3.5 && this.avgRate <4.5) {
			
			this.avgRate = 4;
		}else if( this.avgRate>4.5) {
			
			this.avgRate = 5;
		}
	
		
	}
}
