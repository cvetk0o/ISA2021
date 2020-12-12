package isa20.back.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import isa20.back.dto.request.SignUpRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User 
{
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String country;

	@NotBlank
	private String city;

	@NotBlank
	private String street;
	
	@NotBlank
	private String phoneNumber;
	
	@ManyToMany
	private Set<Authority> authorities =new HashSet< Authority>();
	
	private Boolean activated;
	
	
	public User() {
		
	}
	
	public User(SignUpRequest signUpRequest) {
		this.name = signUpRequest.getName();
		this.lastname = signUpRequest.getLastname();
		this.email = signUpRequest.getEmail();
		this.password = signUpRequest.getPassword1();
		this.country = signUpRequest.getCountry();
		this.city = signUpRequest.getCity();
		this.street = signUpRequest.getStreet();
		this.phoneNumber = signUpRequest.getPhoneNumber();
		this.activated = false;
	
	
	}
	
}
