package isa20.back.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Consulting;
import isa20.back.model.DrugReservation;
import isa20.back.model.Item;
import isa20.back.model.Patient;


public interface PatientRepository extends JpaRepository< Patient, Long >
{

	Patient findByDrugReservations(DrugReservation reservation);
	
	List< Patient > findAllByConsultings_StartTimeLessThanEqualAndConsultings_EndTimeGreaterThanEqual(LocalDateTime time1 , LocalDateTime time2);
	
	Optional< Patient > findByConsultings(Consulting c);
 	
}
