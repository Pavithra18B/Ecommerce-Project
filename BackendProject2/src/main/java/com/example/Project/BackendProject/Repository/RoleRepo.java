package com.example.Project.BackendProject.Repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Model.Role;


@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

}
