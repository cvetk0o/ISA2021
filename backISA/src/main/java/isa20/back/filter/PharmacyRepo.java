package isa20.back.filter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;


import isa20.back.model.Pharmacy;
import isa20.back.model.QPharmacist;
import isa20.back.model.QPharmacy;
import isa20.back.model.QUser;
import isa20.back.model.User;

public interface PharmacyRepo extends JpaRepository< Pharmacy, Long>, QuerydslPredicateExecutor< Pharmacy > , QuerydslBinderCustomizer< QPharmacy > {

	
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
