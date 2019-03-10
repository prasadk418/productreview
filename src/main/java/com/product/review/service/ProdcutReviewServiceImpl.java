package com.product.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.review.domain.Review;
import com.product.review.repository.ProductReviewRepository;

@Service
public class ProdcutReviewServiceImpl implements ProdcutReviewService {

	@Autowired
	private ProductReviewRepository reviewRepository; 
	
	@Override
	public List<Review> getProductReviews(Integer productId) {				
		return reviewRepository.getReviewsByProductId(productId);
	}
	
	@Override
	public Review saveProductReview(Review product) {
		return reviewRepository.save(product);
	}

	@Override
	public Review updateProductReview(Review product) {
		return reviewRepository.save(product);		
	}
	
	public void deleteProductReview(Integer reviewID, Integer productID) {
		reviewRepository.deleteProductReview(productID, reviewID);
		
	}

	@Override
	public Optional<Review> getReviewById(Integer reviewId) {		
		Optional<Review> review=reviewRepository.findById(reviewId);
		return review;
	}

	@Override
	public Optional<Review> getReviewById(Integer productId, Integer reviewId) {
		Optional<Review> review=reviewRepository.getReviewByProductId(productId, reviewId);
		return review;
	}

	
}
