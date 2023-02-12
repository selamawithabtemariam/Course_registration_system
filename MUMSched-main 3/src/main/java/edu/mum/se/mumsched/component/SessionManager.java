package edu.mum.se.mumsched.component;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mum.se.mumsched.domain.CustomUser;

@Component
public class SessionManager {

	public CustomUser getUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return (CustomUser) securityContext.getAuthentication().getPrincipal();
	}
}
