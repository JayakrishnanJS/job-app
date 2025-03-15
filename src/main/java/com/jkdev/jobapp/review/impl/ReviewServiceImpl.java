package com.jkdev.jobapp.review.impl;

import com.jkdev.jobapp.company.Company;
import com.jkdev.jobapp.company.CompanyService;
import com.jkdev.jobapp.review.Review;
import com.jkdev.jobapp.review.ReviewRepository;
import com.jkdev.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final CompanyService companyService;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(long companyId, long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null & reviewRepository.existsById(reviewId)) {
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                company.getReviews().remove(review);
                review.setCompany(null); // Break the bidirectional link
                companyService. updateCompany(company, companyId);
                reviewRepository.deleteById(reviewId);
                return true;
        }
        return false;
    }
}
