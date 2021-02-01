package isa20.back.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Item;


public interface ItemRepository extends JpaRepository< Item, Long>
{

	List< Item > findByDrugId(Long id);
	
	List<Item> findByDrugIdAndQuantityGreaterThan(Long drugId , Integer i);
 }
