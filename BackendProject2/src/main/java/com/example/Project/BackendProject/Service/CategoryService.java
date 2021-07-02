package com.example.Project.BackendProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.CategoryRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Repository.CategoryRepo;
import com.example.Project.BackendProject.ServiceInterface.CategoryServiceInter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService implements CategoryServiceInter {
	@Autowired
	private CategoryRepo categoryRepo;

//List of categories
	@Override
	public List<Category> listCategories() {
		log.info("Service method list Category  called");
		return (List<Category>) categoryRepo.findAll();
	}
 
//pagination
	public Page<Category> getCategories(Pageable page) {
		log.info("Page", page);
		Page<Category> category = categoryRepo.findAll(page);
		log.info("Category", category);
		return categoryRepo.findAll(page);

	}

//add category
	@Override
	public Category addCategory(CategoryRequest categoryRequest) throws Exception {

		Category category = new Category();
		log.info(category.toString());
		category.setCategoryName(categoryRequest.getCategoryName());
		category.setDescription(categoryRequest.getDescription());
		category.setImageUrl(categoryRequest.getImageUrl());

		return categoryRepo.save(category);
	}

//Update category
	@Override
	public Category updateCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception {
		log.info("Upadte Category service method");
		Category category = new Category();
		Optional<Category> categories = categoryRepo.findById(categoryId);
		if (!categories.isPresent()) {
			throw new Exception("Could not find product with id- " + categories);
		} else {

			category.setCategoryName(categoryRequest.getCategoryName());
			category.setDescription(categoryRequest.getDescription());
			category.setImageUrl(categoryRequest.getImageUrl());

		}
		category.setCategoryId(categoryId);

		return categoryRepo.save(category);
	}

//find category by Id
	@Override
	public Category findById(Long categoryId) {
		return categoryRepo.findById(categoryId).get();
	}

//delete Category by Id
	@Override
	public void delete(Long categoryId) {
		categoryRepo.deleteById(categoryId);
	}

//delete all
	@Override
	public void deleteAllCategories() {
		categoryRepo.deleteAll();
	}

}
