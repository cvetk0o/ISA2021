package isa20.back.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Consulting;
import isa20.back.model.ConsultingReservation;


public interface ConsultingRepo extends JpaRepository< Consulting, Long>
{

	List<Consulting> findAllByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(LocalDateTime time1 , LocalDateTime time2);
	
	Consulting findByStartTime(LocalDateTime time);
	
	
	
}
