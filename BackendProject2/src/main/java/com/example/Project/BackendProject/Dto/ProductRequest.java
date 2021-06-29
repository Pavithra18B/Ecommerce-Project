package com.example.Project.BackendProject.Dto;

import com.example.Project.BackendProject.Model.Product;

import lombok.Data;
@Data
public class ProductRequest {
	private Long id;
    private  String name;
    private  String imageURL;
    private  double price;
    private  String description;
    private  Long categoryId;

    public ProductRequest(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageURL(product.getImageURL());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

	public ProductRequest(Long id, String name, String imageURL, double price, String description, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
	}

	}
