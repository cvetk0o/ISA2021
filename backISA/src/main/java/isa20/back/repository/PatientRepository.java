package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Patient;


public interface PatientRepository extends JpaRepository< Patient, Long >
{

}
