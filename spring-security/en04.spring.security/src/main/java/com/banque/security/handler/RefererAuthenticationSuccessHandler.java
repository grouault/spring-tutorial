package com.banque.security.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

public class RefererAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SessionLocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        setLocale(authentication, request, response);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    protected void setLocale(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
    	
    	// localeResolver.setLocale(request, response, Locale.ENGLISH);
    	localeResolver.setLocale(request, response, Locale.FRANCE);
    	// localeResolver.setLocale(request, response, Locale.GERMANY);
    	/*
    	Locale locale = new Locale("fr","FR");
    	localeResolver.setLocale(request, response, locale);
    	localeResolver.setDefaultLocale(locale);
    	localeResolver.setLocaleAttributeName("fr_FR");
    	
    	LocaleResolver myLocalResolver = RequestContextUtils.getLocaleResolver(request);
    	System.out.println("maj locale");
    	response.setLocale(locale);
    	System.out.println(localeResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    	HttpSession session = request.getSession(false);
    	session.getAttribute(localeResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    	*/
    	/*
    	if (authentication != null) {
        	
            Object principal = authentication.getPrincipal();
            if (principal instanceof LocaleProvider) {
                LocaleProvider localeProvider = (LocaleProvider) principal;
                Locale providedLocale = localeProvider.getLocale();
                localeResolver.setLocale(request, response, providedLocale);
            }
            
        }
    	*/
    }
	
}
