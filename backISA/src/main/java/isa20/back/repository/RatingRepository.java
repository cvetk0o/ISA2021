package isa20.back.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Rating;


public interface RatingRepository extends JpaRepository< Rating, Long>
{

	List< Rating > findByPatientId(Long id);
}
