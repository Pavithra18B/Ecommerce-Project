package com.example.Project.BackendProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Repository.ProductRepo;
import com.example.Project.BackendProject.ServiceInterface.ProductServiceInter;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ProductService implements ProductServiceInter {
	@Autowired
	private ProductRepo productRepo;
	
	
    /* PRODUCT */
    public List<ProductRequest> listProducts() {
    	log.info("Service method list products  called");
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
    	log.info("Service method add product  called");
        Product product = getProductFromDto(productRequest, category);
        productRepo.save(product);
    }

    public void updateProduct(long productId, ProductRequest productRequest, Category category) {
    	log.info("Service method update Product  called");
        Product product = getProductFromDto(productRequest, category);
        product.setProductId(productId);
        productRepo.save(product);
    }

	public Page<Product> getProducts(Pageable page) {
		log.info("Page", page);
		Page<Product> product = productRepo.findAll(page);
		log.info("Product", product);
		return productRepo.findAll(page);
	}

	
}
