package com.example.Project.BackendProject.Model;

import java.util.List;

import javax.persistence.*;
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
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "name")
	private String name;
	@Column(name = "product_imageurl")
	private String productImageurl;
	@Column(name = "price")
	private double price;
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productcat_id", referencedColumnName = "category_id")
	private Category category;
	
	public Product() {
		log.info("Product Entity");
	}

	public Product(Long productId) {

	}
}
