package com.springboot.mywebapp.controllers.login;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.ISBN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.mywebapp.models.Todo;
import com.springboot.mywebapp.models.User;
import com.springboot.mywebapp.repositories.UserJpaRepository;
import com.springboot.mywebapp.services.AuthenticationAgent;
import com.springboot.mywebapp.services.AuthenticationService;
import com.springboot.mywebapp.services.TodoManageAgent;
import com.springboot.mywebapp.services.TodoManageService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("validateduser")
public class TodoController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TodoManageService todoManageService;
	@Autowired
	private AuthenticationService authenticationService;
	
	@RequestMapping(value="todo-list", method=RequestMethod.GET)
	public String showTodoListGet(@RequestParam String username,
			ModelMap model) throws Exception {
		
		if(model.get("errorMessage") != null) {
			logger.info(model.get("errorMessage").toString());
		}
		
		//setting default value of todo as binding modelAttribute = todo in jsp when create new todo need this
		Todo defaultTodo = new Todo("default description",null,false,null);
		model.put("todo", defaultTodo);
		
		logger.info((String)model.get("validateduser"));
		if(!authenticationService.checkUser(username, (String)model.get("validateduser"))) {
			throw new Exception("User not logined"); 
		}
		
		logger.info("------------ showTodoList user-----------");
		
		User user = authenticationService.findUserByName(username);
		List<Todo> todos = (List<Todo>) todoManageService.findTodoByUserId(user.getId());
		if(todos !=null && !todos.isEmpty()) {
			model.put("name", username);
			model.put("todos", todos);
			return "todo";
		}else {
			model.put("name", username);
			model.put("NotTodoFound", "Can't find to do list.");
			return "todo";
		}
		
	}
	
	
	@RequestMapping(value="todo-list", method=RequestMethod.POST)
	public ModelAndView showTodoList(@RequestParam String username) {
		logger.info("------------ showTodoList user-----------");
		return new ModelAndView("redirect:/todo-list?username="+username);
		
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public ModelAndView addTodo(ModelMap map, @Valid Todo todo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			String errorMessage = result.getFieldErrors().stream()
					.map(error -> error.getField() + " : " + error.getDefaultMessage())
					.collect(Collectors.toList()).toString();
			
			//actually stores the attributes in a flashmap 
			//(which is internally maintained in the users session and removed once the next redirected request gets fulfilled)
			attributes.addFlashAttribute("errorMessage",errorMessage);
			
			return new ModelAndView("redirect:/todo-list?username=" + map.get("validateduser").toString());
		}
		todoManageService.addTodo(todo.getDescription(), todo.getTargetDate(), (String)map.get("validateduser"));
		return new ModelAndView("redirect:/todo-list?username=" + map.get("validateduser").toString());
	}
	
	@RequestMapping(value="delete-todo", method=RequestMethod.POST)
	public ModelAndView showTodoList(@RequestParam Long todoId, ModelMap map) {
		logger.info("------------ showTodoList user-----------");
		todoManageService.deleteTodo(todoId);
		return new ModelAndView("redirect:/todo-list?username="+map.get("validateduser").toString());
		
	}
	
	/*
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public ModelAndView updateTodo(@RequestParam Long todoId, 
			@RequestParam String description,
			@RequestParam(required = false) String done, 
			@RequestParam LocalDate targetDate,
			ModelMap map) {
		logger.info("------------ update todo user-----------");
		Todo todo =  todoManageService.getTodo(todoId);
		todo.setTargetDate(targetDate);
		if(done == null) 
			todo.setDone(false);
		else
			todo.setDone(true);
		todo.setDescription(description);
		todoManageService.saveTodo(todo);
		return new ModelAndView("redirect:/todo-list?username="+map.get("validateduser").toString());
		
	}
	*/
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public ModelAndView updateTodo(ModelMap map, Todo todo) {
		logger.info("------------ update todo user-----------");
		Todo todoInDb =  todoManageService.getTodo(todo.getId());
		todoInDb.setTargetDate(todo.getTargetDate());
		if(todo.getDone() == null) 
			todoInDb.setDone(false);
		else
			todoInDb.setDone(true);
		todoInDb.setDescription(todo.getDescription());
		todoManageService.saveTodo(todoInDb);
		return new ModelAndView("redirect:/todo-list?username="+map.get("validateduser").toString());
		
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String updateTodo(@RequestParam Long todoId, ModelMap map) {
		logger.info("------------ update todo user-----------");
		map.put("todo", todoManageService.getTodo(todoId));
		return "updateTodo";
		
	}

}
