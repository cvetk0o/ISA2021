package isa20.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import isa20.back.model.Drug;

public interface DrugRepository extends JpaRepository< Drug,Long>
{

}
