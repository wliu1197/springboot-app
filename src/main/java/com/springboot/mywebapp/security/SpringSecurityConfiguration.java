package com.springboot.mywebapp.security;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;

import com.springboot.mywebapp.repositories.TodoJpaRepository;
import com.springboot.mywebapp.repositories.UserJpaRepository;
import com.springboot.mywebapp.services.AuthenticationAgent;
import com.springboot.mywebapp.models.Todo;
import com.springboot.mywebapp.repositories.TodoJpaRepository;
import com.springboot.mywebapp.repositories.UserJpaRepository;
import static org.springframework.security.config.Customizer.withDefaults; 

@Configuration
public class SpringSecurityConfiguration {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private TodoJpaRepository todoRepository;
	
	@Autowired
	private AuthenticationAgent authenticationService;
	
	@Bean
	public InMemoryUserDetailsManager createDetailsManager() {
		//create initial users and todo list
		createDBdata();
		//find all users from db
		List<com.springboot.mywebapp.models.User>  usersFromDB =  authenticationService.findAllUsers();
			
		//convert db users into spring security UserDetails
		Function<String, String> encoder = input -> passwordEncoder().encode(input);
		List<UserDetails> users = usersFromDB.stream().map(userFromDB ->  User.builder().passwordEncoder(encoder)
																	  .username(userFromDB.getUserName())
																	  .password(userFromDB.getPassword())
																	  .roles("USER","ADMIN")
																	  .build())
																	  .collect(Collectors.toList());
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//to fix 403 forbidden error (type=Forbidden, status=403)
	//disable csrf
	//ensure all request are authenticated
	//enabling use of frame in our application
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
	
	public void createDBdata() {
		//create users in db
		logger.info("--------------------- Creating db data  -----------------");
		com.springboot.mywebapp.models.User userwen = userRepository.save(new com.springboot.mywebapp.models.User("wen","comein22"));
		com.springboot.mywebapp.models.User userwen2 = userRepository.save(new com.springboot.mywebapp.models.User("hao","comein22"));
		
		todoRepository.save(new Todo("Go Japan",  LocalDate.now(), false, userwen));
		todoRepository.save(new Todo("Go Sydeny", LocalDate.now(), false, userwen));
		todoRepository.save(new Todo("Make 120k", LocalDate.now(), true, userwen));
	}
}
