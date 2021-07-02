package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Model.Category;

public interface CategoryControllerInter {
	ResponseEntity<List<Category>> getCategories();

	Category createCategory(@RequestBody CategoryRequest categoryRequest) throws Exception;

	ResponseEntity<ApiResponse> updateCategory(Long categoryId, @RequestBody CategoryRequest categoryRequest) throws Exception;

	ResponseEntity<ApiResponse> getOne(Long categoryId);

	Category deleteCategory(Long categoryId);
}
