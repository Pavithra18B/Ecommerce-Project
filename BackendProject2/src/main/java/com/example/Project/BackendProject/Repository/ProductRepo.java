package com.example.Project.BackendProject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Product;
@Repository
public interface ProductRepo extends CrudRepository<Product, Long>{

	
}
