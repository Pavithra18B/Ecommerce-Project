package com.example.Project.BackendProject.Model;

import javax.persistence.*;

import com.example.Project.BackendProject.Dto.ProductRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "products")
@Data
@Slf4j
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_Id")
	private Long productId;
	@Column(name = "name")
	private String name;
	@Column(name = "image_URL")
	private String imageURL;
	@Column(name = "price")
	private double price;
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_Id", referencedColumnName = "category_Id")
	private  Category category;

	public Product(ProductRequest productRequest, Category category) {
		this.name = productRequest.getName();
		this.imageURL = productRequest.getImageURL();
		this.description = productRequest.getDescription();
		this.price = productRequest.getPrice();
		this.category = category;
	}
	public Product() {
		log.info("Product Entity");
	}
}
