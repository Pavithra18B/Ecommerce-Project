package com.example.Project.BackendProject.Model;

import javax.persistence.*;

import com.example.Project.BackendProject.Dto.ProductRequest;

import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productid")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "imageURL")
	private String imageURL;
	@Column(name = "price")
	private double price;
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private  Category category;

	public Product(ProductRequest productRequest, Category category) {
		this.name = productRequest.getName();
		this.imageURL = productRequest.getImageURL();
		this.description = productRequest.getDescription();
		this.price = productRequest.getPrice();
		this.category = category;
	}
public Product() {
	
}
}
