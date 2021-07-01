package com.example.Project.BackendProject.ServiceInterface;

import com.example.Project.BackendProject.Dto.RoleRequest;
import com.example.Project.BackendProject.Model.Role;

public interface RoleServInter {
	public Role addRole(RoleRequest roleRequest);
}
