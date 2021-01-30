package isa20.back.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;


import isa20.back.model.Pharmacy;
import isa20.back.model.User;

@RestController
@RequestMapping( "api/userFilterController")
public class UserFilterController
{

	
	@Autowired
    private MyUserRepository myUserRepository;
	
	@Autowired
	private PharmacyRepo pharmacyRepo;


    
    
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public Iterable<User> findAllByWebQuerydsl(
      @QuerydslPredicate(root = User.class) Predicate predicate) {
        return myUserRepository.findAll(predicate);
    }

    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/pharm")
    @ResponseBody
    public Iterable<Pharmacy> findAllByPharm(
      @QuerydslPredicate(root = Pharmacy.class) Predicate predicate) {
        return pharmacyRepo.findAll(predicate);
    }
    
    
    
    
}
