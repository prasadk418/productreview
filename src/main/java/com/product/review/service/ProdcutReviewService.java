package com.product.review.service;

import java.util.List;
import java.util.Optional;
import com.product.review.domain.Review;

public interface ProdcutReviewService {
	public List<Review> getProductReviews(Integer productId);
	public Review getReviewById(Integer reviewId);
	public Review getReviewById(Integer productId, Integer reviewId);	
	public Review saveProductReview(Review review);
	public Review updateProductReview(Review review);
	public void deleteProductReview(Integer productID, Integer reviewID);	
}
