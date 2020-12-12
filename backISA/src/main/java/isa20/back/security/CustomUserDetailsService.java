package isa20.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;





import isa20.back.model.User;
import isa20.back.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		
		User user = userRepository.findByEmail( email )
				.orElseThrow(
				() -> new UsernameNotFoundException( "User not found with  email : " + email) );
		
		return UserPrincipal.create( user );

	}

	
	public UserDetails loadUserById( Long id) {
		User user = userRepository.findById( id )
				.orElseThrow( () -> new UsernameNotFoundException( "User not found with id : " + id ));
	
		return UserPrincipal.create( user );
	}
	
}
