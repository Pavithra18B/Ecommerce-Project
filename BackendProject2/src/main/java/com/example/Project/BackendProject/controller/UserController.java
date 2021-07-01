package com.example.Project.BackendProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.User;
import com.example.Project.BackendProject.Service.UserService;
import com.example.Project.BackendProject.controllerInterface.UserContIntrf;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController implements UserContIntrf {
	@Autowired
	private UserService userService;

	@Override
	@PostMapping("/adduser")
	@ApiOperation(value = "add user")
	public User addUser(@RequestBody UserRequest userRequest) {

		log.info(" add User");
		return userService.addUser(userRequest);
	}

	@GetMapping("/viewpage")
	Page<User> getUser(@PageableDefault(sort = { "userId" }) final Pageable page) {
		log.info("display all users");
		return userService.getUser(page);

	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ApiOperation(value = "list of users with details")
	public List listUser() {
		log.info(" list of Users");
		return userService.findAll();
	}

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
	@ApiOperation(value = "get user with details")
	public User getOne(@PathVariable(value = "user_id") int userId) {
		return userService.findById(userId);
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete users details")
	public User deleteUser(@PathVariable(value = "user_id") int userId) {
		userService.delete(userId);
		return new User(userId);
	}

}
