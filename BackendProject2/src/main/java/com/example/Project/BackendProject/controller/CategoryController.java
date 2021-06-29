package com.example.Project.BackendProject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerInter{
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/viewpage/Category")
	Page<Category> getCategories (@PageableDefault(sort= {"categoryId"}) final Pageable page){
		log.info("Display  all category by id ");
		return categoryService.getCategories(page);

	}
	@PreAuthorize("hasRole('admin')")
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> body = categoryService.listCategories();
		log.info(" List of Categories");
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory( @RequestBody Category category) {
		log.info(" Create Category");
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {

			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/update/{category_Id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("category_Id") long categoryId,  @RequestBody Category category) {
		log.info(" Update Category by id");
		// Check to see if the category exists.
		if (Helper.notNull(categoryService.readCategory(categoryId))) {
			// If the category exists then update it.
			categoryService.updateCategory(categoryId, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
		}

		// If the category doesn't exist then return a response of unsuccessful.
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
	}

}
