package com.example.Project.BackendProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Repository.ProductRepo;
import com.example.Project.BackendProject.ServiceInterface.ProductServiceInter;
@Service
public class ProductService implements ProductServiceInter {
	@Autowired
	private ProductRepo productRepo;
    /* PRODUCT */
    public List<ProductRequest> listProducts() {
        Iterable<Product> products = productRepo.findAll();
        List<ProductRequest> productRequests = new ArrayList<>();
        for(Product product : products) {
        	ProductRequest productRequest = getDtoFromProduct(product);
        	productRequests.add(productRequest);
        }
        return productRequests;
    }

    public  ProductRequest getDtoFromProduct(Product product) {
    	ProductRequest productRequest = new ProductRequest(product);
        return productRequest;
    }

    public Product getProductFromDto(ProductRequest productRequest, Category category) {
        Product product = new Product(productRequest, category);
        return product;
    }

    public void addProduct(ProductRequest productRequest, Category category) {
        Product product = getProductFromDto(productRequest, category);
        productRepo.save(product);
    }

    public void updateProduct(long productID, ProductRequest productRequest, Category category) {
        Product product = getProductFromDto(productRequest, category);
        product.setId(productID);
        productRepo.save(product);
    }

}