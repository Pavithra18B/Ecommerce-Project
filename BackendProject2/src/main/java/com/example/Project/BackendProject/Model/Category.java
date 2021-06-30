package com.example.Project.BackendProject.Model;


import javax.persistence.*;

import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Dto.ProductRequest;

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
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private  String categoryName;
	@Column(name = "description")
	private  String description;
	@Column(name = "image_url")
	private  String imageUrl;

	public Category() {
		log.info("category Entity");
	}

	public Category(Long categoryId) {
		
	}	
	
}
