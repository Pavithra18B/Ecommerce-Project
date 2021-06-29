package com.example.Project.BackendProject.Model;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "categories")
@Data
@Slf4j
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_Id")
	private Long categoryId;

	@Column(name = "category_Name")
	private  String categoryName;
	@Column(name = "description")
	private  String description;
	@Column(name = "image_Url")
	private  String imageUrl;

	public Category() {
		log.info("category Entity");
	}	

}
