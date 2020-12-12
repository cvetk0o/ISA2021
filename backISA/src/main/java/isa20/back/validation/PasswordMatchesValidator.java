package isa20.back.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import isa20.back.dto.request.SignUpRequest;




public class PasswordMatchesValidator implements ConstraintValidator< PasswordMatches, Object >
{
	
	  @Override
	    public void initialize(PasswordMatches constraintAnnotation) {       
	    }

	    @Override
	    public boolean isValid(Object obj, ConstraintValidatorContext context){   
	        SignUpRequest user = (SignUpRequest) obj;
	        return user.getPassword1().equals(user.getPassword2());    
	    }   

}
