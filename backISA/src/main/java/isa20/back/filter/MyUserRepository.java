package isa20.back.filter;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;


import isa20.back.model.QUser;
import isa20.back.model.User;



public interface MyUserRepository extends JpaRepository< User, Long>, QuerydslPredicateExecutor< User > , QuerydslBinderCustomizer< QUser > {

	
	 @Override
	    default public void customize(
	      QuerydslBindings bindings, QUser root) {
	     
		 
		 

		 bindings.bind(String.class).all(new MultiValueBinding<StringPath, String> () {
		        @Override
		        public Optional< Predicate > bind(StringPath path, Collection<? extends String> values) {
		            BooleanBuilder predicate = new BooleanBuilder();
		            values.forEach( value -> predicate.or(path.containsIgnoreCase(value)));
		            return Optional.of(predicate);
		        }
		    });

		 
		 //ovo je za between 
		 
		  bindings.bind(root.id).all((path, value) -> {
	            Iterator<? extends Long> it = value.iterator();
	            Long from = it.next();
	            if (value.size() >= 2) {
	                Long to = it.next();
	                return Optional.of(path.between(from, to));
	            } else {
	                return Optional.of(path.goe(from));
	            }
	        });
		  

		  
		 
	        bindings.excluding(root.email);
	      }
}
