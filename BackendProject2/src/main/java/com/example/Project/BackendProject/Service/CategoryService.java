package com.example.Project.BackendProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Repository.CategoryRepo;
import com.example.Project.BackendProject.ServiceInterface.CategoryServiceInter;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CategoryService implements CategoryServiceInter{
	@Autowired
	private  CategoryRepo categoryRepo;

	public CategoryService(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	public List<Category> listCategories() {
		log.info("Service method list Category  called");
		return (List<Category>) categoryRepo.findAll();
	}

	public void createCategory(Category category) {
		log.info("Service method create Category  called");
		categoryRepo.save(category);
	}

	public Category readCategory(String categoryName) {
		log.info("Service method read Category byname  called");
		return categoryRepo.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Long categoryId) {
		log.info("Service method read Category by id  called");
		return categoryRepo.findById(categoryId);
	}

	public void updateCategory(Long categoryId, Category newCategory) {
		log.info("Service method update Category  called");
		Category category = categoryRepo.findById(categoryId).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setImageUrl(newCategory.getImageUrl());

		categoryRepo.save(category);
	}

	public Page<Category> getCategories(Pageable page) {
		log.info("Page", page);
		Page<Category> category = categoryRepo.findAll(page);
		log.info("Category", category);
		return categoryRepo.findAll(page);

	}

}
