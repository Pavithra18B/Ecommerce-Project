package com.example.Project.BackendProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.Project.BackendProject.Service.CategoryService;
import com.example.Project.BackendProject.Service.ProductService;
import com.example.Project.BackendProject.controllerInterface.ProductControllerInter;


@RestController
public class ProductController implements ProductControllerInter {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	 @PreAuthorize("hasRole('admin')")
    @GetMapping("/")
    public ResponseEntity<List<ProductRequest>> getProducts() {
        List<ProductRequest> body = productService.listProducts();
        return new ResponseEntity<List<ProductRequest>>(body, HttpStatus.OK);
    }
	 
	 @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRequest productRequest) {
        Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productRequest, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }
	 
	 @PreAuthorize("hasRole('admin')")
    @PostMapping("/update/{productid}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productid") long productid, @RequestBody  ProductRequest productRequest) {
        Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productid, productRequest, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
