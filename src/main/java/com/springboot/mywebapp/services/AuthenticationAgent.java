package com.springboot.mywebapp.services;

import java.util.List;

import com.springboot.mywebapp.models.User;
import com.springboot.mywebapp.repositories.UserJpaRepository;

public interface AuthenticationAgent {
	boolean validateUser(String username, String pwd);
	boolean checkUser(String username, String validatedUser);
	User findUserByName(String username);
	List<User> findAllUsers();
	public String getValidatedUser();
}
