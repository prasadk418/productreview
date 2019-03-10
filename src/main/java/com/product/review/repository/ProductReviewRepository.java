package com.product.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.review.domain.Review;


@Repository
public interface ProductReviewRepository extends JpaRepository<Review, Integer>{

	@Query("select review from Review review where review.productId=:productID")
	public List<Review> getReviewsByProductId(@Param("productID") Integer productID);
	
	@Query("delete from Review review where review.productId=:productID AND review.reviewId=:reviewID")
	public List<Review> deleteProductReview(@Param("productID") Integer productID, @Param("reviewID") Integer reviewID);
	
}
 