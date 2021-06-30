package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;

public interface CategoryServiceInter {
	List<Category> listCategories();
	Page<Category> getCategories(Pageable page);
	Category addCategory(CategoryRequest categoryRequest) throws Exception;
	Category updateCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception;
	Category findById(Long categoryId);
	void delete(Long categoryId);
	void deleteAllCategories();
	}
