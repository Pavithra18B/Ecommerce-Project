package com.example.Project.BackendProject.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.RoleRequest;
import com.example.Project.BackendProject.Model.Role;
import com.example.Project.BackendProject.Repository.RoleRepo;
import com.example.Project.BackendProject.ServiceInterface.RoleServInter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleService implements RoleServInter {

	@Autowired
	private RoleRepo roleRepo;
//add Role
	public Role addRole(RoleRequest roleRequest) {

		Role role = new Role();
		log.info(role.toString());
		role.setRoleName(roleRequest.getRoleName());
		role.setRoleDescription(roleRequest.getRoleDescription());

		return roleRepo.save(role);
	}

}
