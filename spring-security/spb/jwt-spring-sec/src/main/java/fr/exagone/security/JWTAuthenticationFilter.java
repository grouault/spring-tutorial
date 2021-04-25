package fr.exagone.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.exagone.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// recuperation des parametres d'authentification dans un objet JSon a deserialiser.
		AppUser user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		} catch (Exception e) {
			throw new RuntimeException(e); // pour que le user recoit qqchose
		}
		
		// tentative d'authentification qui reste la même
		// par défaut on se base sur le manager authenticationManager.
		System.out.println("** attemptAuthentication ***");
		System.out.println("username = " + user.getUsername());
		System.out.println("password = " + user.getPassword());
		System.out.println("************");
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		System.out.println("** successfulAuthentication ***");

		// recuperation du principal
		User springUser = (User)authResult.getPrincipal();
		
		// construction du jwt.
		String jwtToken = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		
		
		// mise dans la réponse du JWT
		// Ajout du préfixe : Bearer + Token
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwtToken);
		
	}
}
