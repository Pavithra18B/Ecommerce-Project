package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Model.Category;

public interface CategoryServiceInter {
	List<Category> listCategories();

	Page<Category> getCategories(Pageable page);

	Category addCategory(CategoryRequest categoryRequest) throws Exception;

	Category updateCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception;

	Category findById(Long categoryId);
 
	void delete(Long categoryId);

	void deleteAllCategories();
}
