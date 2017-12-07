package com.remises.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private static final Logger LOGGER = Logger.getLogger(CustomSuccessHandler.class);
	
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		
		if (response.isCommitted()) {
			LOGGER.info("No puede redirigir");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();		
		List<String> roles = new ArrayList<String>();
		
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		
		if (isDba(roles)) {
			url = "/db";
		} else if (isAdmin(roles)) {
			url = "/admin";
		} else if (isUser(roles)) {
			url = "/home";
		} else {
			url = "/accessDenied";
		}
		return url;
	}
	
	private boolean isDba(List<String> roles) {
		return (roles.contains("ROLE_DBA")) ? true : false; 
	}

	private boolean isAdmin(List<String> roles) {
		return (roles.contains("ROLE_ADMIN")) ? true : false; 
	}
	
	private boolean isUser(List<String> roles) {
		return (roles.contains("ROLE_USER")) ? true : false; 
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	
}
