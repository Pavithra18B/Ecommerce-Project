package com.example.Project.BackendProject.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "wishlists")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wishlist_id")
    private Integer wishListId;
    
    @Column(name = "user_id")
    private Integer userId;
@JsonFormat(pattern="MM/dd/yyyy")
    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wishListProduct_id",referencedColumnName = "product_id")
    private Product product;
    
	public WishList(Integer wishListId) {
		this.wishListId = wishListId;
			}
    
}
