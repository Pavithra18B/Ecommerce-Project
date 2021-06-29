package com.example.Project.BackendProject.Model;


import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Long id;

		@Column(name = "categoryName")
		private  String categoryName;
		@Column(name = "description")
		private  String description;
		@Column(name = "imageUrl")
		private  String imageUrl;

		// add imageURL here
		/*
		 * @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade =
		 * CascadeType.ALL) Set<Product> products;
		 */
		public Category() {
		}
		
		public Category( String categoryName,  String description) {
			this.categoryName = categoryName;
			this.description = description;
		}

		

}
