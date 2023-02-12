package edu.mum.se.mumsched.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import edu.mum.se.mumsched.domain.CustomUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			try {
				if(authority.getAuthority().equals("ROLE_STUDENT")) {
					redirectStrategy.sendRedirect(request, response, "/student");
				} else if(authority.getAuthority().equals("ROLE_FACULTY")) {
					redirectStrategy.sendRedirect(request, response, "/faculty");
				} else if(authority.getAuthority().equals("ROLE_ADMIN")) {
					redirectStrategy.sendRedirect(request, response, "/admin");
				} else {
					redirectStrategy.sendRedirect(request, response, "/access-denied");
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
