package com.example.Project.BackendProject.Model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "usertable")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int user_id;

	@Column(name = "user_name")
	private String user_name;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "user_password")
	private String user_password;

	@Column(name = "email_id")
	private String email_id;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE",
	joinColumns = {
			@JoinColumn(name="USER_ID")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID")
	}
			)
	private Set<Role> role;

	public User() {
		System.out.println("User Entity");
	}

	public User(int id) {
		// TODO Auto-generated constructor stub
	}

	
}
