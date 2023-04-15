package com.student.shop.repository;

import com.student.shop.model.Announcement;
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
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
