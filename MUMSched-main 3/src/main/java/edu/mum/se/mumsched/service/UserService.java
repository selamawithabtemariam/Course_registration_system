package edu.mum.se.mumsched.service;

import org.springframework.beans.factory.annotation.Autowired;

import edu.mum.se.mumsched.dao.UserDao;

public interface UserService {
	
	Integer createUser(String username, String role);

	void updateUser(Integer id, String email);
}
