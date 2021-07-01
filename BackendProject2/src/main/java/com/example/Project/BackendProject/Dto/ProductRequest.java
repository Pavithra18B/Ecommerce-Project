package com.example.Project.BackendProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private String name;
	private String productImageurl;
	private double price;
	private String description;
	private Long productcat_id;
}
