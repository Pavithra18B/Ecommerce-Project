package com.example.Project.BackendProject.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListRequest {
	private Integer userId;
	private Date createdDate;
	private Long productId;
}
