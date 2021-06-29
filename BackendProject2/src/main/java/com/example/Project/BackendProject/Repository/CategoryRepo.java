package com.example.Project.BackendProject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Project.BackendProject.Model.Category;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
	
	Category findByCategoryName(String categoryName);

}
