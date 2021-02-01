package isa20.back.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import isa20.back.security.CustomUserDetailsService;
import isa20.back.security.JwtAuthenticationEntryPoint;
import isa20.back.security.JwtAuthenticationFilter;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true, jsr250Enabled = true, prePostEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	
	/**
	 * securedEnabled: It enables the @Secured annotation using which you can
	 * protect your controller/service methods
	 * 
	 * @Secured("ROLE_ADMIN")
	 * 
	 * jsr250Enabled: It enables the @RolesAllowed annotation
	 * 
	 * @RolesAllowed("ROLE_ADMIN")
	 * 
	 * 
	 * prePostEnabled: It enables more complex expression based access control
	 * syntax with @PreAuthorize and @PostAuthorize annotations
	 * 
	 * @PreAuthorize("isAnonymous()")
	 * 
	 * @PreAuthorize("hasRole('USER')")
	 */
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean( BeanIds.AUTHENTICATION_MANAGER )
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();

	}
	
	
	@Override
	public void configure( AuthenticationManagerBuilder auth ) throws Exception
	{
			auth.userDetailsService(customUserDetailsService) .passwordEncoder( passwordEncoder() );
	
	}
	
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception
	{
		// TODO Auto-generated method stub
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint( unauthorizedHandler ).and().sessionManagement()
		.sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and().authorizeRequests()
		.antMatchers( "/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js" ).permitAll()
		.antMatchers( "/api/auth/signUp" , "/api/auth/login" ,"/api/auth/activate/**"  , "/api/pharmacyController/**" , "/api/userFilterController/**" , "/api/userController/**" , "/api/drugController/**" , "/api/patientController/**").permitAll().antMatchers( "/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability" ).permitAll()
		.antMatchers( HttpMethod.GET, "/api/user/**" ).permitAll().anyRequest().authenticated();

// Add custom JWT security filter
http.addFilterBefore( jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class );

	
	}
	
	
	
}
