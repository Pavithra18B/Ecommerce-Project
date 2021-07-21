package com.example.Project.BackendProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Project.BackendProject.Dto.WishListRequest;
import com.example.Project.BackendProject.Model.Product;
import com.example.Project.BackendProject.Model.WishList;
import com.example.Project.BackendProject.Repository.ProductRepo;
import com.example.Project.BackendProject.Repository.WishListRepo;
import com.example.Project.BackendProject.ServiceInterface.WishListServiceInter;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class WishListService implements WishListServiceInter{
	@Autowired
	private  WishListRepo wishListRepository;
	@Autowired
	private ProductRepo productRepo;
	
	//pagination
		public Page<WishList> getWishList(Pageable page) {
			log.info("Page", page);
			Page<WishList> wishList = wishListRepository.findAll(page);
			log.info("WishList", wishList);
			return wishListRepository.findAll(page);
		}

	@Override
	public WishList createWishlist(WishListRequest wishListRequest) throws Exception {
		WishList wishList = new WishList();
		log.info(wishList.toString());
		wishList.setCreatedDate(wishListRequest.getCreatedDate());
		wishList.setUserId(wishListRequest.getUserId());
		
		Optional<Product> product = productRepo.findById(wishListRequest.getProductId());
		if (!product.isPresent()) {
			throw new Exception("product id not found");
		} else {
			wishList.setProduct(product.get());
		}

		return wishListRepository.save(wishList);
	}

@Override
	public List<WishList> readWishList(Integer userId) {
		return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
	}

//delete wishlist by Id
	@Override
	public void delete(Integer wishListId) {
		wishListRepository.deleteById(wishListId);
	}


}
