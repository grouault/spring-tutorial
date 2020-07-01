package com.banque.security.handler;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RefererRedirectionAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	
	public RefererRedirectionAuthenticationSuccessHandler() {
		super();
        setUseReferer(true);
	}
	
}
