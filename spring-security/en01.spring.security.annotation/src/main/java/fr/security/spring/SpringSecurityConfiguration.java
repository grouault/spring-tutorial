package fr.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Configuration sous la forme d'une classe Java. <br/>
 *
 * Ce fichier ne concerne que Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		// Ne pas indiquer ROLE_, c'est ajoute automatiquement
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "USER");
		auth.inMemoryAuthentication().withUser("guest").password("guest").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			// authorisation
			.authorizeRequests()
				.antMatchers("/user/pageA.jsp").access("hasRole('ROLE_USER')")
				.antMatchers("/adm/pageB.jsp").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()
			// login
			.formLogin()
				.loginPage("/login.jsp").permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/menu.jsp")
				.failureForwardUrl("/login.jsp?erreur=true")
				.and()
			// logout
			.logout()
			 	// url qui déclenche le mécanisme de déconnexion. [valeur par défaut : /logout]
				.logoutUrl("/logout")
				// page à afficher quand le logout est realisé.
				.logoutSuccessUrl("/logout-success.jsp").permitAll()
				// TODO : ??
				// .logoutSuccessHandler(LogoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.and()
			// access-denied for role.
			.exceptionHandling()
				.accessDeniedPage("/mauvaisrole.jsp")
			;

	}
	
	
}
