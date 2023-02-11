package edu.mum.se.mumsched.domain;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	private final static boolean ENABLED = true;
	private final static boolean ACCOUNT_NON_EXPIRED = true;
	private final static boolean CREDENTIALS_NON_EXPIRED = true;
	private final static boolean ACCOUNT_NON_LOCKED = true;
	
	private int id;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int userId) {

		super(username, password, ENABLED, ACCOUNT_NON_EXPIRED, CREDENTIALS_NON_EXPIRED, ACCOUNT_NON_LOCKED, authorities);
		this.id = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
