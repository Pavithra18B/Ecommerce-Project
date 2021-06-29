package com.example.Project.BackendProject.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.RoleRequest;
import com.example.Project.BackendProject.Model.Role;
import com.example.Project.BackendProject.Repository.RoleRepo;
import com.example.Project.BackendProject.ServiceInterface.RoleServInter;


@Service
public class RoleService implements RoleServInter {

	@Autowired
	private RoleRepo roleRepo;

	public Role addRole(RoleRequest roleRequest) {

		Role role = new Role();
		System.out.println(role.toString());
		role.setRole_name(roleRequest.getRole_name());
		role.setRole_description(roleRequest.getRole_description());

		return roleRepo.save(role);
	}

	
}
