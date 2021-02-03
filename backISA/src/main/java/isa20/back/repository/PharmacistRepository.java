package isa20.back.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import isa20.back.model.Consulting;
import isa20.back.model.Pharmacist;


public interface PharmacistRepository extends JpaRepository< Pharmacist, Long >
{

	List<Pharmacist> findAllByVacations_VacationFromLessThanEqualAndVacations_VacationToGreaterThanEqual(LocalDateTime time1,LocalDateTime time2);
	
	List<Pharmacist> findAllByVacations_VacationFromGreaterThan(LocalDateTime time1);
	
	List<Pharmacist> findAllByIdNotIn(List<Long> lista);
	
	List<Pharmacist> findByConsultingsIdIn(List<Long> consultings);
	
	Optional<Pharmacist> findByConsultings(Consulting consulting);
	
	
	
}
