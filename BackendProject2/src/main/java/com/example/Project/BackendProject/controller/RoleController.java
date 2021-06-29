package com.example.Project.BackendProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.RoleRequest;
import com.example.Project.BackendProject.Model.Role;
import com.example.Project.BackendProject.Service.RoleService;
import com.example.Project.BackendProject.controllerInterface.RoleControIntr;

@RestController
public class RoleController implements RoleControIntr{
	@Autowired
	private RoleService roleService;

	@PostMapping("/addrole")
	@PreAuthorize("hasRole('admin')")
	public Role addRole(@RequestBody RoleRequest roleRequest) {
		return roleService.addRole(roleRequest);
	}
}
