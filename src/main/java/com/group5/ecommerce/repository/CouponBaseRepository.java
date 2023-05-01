package com.group5.ecommerce.repository;

import com.group5.ecommerce.entity.CouponBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponBaseRepository extends JpaRepository<CouponBase, Long> {
}
