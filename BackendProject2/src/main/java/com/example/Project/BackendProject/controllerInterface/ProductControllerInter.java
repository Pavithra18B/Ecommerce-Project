package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Product;

public interface ProductControllerInter {
	ResponseEntity<List<Product>> getProducts();

	Product createProduct(@RequestBody ProductRequest productRequest) throws Exception;

	ResponseEntity<?> updateProduct(Long productId, @RequestBody ProductRequest productRequest) throws Exception;

	ResponseEntity<ApiResponse> getOne(Long productId);
  
	Product deleteProduct(Long productId);
}
