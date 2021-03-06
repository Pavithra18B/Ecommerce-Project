package com.example.Project.BackendProject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Service.CategoryService;
import com.example.Project.BackendProject.controllerInterface.CategoryControllerInter;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerInter {
	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping("/viewpage/Category")
	@ApiOperation(value = "pagination and sorting by id")
	Page<Category> getCategories(@PageableDefault(sort = { "categoryId" }) final Pageable page) {
		log.info("Display  all category by id ");
		return categoryService.getCategories(page);

	}

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@ApiOperation(value = "list of category with details")
	@GetMapping("/list")
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> body = categoryService.listCategories();
		log.info(" List of Categories");
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "create category")
	public Category createCategory(@RequestBody CategoryRequest categoryRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new Category method is invoked ");
		return categoryService.addCategory(categoryRequest);
	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping(value = "/getby/{category_id}")
	@ApiOperation(value = "get category with details by id")
	public ResponseEntity<ApiResponse> getOne(@PathVariable(value = "category_id") Long categoryId) {
		try {
			Category category = categoryService.findById(categoryId);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, "Category with the given categoryId is available "), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "categoryId is invalid"),
					HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/update/{category_id}")
	@ApiOperation(value = "Update category")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("category_id") Long categoryId,
			@RequestBody CategoryRequest categoryRequest) throws Exception {
		log.info("update Category details");
		try {
			Category existCategory = categoryService.findById(categoryId);
			categoryService.updateCategory(categoryId, categoryRequest);
			return new ResponseEntity<>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"),
					HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/delete/{category_id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete category details by id")
	public Category deleteCategory(@PathVariable(value = "category_id") Long categoryId) {
		categoryService.delete(categoryId);
		return new Category(categoryId);
	}

}
