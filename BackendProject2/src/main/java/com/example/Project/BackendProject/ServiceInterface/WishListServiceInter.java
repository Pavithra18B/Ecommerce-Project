package com.example.Project.BackendProject.ServiceInterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Project.BackendProject.Dto.WishListRequest;
import com.example.Project.BackendProject.Model.WishList;

public interface WishListServiceInter {
	Page<WishList> getWishList(Pageable page);
	List<WishList> readWishList(Integer userId);
	WishList createWishlist(WishListRequest wishListRequest) throws Exception ;
	void delete(Integer wishListId);
}
