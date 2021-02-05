package isa20.back.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;


import isa20.back.model.Item;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.model.QPharmacy;


public interface PharmacyRepository extends JpaRepository< Pharmacy, Long>, QuerydslPredicateExecutor< Pharmacy > , QuerydslBinderCustomizer< QPharmacy > {

	
		Pharmacy  findByItemList(Item item);
		
		List<Pharmacy> findAllByPharmacistsIn(List<Pharmacist> lista);
		
		Optional< Pharmacy > findByPharmacists(Pharmacist pharm);
		
		List<Pharmacy> findAllByIdIn(List< Long > ids , Sort s);
 	
	 @Override
	    default public void customize(
	      QuerydslBindings bindings, QPharmacy root) {
	        		

		 bindings.bind(String.class).all(new MultiValueBinding<StringPath, String> () {
		        @Override
		        public Optional< Predicate > bind(StringPath path, Collection<? extends String> values) {
		            BooleanBuilder predicate = new BooleanBuilder();
		            values.forEach( value -> predicate.or(path.containsIgnoreCase(value)));
		            return Optional.of(predicate);
		        }
		    });

		 
		 
	 }
}
