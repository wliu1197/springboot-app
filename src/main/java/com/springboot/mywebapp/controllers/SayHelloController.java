package com.springboot.mywebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

	// http://localhost:8081/say-hello
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello!";
	}
	
	// http://localhost:8081/say-hello-jsp
	// say-hello-jsp => sayHello.jsp
	// we have config it in application.properties and org.apache.tomcat.embed dependency is added
	// spring.mvc.view.prefix=/WEB-INF/jsp/
	// spring.mvc.view.suffix=.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
}
