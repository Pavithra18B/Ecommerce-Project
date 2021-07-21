package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Product;

public interface ProductServiceInter {
	List<Product> listProducts();

	Page<Product> getProducts(Pageable page);

	Product addProduct(ProductRequest productRequest) throws Exception;

	Product updateProduct(Long productId, ProductRequest productRequest) throws Exception;

	void deleteAllProducts();

	Product findById(Long productId);

	void delete(Long productId);

	
}
