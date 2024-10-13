package com.springboot.mywebapp.commandline.runners;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.springboot.mywebapp.models.User;
import com.springboot.mywebapp.models.Todo;
import com.springboot.mywebapp.repositories.TodoJpaRepository;
import com.springboot.mywebapp.repositories.UserJpaRepository;


@Component
public class UserJdbcCommandLineRunner implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(getClass());
/*	
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private TodoJpaRepository todoRepository;


	//anything needs to run at start of spring application can use CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		
		//create users in db
		logger.info("--------------------- Creating db data  -----------------");
		User userwen = userRepository.save(new User("wen","comein22"));
		User userwen2 = userRepository.save(new User("hao","comein22"));
		
		todoRepository.save(new Todo("Go Japan",  LocalDate.now(), false, userwen));
		todoRepository.save(new Todo("Go Sydeny", LocalDate.now(), false, userwen));
		todoRepository.save(new Todo("Make 120k", LocalDate.now(), true, userwen));
	
	}
	
*/
	//create initial users in spring security configuration class
	@Override
	public void run(String... args) throws Exception {
		
		//create users in db
		logger.info("--------------------- Creating db data in spring security class -----------------");
		
	
	}
}
