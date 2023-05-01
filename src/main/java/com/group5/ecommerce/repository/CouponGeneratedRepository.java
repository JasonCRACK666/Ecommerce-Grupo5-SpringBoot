package com.group5.ecommerce.repository;

import com.group5.ecommerce.entity.CouponGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponGeneratedRepository extends JpaRepository<CouponGenerated, Long> {
}
