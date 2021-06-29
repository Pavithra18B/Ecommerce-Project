package com.example.Project.BackendProject.controllerInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.JwtDto.ApiResponse;

public interface ProductControllerInter {
	public ResponseEntity<List<ProductRequest>> getProducts();
	public ResponseEntity<ApiResponse> addProduct(ProductRequest productRequest);
	public ResponseEntity<ApiResponse> updateProduct(long productid,ProductRequest productRequest);
}
