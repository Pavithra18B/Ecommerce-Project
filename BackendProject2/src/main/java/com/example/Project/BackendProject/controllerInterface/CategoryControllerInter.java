package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Project.BackendProject.JwtDto.ApiResponse;
import com.example.Project.BackendProject.Model.Category;

public interface CategoryControllerInter {
	public ResponseEntity<List<Category>> getCategories();
	public ResponseEntity<ApiResponse> createCategory( Category category);
	public ResponseEntity<ApiResponse> updateCategory( long categoryId,Category category);

}
