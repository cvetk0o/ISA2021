package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Vacation;


public interface VacationRepository extends JpaRepository< Vacation, Long >
{

}
