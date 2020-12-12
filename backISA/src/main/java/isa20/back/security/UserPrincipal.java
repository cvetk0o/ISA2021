package isa20.back.security;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;


import isa20.back.model.User;
import lombok.Setter;


import lombok.Getter;


@Getter
@Setter
public class UserPrincipal implements UserDetails
{
	private static final long serialVersionUID = 2196229078859399515L;

	private Long id;


	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;
	
	private Collection< ? extends GrantedAuthority > authorities;
	
	
	public UserPrincipal ( Long id, String email, String password ,Collection< ? extends GrantedAuthority > authorities  ) {
		
		this.id= id;
		this.email = email;
		this.password = password ;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create( User user )
	{
		List< GrantedAuthority > authorities = user.getAuthorities().stream()
				.map( role -> new SimpleGrantedAuthority( role.getName().name() ) ).collect( Collectors.toList() );

		return new UserPrincipal( user.getId(),  user.getEmail(), user.getPassword(),
				authorities );

	}

	@Override
	public Collection< ? extends GrantedAuthority > getAuthorities()
	{
		// TODO Auto-generated method stub
		return authorities;

	}


	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return this.password;

	}


	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.email;

	}


	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;

	}


	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;

	}


	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;

	}


	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;

	}

}
