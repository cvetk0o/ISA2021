package isa20.back.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import isa20.back.model.Authority;
import isa20.back.model.RoleName;


public interface AuthorityRepository extends JpaRepository< Authority, Long >
{

	Optional< Authority > findByName(RoleName name);
}
