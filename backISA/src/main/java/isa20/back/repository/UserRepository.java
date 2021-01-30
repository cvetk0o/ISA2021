package isa20.back.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.User;


public interface UserRepository extends JpaRepository< User, Long >
{

	Optional< User > findByEmail(String email);
	
	Optional<User> findById(Long id);
	
	Boolean existsByEmail( String email );
	
}