package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Dermatologist;


public interface DermatologistRepository extends JpaRepository< Dermatologist, Long >
{

}
