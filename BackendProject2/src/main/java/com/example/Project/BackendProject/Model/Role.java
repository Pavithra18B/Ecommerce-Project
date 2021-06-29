package com.example.Project.BackendProject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private int role_id;

	@Column(name = "role_name")
	private String role_name;

	@Column(name = "role_description")
	private String role_description;

	public Role() {
		System.out.println("Role Entity");
	}
}
