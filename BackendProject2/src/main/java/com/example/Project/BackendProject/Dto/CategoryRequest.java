package com.example.Project.BackendProject.Dto;

import com.example.Project.BackendProject.Model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
	private  String categoryName;
	private  String description;
	private  String imageUrl;
	
	
}
