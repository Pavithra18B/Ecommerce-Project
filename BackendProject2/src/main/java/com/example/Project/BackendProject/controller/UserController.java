package com.example.Project.BackendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RestController
public class UserController implements UserContIntrf {
	@Autowired
	private UserService userService;

	/*
	 * @PostConstruct public void UserRole() {
	 * System.out.println("set default user function "); userService.UserRole();
	 * 
	 * }
	 */

	@Override
	@PostMapping("/adduser")
	@ApiOperation(value = "add user")
	public User addUser(@RequestBody UserRequest userRequest) {

		System.out.println("User controller");
		return userService.addUser(userRequest);
	}
	
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    @ApiOperation(value = "list of users with details")
    public List listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('user')")
    @PreAuthorize("hasAnyRole('customer', 'admin')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "get user with details")
    public User getOne(@PathVariable(value = "id") int id){
        return userService.findById(id);
    }

	/*
	 * @RequestMapping(value="/signup", method = RequestMethod.POST) public User
	 * create(@RequestBody UserRequest userRequest){ return
	 * userService.save(userRequest); }
	 */
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "delete users details")
    public User deleteUser(@PathVariable(value = "id") int id){
        userService.delete(id);
        return new User(id);
    }

	/*
	 * @DeleteMapping("/removeuser/{user_id}")
	 * 
	 * @PreAuthorize("hasRole('admin')") public void
	 * delUser(@PathVariable("user_id") Long user_id) {
	 * userService.delUser(user_id);
	 * 
	 * }
	 */

}
