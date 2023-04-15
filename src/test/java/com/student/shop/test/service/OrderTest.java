package com.student.shop.test.service;

import com.student.shop.model.OrderItem;
import com.student.shop.service.OrderService;
import com.student.shop.service.UserService;
import com.student.shop.test.BaseTest;
import com.student.shop.model.Order;
import com.student.shop.model.Product;
import com.student.shop.model.UserAddress;
import com.student.shop.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author student
 * @version 2023/03/14
 */
public class OrderTest extends BaseTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Test
    public void testOrdering(){
        List<Product> productList = productService.findAll();
        Order order = new Order();
        order.setCreateTime(new Date());
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for(Product pro:productList){
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(2);
            orderItem.setOrder(order);
            orderItem.setProduct(pro);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        UserAddress ua = new UserAddress();
        ua.setAddress("asdfasdf");
        orderService.addOrder(order,orderItems,ua);
    }

    @Test
    public void testDelOrder(){
        //orderService.deleteOrder(5);
    }
}
