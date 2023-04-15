package com.student.shop.repository;

import com.student.shop.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Yike Du
 * @date 2023-3-9
 * 
 */
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
