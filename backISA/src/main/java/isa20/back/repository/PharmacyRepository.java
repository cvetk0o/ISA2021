package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Pharmacy;


public interface PharmacyRepository extends JpaRepository< Pharmacy , Long >
{

}
