package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;

public interface ProductServiceInter {
	public List<ProductRequest> listProducts();
	public  ProductRequest getDtoFromProduct(Product product);
	public Product getProductFromDto(ProductRequest productRequest, Category category);
	public void addProduct(ProductRequest productRequest, Category category);
	public void updateProduct(long productId, ProductRequest productRequest, Category category);

}
