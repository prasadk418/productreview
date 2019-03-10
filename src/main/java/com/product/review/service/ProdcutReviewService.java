package com.product.review.service;

import java.util.List;
import java.util.Optional;
import com.product.review.domain.Review;

public interface ProdcutReviewService {
	public List<Review> getProductReviews(Integer productId);	
	public Review saveProductReview(Review review);
	public Review updateProductReview(Review review);
	public void deleteProductReview(Integer productID, Integer reviewID);	
}
