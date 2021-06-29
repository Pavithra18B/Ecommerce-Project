package com.example.Project.BackendProject.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.JwtDto.ApiResponse;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Service.CategoryService;
import com.example.Project.BackendProject.Service.ProductService;
import com.example.Project.BackendProject.controllerInterface.ProductControllerInter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController implements ProductControllerInter {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/viewpage/products")
	Page<Product> getProducts (@PageableDefault(sort= {"productId"}) final Pageable page){
		log.info("Display  all products by id ");
		return productService.getProducts(page);

	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping("/list")
	public ResponseEntity<List<ProductRequest>> getProducts() {
		log.info("list of all products ");
		List<ProductRequest> body = productService.listProducts();
		return new ResponseEntity<List<ProductRequest>>(body, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRequest productRequest) {
		log.info("add products ");
		Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
		}
		Category category = optionalCategory.get();
		productService.addProduct(productRequest, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/update/{product_Id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("product_Id") long productId, @RequestBody  ProductRequest productRequest) {
		log.info("update products ");
		Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
		if (!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
		}
		Category category = optionalCategory.get();
		productService.updateProduct(productId, productRequest, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
	}


}
