package isa20.back.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.ConsultingReservation;


public interface ConsultingReservationRepository extends JpaRepository< ConsultingReservation, Long >
{

		List< ConsultingReservation > findAllByConsulting_StartTimeLessThanEqualAndConsulting_EndTimeGreaterThanEqual(LocalDateTime time1 , LocalDateTime time2);
		
		List<ConsultingReservation> findAllByConsultingStartTime(LocalDateTime time);
}

