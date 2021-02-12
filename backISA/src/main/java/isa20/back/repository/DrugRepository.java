package isa20.back.repository;

import java.util.Collection;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;


import isa20.back.model.Drug;

import isa20.back.model.QDrug;



public interface DrugRepository extends JpaRepository< Drug,Long> 
{

	

	
}
	 
	 
