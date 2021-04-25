package fr.exagone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		// recuperation des utilisateur avec la couche de service : UserDetailService	
		// check-password
		auth
			.userDetailsService(userDetailService) // custo du service pour la récupération du user et de ses rôles.
			.passwordEncoder(getBCryptPasswdEncoder()); // chargement pour UserDetail et vérification du password pour l'Authenfication manager
	
	 }
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {

		// h2: problème affichage des frames. 
        http.headers().frameOptions().disable();

		// application stateless : désactivation
		http.csrf().disable();
		
		// permet de désactiver la session.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// url autorisée sans autentification
		http.authorizeRequests()
			.antMatchers("/login/**","/register/**").permitAll();
		
		// URL de type POST ET /tasks/** nécessite le rôle ADMIN
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/tasks/**").hasAuthority("ADMIN");
		
		// toutes les autres necessistent une authentification
		http.authorizeRequests()
			.anyRequest().authenticated();

		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	 }
	
}
