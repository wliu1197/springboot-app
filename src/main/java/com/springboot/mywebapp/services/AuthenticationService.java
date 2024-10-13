package com.springboot.mywebapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.mywebapp.models.User;
import com.springboot.mywebapp.repositories.UserJpaRepository;

@Service
public class AuthenticationService implements AuthenticationAgent{
	@Autowired
	private UserJpaRepository repository;

	
	@Override
	public boolean validateUser(String username, String pwd) {
		List<User> users =repository.findActiveUser(username);
		//List<User> users =repository.findByUserName(username);
		if(users == null || users.isEmpty()) return false;
		User user = users.get(0);
		if(user.getUserName().equals(username)
				&& user.getPassword().equals(pwd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public User findUserByName(String username) {
		List<User> users =repository.findActiveUser(username);
		if(users == null || users.isEmpty()) 
			return null;
		return users.get(0);
	}
	
	@Override
	public boolean checkUser(String username, String validatedUser) {
		return  validatedUser != null && validatedUser.equals(username);	
	}
	
	@Override
	public List<User> findAllUsers(){
		return repository.findAllActiveUser();
	}
	
	@Override
	public String getValidatedUser() {
		Authentication autthentication =  SecurityContextHolder.getContext().getAuthentication();
		return autthentication.getName();
	}
}