package com.product.review.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.review.domain.Review;
import com.product.review.exception.OperationNotPerformed;
import com.product.review.exception.ReviewNotFoundException;
import com.product.review.service.ProdcutReviewService;

@RestController
@RequestMapping
public class ProductReviewController {

	@Autowired
	private ProdcutReviewService reviewService;

	@GetMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> getProductReviews(@PathVariable("productid") Integer productID) {
		List<Review> reviews = reviewService.getProductReviews(productID);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> getProductReviews(@PathVariable("productid") Integer productID, @PathVariable("reviewid") Integer reviewID) {
		Optional<Review> reviews = reviewService.getReviewById(productID, reviewID);//getProductReviews(productID);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@PostMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> createProductReview(@PathVariable("productid") Integer productID, @RequestBody Review review) {
		review.setProductId(productID);
		Review review1=reviewService.saveProductReview(review);
		if(review1 == null){
			throw new OperationNotPerformed("Data not inserted into  DB.");
		}
		return new ResponseEntity<>(review1.getReviewId(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> updateProductReview(@PathVariable("productid") Integer productID, @PathVariable("reviewid") Integer reviewID,
			@RequestBody Review review) {
		Optional<Review> review1=reviewService.getReviewById(reviewID);
		review1.ifPresent(r -> {
			r.setProductId(productID);			
			reviewService.saveProductReview(r);
		});
		review1.orElseThrow(() -> new ReviewNotFoundException("Review Not Found"));
								 
		return new ResponseEntity<>(review1.get().getReviewId(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> deleteProductReview(@PathVariable("productid") Integer productID, @PathVariable("reviewid") Integer reviewID) {
		Optional<Review> review1=reviewService.getReviewById(reviewID);
		review1.ifPresent(r -> {
			reviewService.deleteProductReview(productID, reviewID);	
		});
		
		review1.orElseThrow(() -> new OperationNotPerformed("Data not deleted from DB."));
		
		return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
	}

}
