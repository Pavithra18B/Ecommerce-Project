package com.example.Project.BackendProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
