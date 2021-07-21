package com.example.Project.BackendProject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.BackendProject.Dto.ApiResponse;
import com.example.Project.BackendProject.Dto.ProductRequest;
import com.example.Project.BackendProject.Dto.WishListRequest;
import com.example.Project.BackendProject.Model.Category;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Model.User;
import com.example.Project.BackendProject.Model.WishList;
import com.example.Project.BackendProject.Service.JwtService;
import com.example.Project.BackendProject.Service.ProductService;
import com.example.Project.BackendProject.Service.WishListService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListService wishListService;
	
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping("/viewpage/wishlist")
	@ApiOperation(value = "pagination and sorting by id")
	Page<WishList> getWishList(@PageableDefault(sort = { "wishListId" }) final Pageable page) {
		log.info("Display  all products by id ");
		return wishListService.getWishList(page);

	}
@PreAuthorize("hasAnyRole('user', 'admin')")
	@GetMapping("/list/{user_id}")
	@ApiOperation(value = "list of product")
	public ResponseEntity<List<WishList>> getWishlist(@PathVariable("user_id") Integer userId) {
		log.info("list of all products ");
		List<WishList> body = wishListService.readWishList(userId);
		return new ResponseEntity<List<WishList>>(body, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('user', 'admin')")
	@ApiOperation(value = "create wishlists ")
	@PostMapping("/add")
   	public WishList addWishList(@RequestBody WishListRequest wishListRequest) throws Exception {
		log.info(this.getClass().getSimpleName() + " - Create new wishlist method is invoked ");
		return wishListService.createWishlist(wishListRequest);
	
	}
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/delete/{wishlist_id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete product details by id")
	public WishList deleteWishList(@PathVariable(value = "wishlist_id") Integer wishListId) {
		wishListService.delete(wishListId);
		return new WishList(wishListId);
	}

   }
