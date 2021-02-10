package isa20.back.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.DrugReservation;


public interface DrugReservationRepository extends JpaRepository< DrugReservation, Long >
{
	
	
}
