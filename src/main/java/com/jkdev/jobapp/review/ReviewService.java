package com.jkdev.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReviewById(long companyId, long reviewId);
    boolean updateReview(long companyId, long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
