package com.student.shop.test.service;

import com.student.shop.model.User;
import com.student.shop.service.UserService;
import com.student.shop.model.Admin;
import com.student.shop.model.Product;
import com.student.shop.service.AdminService;
import com.student.shop.service.OrderService;
import com.student.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * init test case
 */
public class InitiTest {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    AdminService adminService;

//    @Test
    public void testInit() {
        Admin admin = new Admin();
        admin.setUsername("vito");
        admin.setPassword("123456");
        adminService.save(admin);

        User user = new User();
        user.setUsername("zhouwentao");
        user.setPassword("123456");
        userService.save(user);

        Product team1 = new Product();
        team1.setTitle("3T Doric Team Carbon 坐管");
        team1.setPoint(799);
        team1.setModel("3T-TEAM-DORIC-309");
        team1.setCreateTime(new Date());
        team1.setInputUser(admin);
        productService.save(team1);

        Product team2 = new Product();
        team2.setTitle("BMC SLR01 车队版全碳车架");
        team2.setPoint(19799);
        team2.setModel("BMC-SLR01");
        team2.setCreateTime(new Date());
        team2.setInputUser(admin);
        productService.save(team1);

        Product team3 = new Product();
        team3.setTitle("FOX FLOAT 32 CTD气压前叉");
        team3.setPoint(19799);
        team3.setModel("FOX-32-100");
        team3.setCreateTime(new Date());
        team3.setInputUser(admin);
        productService.save(team1);
    }

}
