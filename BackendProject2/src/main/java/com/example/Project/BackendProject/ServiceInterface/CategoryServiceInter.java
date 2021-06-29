package com.example.Project.BackendProject.ServiceInterface;

import java.util.Optional;

import com.example.Project.BackendProject.Model.Category;

public interface CategoryServiceInter {
	public Iterable<Category> listCategories();
	public void createCategory(Category category);
	public Category readCategory(String categoryName);
	public Optional<Category> readCategory(Long categoryId);
	public void updateCategory(Long categoryId, Category newCategory);
}
