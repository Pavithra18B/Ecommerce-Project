package com.example.Project.BackendProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Repository.CategoryRepo;
import com.example.Project.BackendProject.ServiceInterface.CategoryServiceInter;

@Service
public class CategoryService implements CategoryServiceInter{
	@Autowired
    private  CategoryRepo categoryRepo;
	
	public CategoryService(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	public List<Category> listCategories() {
		return (List<Category>) categoryRepo.findAll();
	}
	
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}

	public Category readCategory(String categoryName) {
		return categoryRepo.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Long categoryId) {
		return categoryRepo.findById(categoryId);
	}

	public void updateCategory(Long categoryID, Category newCategory) {
		Category category = categoryRepo.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setImageUrl(newCategory.getImageUrl());

		categoryRepo.save(category);
	}

}
