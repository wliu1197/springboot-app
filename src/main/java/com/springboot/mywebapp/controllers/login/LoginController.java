package com.springboot.mywebapp.controllers.login;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.mywebapp.services.AuthenticationAgent;



@Controller
@SessionAttributes("validateduser")
public class LoginController {
	@Autowired
	private AuthenticationAgent authenticationService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String loginFailedMessage  = "Login failed!";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String manageLoginUser(ModelMap model) {
		logger.info("------------------------here----------------------");
		
		model.put("name", authenticationService.getValidatedUser());
		model.put("validateduser", authenticationService.getValidatedUser());
		
		return "welcome";
	
	}
	

//using spring security to manage login infos	
/*	
    // http://localhost:8081/login
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(@RequestParam(required=false) String name, ModelMap model) {
		logger.info("------------------------here----------------------");
		if(name != null) {
			model.put("name", name);
		}
		return "login";
	}
		
	// http://localhost:8081/login
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@RequestParam String username,
			@RequestParam String password,
			ModelMap model) {
		if(authenticationService.validateUser(username, password)) {
			model.put("name", username);
			return "welcome";
		}else {
			model.put("failedLoginMessage", loginFailedMessage);
			return "login";
		}
	}
	
	// http://localhost:8081/login
	@RequestMapping(value="validateuser", method=RequestMethod.POST)
	public String validateUser(@RequestParam String username,
			@RequestParam String password,
			ModelMap model) {
		logger.info("------------validate user-----------");
		if(authenticationService.validateUser(username, password)) {
			model.put("name", username);
			model.put("validateduser", username);
			return "welcome";
		}else {
			model.put("failedLoginMessage", loginFailedMessage);
			return "login";
		}
	}
*/
}
