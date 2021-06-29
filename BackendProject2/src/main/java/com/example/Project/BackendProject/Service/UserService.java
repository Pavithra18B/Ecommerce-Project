package com.example.Project.BackendProject.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.Project.BackendProject.Dto.UserRequest;
import com.example.Project.BackendProject.Model.Role;
import com.example.Project.BackendProject.Model.User;
import com.example.Project.BackendProject.Repository.RoleRepo;
import com.example.Project.BackendProject.Repository.UserRepo;
import com.example.Project.BackendProject.ServiceInterface.UserServIntr;

@Service
public class UserService implements UserServIntr {


	@Autowired
	private UserRepo userRepo;

	@Autowired 
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passEncode;

	Logger logger = LoggerFactory.getLogger(UserService.class);
 

	public String getEncodedPass(String user_password) {
	return passEncode.encode(user_password);
	
	}
	
    	public User addUser(UserRequest userRequest) {
		User user = new User();
		Set<Role> roles = new HashSet<>();
		System.out.println(user.toString());
		user.setUser_name(userRequest.getUser_name() );
		user.setFirst_name(userRequest.getFirst_name());
		user.setLast_name(userRequest.getLast_name());
		user.setEmail_id(userRequest.getEmail_id());
		user.setUser_password(getEncodedPass(userRequest.getUser_password()));
		for(Integer role_id: userRequest.getRole()) {
			Optional<Role> role = roleRepo.findById(role_id);
			if(!role.isPresent()) {		
			}else {
				roles.add(role.get());
			}
		}
		user.setRole(roles);
		return userRepo.save(user);
	}
	public List findAll() {
		List list = new ArrayList<>();
		userRepo.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		userRepo.deleteById(id);
	}


	@Override
	public User findById(int id) {
		return userRepo.findById(id).get();
	}

	@Override
	public Page<User> getUser(Pageable page) {
		logger.info("Page", page);
		Page<User> user = userRepo.findAll(page);
		logger.info("User", user);
		return userRepo.findAll(page);
	}}
