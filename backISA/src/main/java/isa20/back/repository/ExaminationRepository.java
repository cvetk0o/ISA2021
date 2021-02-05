package isa20.back.repository;

import java.util.List;
import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Dermatologist;
import isa20.back.model.Examination;


public interface ExaminationRepository extends JpaRepository< Examination,Long>
{
	List<Examination>  findByStatusEqualsAndDermatologistIn(int status , Set<Dermatologist> dermatologists );
	
	List<Examination> findByPatientId( Long id);

}
