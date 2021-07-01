package com.example.Project.BackendProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Repository.CategoryRepo;
import com.example.Project.BackendProject.Repository.ProductRepo;
import com.example.Project.BackendProject.ServiceInterface.ProductServiceInter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService implements ProductServiceInter {
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	// list of products
	@Override
	public List<Product> listProducts() {
		log.info("Service method list Category  called");
		return (List<Product>) productRepo.findAll();
	}

//pagination
	public Page<Product> getProducts(Pageable page) {
		log.info("Page", page);
		Page<Product> product = productRepo.findAll(page);
		log.info("Product", product);
		return productRepo.findAll(page);
	}

//add product
	@Override
	public Product addProduct(ProductRequest productRequest) throws Exception {

		Product product = new Product();
		log.info(product.toString());
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setPrice(productRequest.getPrice());
		product.setProductImageurl(productRequest.getProductImageurl());

		Optional<Category> category = categoryRepo.findById(productRequest.getProductcat_id());
		if (!category.isPresent()) {
			throw new Exception("Category id not found");
		} else {
			product.setCategory(category.get());
		}

		return productRepo.save(product);
	}

//update product
	@Override
	public Product updateProduct(Long productId, ProductRequest productRequest) throws Exception {
		log.info("Upadte Product service method");
		Product product = new Product();
		Optional<Product> prod = productRepo.findById(productId);
		if (!prod.isPresent()) {
			throw new Exception("Could not find product with id- " + prod);
		} else {

			product.setName(productRequest.getName());
			product.setProductImageurl(productRequest.getProductImageurl());
			product.setPrice(productRequest.getPrice());
			product.setDescription(productRequest.getDescription());
			Optional<Category> category = categoryRepo.findById(productRequest.getProductcat_id());
			if (!category.isPresent()) {
				throw new Exception("category id not found");
			} else {
				product.setCategory(category.get());
			}

			product.setProductId(productId);

		}
		return productRepo.save(product);
	}

//find product by Id
	@Override
	public Product findById(Long productId) {
		return productRepo.findById(productId).get();
	}

//delete product by Id
	@Override
	public void delete(Long productId) {
		productRepo.deleteById(productId);
	}

//delete all products
	@Override
	public void deleteAllProducts() {
		productRepo.deleteAll();
	}

}
