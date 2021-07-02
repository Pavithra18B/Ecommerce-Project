package com.example.Project.BackendProject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.Product;
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
	@GetMapping(value = "/user")
	@ApiOperation(value = "list of users with details")
	public List listUser() {
		log.info(" list of Users");
		return userService.findAll();
	}

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping(value = "/user/{user_id}")
	@ApiOperation(value = "get user with details")
	public ResponseEntity<ApiResponse> getOne(@PathVariable(value = "user_id") int userId) {
		try {
			User user = userService.findById(userId);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "User with the given userId is available "), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "userId is invalid"), HttpStatus.CONFLICT);
		}
	}
	@PreAuthorize("hasRole('admin')")
	@PostMapping("/user/{user_id}")
	@ApiOperation(value = "Update user")
	public ResponseEntity<ApiResponse> updateUser(@PathVariable("user_id") int userId,
			@RequestBody UserRequest userRequest) throws Exception {
		log.info("update User  details");
		try {
			User existUser = userService.findById(userId);
			userService.updateUser(userId, userRequest);
			return new ResponseEntity<>(new ApiResponse(true, "User has been updated"), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "userId is invalid"), HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/user/{user_id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete users details")
	public User deleteUser(@PathVariable(value = "user_id") int userId) {
		userService.delete(userId);
		return new User(userId);
	}

}
