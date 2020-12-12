package isa20.back.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;





import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Authority  {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

	@Enumerated( EnumType.STRING )
    @NaturalId
    private RoleName name;

	

}
