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
import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Service.ProductService;
import com.example.Project.BackendProject.controllerInterface.ProductControllerInter;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class ProductController implements ProductControllerInter {
	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping("/viewpage/products")
	@ApiOperation(value = "pagination and sorting by id")
	Page<Product> getProducts(@PageableDefault(sort = { "productId" }) final Pageable page) {
		log.info("Display  all products by id ");
		return productService.getProducts(page);

	}

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping("/list")
	@ApiOperation(value = "list of product")
	public ResponseEntity<List<Product>> getProducts() {
		log.info("list of all products ");
		List<Product> body = productService.listProducts();
		return new ResponseEntity<List<Product>>(body, HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	@PreAuthorize("hasRole('admin')")
	@ApiOperation(value = "create product")
	public Product createProduct(@RequestBody ProductRequest productRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new product method is invoked ");
		return productService.addProduct(productRequest);
	}

	@PreAuthorize("hasRole('admin')")
	@GetMapping(value = "/product/{product_id}")
	@ApiOperation(value = "get product with details by id")
	public ResponseEntity<ApiResponse> getOne(@PathVariable(value = "product_id") Long productId) {
		try {
			Product product = productService.findById(productId);
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(true, "Product with the given Productid is available "), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Productid is invalid"), HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasRole('admin')")
	@PostMapping("/update/{product_id}")
	@ApiOperation(value = "Update product")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("product_id") Long productId,
			@RequestBody ProductRequest productRequest) throws Exception {
		log.info("update Product  details");
		try {
			Product existProduct = productService.findById(productId);
			productService.updateProduct(productId, productRequest);
			return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Productid is invalid"), HttpStatus.CONFLICT);
		}
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/delete/{product_id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete product details by id")
	public Product deleteProduct(@PathVariable(value = "product_id") Long productId) {
		productService.delete(productId);
		return new Product(productId);
	}

}
