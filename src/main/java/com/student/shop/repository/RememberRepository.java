package com.student.shop.repository;

import com.student.shop.model.Remember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author student
 * @version 2023/3/13
 */
public interface RememberRepository extends JpaRepository<Remember,String> {
}
