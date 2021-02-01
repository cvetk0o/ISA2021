package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.DrugReservation;
import isa20.back.model.Item;
import isa20.back.model.Patient;


public interface PatientRepository extends JpaRepository< Patient, Long >
{

	Patient findByDrugReservations(DrugReservation reservation);
}
