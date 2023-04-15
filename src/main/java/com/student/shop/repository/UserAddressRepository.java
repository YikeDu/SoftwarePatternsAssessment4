package com.student.shop.repository;

import com.student.shop.model.User;
import com.student.shop.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Student
 * @email student@test.com
 * @date 2023-3-9
 * 
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {
    List<UserAddress> findByUser(User user);
}
