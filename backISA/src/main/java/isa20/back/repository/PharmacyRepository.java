package isa20.back.repository;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;


import isa20.back.model.Dermatologist;
import isa20.back.model.Item;
import isa20.back.model.Pharmacist;
import isa20.back.model.Pharmacy;
import isa20.back.model.QPharmacy;


public interface PharmacyRepository extends JpaRepository< Pharmacy, Long>, QuerydslPredicateExecutor< Pharmacy > , QuerydslBinderCustomizer< QPharmacy > {

	
		Pharmacy  findByItemList(Item item);
		
		List<Pharmacy> findAllByPharmacistsIn(List<Pharmacist> lista);
		
		List<Pharmacy> findAllByDermatologistsIn(List<Dermatologist> lista);
		
		Optional< Pharmacy > findByPharmacists(Pharmacist pharm);
		
		List<Pharmacy> findAllByIdIn(List< Long > ids , Sort s);
		
		List<Pharmacy> findAllByItemListIn(Set<Item> items);
 	
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
		 
		 
	        // Make a kind of 'between' filter for Person.age property
	        bindings.bind(root.consultingPrice).all((path, value) -> {
	            Iterator<? extends Long> it = value.iterator();
	            Long from = it.next();
	            if (value.size() >= 2) {
	            	System.out.println( "DVAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" );
	                Long to = it.next();
	                return Optional.of(path.between(from, to));
	            } else {
	            	
	                return Optional.of(path.goe(from));
	            }
	        });

	
		 
		 
	 }
}
