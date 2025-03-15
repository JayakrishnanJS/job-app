package com.jkdev.jobapp.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(long companyId);
    // No need to implement, JPA will use findBy and pass companyId in runtime
}
