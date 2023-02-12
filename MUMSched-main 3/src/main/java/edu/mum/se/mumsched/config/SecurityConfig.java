package edu.mum.se.mumsched.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService uds;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationHandler successHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()
			.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/", "/home", "/admin/entry/delete/1").permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.requestMatchers("/faculty/**").hasRole("FACULTY")
					.requestMatchers("/student/**").hasRole("STUDENT")
					.anyRequest().authenticated())
			.formLogin((form) -> form.loginPage("/login").successHandler(successHandler).permitAll())
				.logout((logout) -> logout.permitAll());
		
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/resources/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(uds);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}
}
