package com.example.Project.BackendProject.Dto;

import com.example.Project.BackendProject.Model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private  String name;
	private  String imageURL;
	private  double price;
	private  String description;
	private  Long categoryId;

	public ProductRequest(Product product) {
		this.setName(product.getName());
		this.setImageURL(product.getImageURL());
		this.setDescription(product.getDescription());
		this.setPrice(product.getPrice());
		this.setCategoryId(product.getCategory().getCategoryId());
	}
}
