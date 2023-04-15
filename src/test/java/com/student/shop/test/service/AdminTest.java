package com.student.shop.test.service;

import com.student.shop.model.Admin;
import com.student.shop.service.AdminService;
import com.student.shop.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author student
 * @version 2023/03/14
 */
public class AdminTest extends BaseTest {

    @Autowired
    AdminService adminService;

    @Test
    public void testAddAdmin() {
        Admin admin = new Admin();
        admin.setUsername("vito");
        admin.setPassword("123456");
        adminService.save(admin);
    }

}
