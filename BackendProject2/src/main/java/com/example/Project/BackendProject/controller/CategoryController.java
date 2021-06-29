package com.example.Project.BackendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Jwt.Helper;
import com.example.Project.BackendProject.JwtDto.ApiResponse;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Service.CategoryService;
import com.example.Project.BackendProject.controllerInterface.CategoryControllerInter;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerInter{
	@Autowired
	private CategoryService categoryService;
	
	 @PreAuthorize("hasRole('admin')")
	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }
	
	 @PreAuthorize("hasRole('admin')")
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory( @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}
	 
	 @PreAuthorize("hasRole('admin')")
	@PostMapping("/update/{category_id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("category_id") long categoryID,  @RequestBody Category category) {
		// Check to see if the category exists.
		if (Helper.notNull(categoryService.readCategory(categoryID))) {
			// If the category exists then update it.
			categoryService.updateCategory(categoryID, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}

}
