package com.student.shop.repository;

import com.student.shop.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 * 
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
}
