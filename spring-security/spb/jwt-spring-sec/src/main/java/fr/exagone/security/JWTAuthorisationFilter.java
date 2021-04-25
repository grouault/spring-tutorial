package fr.exagone.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorisationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		// recuperation du token
		// check la bonne récupération
		String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
		if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response); // on poursuit dans la chaine des filtres
			return; // on quitte la méthode
		}
		
		// parsing du token avec verification du secret.
		// renvoie une exception : SignatureException si pb.
		// pris en compte dans ExceptionTranslationFilter en AccessDeniedException
		Claims claims = Jwts.parser()
					// precise le secret pour le parser
					.setSigningKey(SecurityConstants.SECRET)
					// parse les claims en suppriment le prefixe
					.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, ""))
					// recuperation des datas du token.
					.getBody();
		
		// recuperation des datas
		String username = claims.getSubject(); // user
		// recuperation des roles et conversion dans le format Spring : GrantedAutority
		ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>)claims.get("roles");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.get("authority")));
		});
		
		// creation d'un objet usernameAuthenticationToken
		// pour chargemennt dans Spring.
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
		// on met le token dans le context Spring pour que Spring soit au courant du user et de ses roles.
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		filterChain.doFilter(request, response);
		
	}

}
