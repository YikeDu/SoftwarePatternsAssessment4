/**
 *
 */
package com.student.shop.service;

import com.student.shop.model.OrderItem;
import com.student.shop.repository.OrderItemRepository;
import com.student.shop.repository.OrderRepository;
import com.student.shop.common.Constants;
import com.student.shop.common.Page;
import com.student.shop.model.Order;
import com.student.shop.model.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Yike Du
 * @date 2023-3-17
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderDao;
    @Autowired
    OrderItemRepository orderItemDao;
    @Autowired
    UserAddressService userAddressService;

    /**
     * 新建订单
     *
     * @param order
     * @param orderItemList
     * @param userAddress
     */
    public void addOrder(Order order, List<OrderItem> orderItemList, UserAddress userAddress) {
        //更新或新增用户收获地址(根据userAddress是否包含id判断新增还是更新)
        userAddressService.save(userAddress);
        save(order);
        for (OrderItem orderItem : orderItemList) {
            orderItemDao.save(orderItem);
        }
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public Order findById(Integer id) {
        return orderDao.getOne(id);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public List<Order> findOrders(Page<Order> page) {
        page.setResult(orderDao.findAll(page.getPageable()).getContent());
        page.setTotalCount(orderDao.count());
        return page.getResult();
    }

    public List<Order> findOrders(Page<Order> page,Integer userId){
        page.setResult(orderDao.findByUserId(userId,page.getPageable()).getContent());
        page.setTotalCount(orderDao.countByUserId(userId));
        return null;
    }

    /**
     * 删除订单以及订单相关信息
     *
     * @param id 订单ID
     */
    public void deleteOrder(Integer id) {
        orderItemDao.deleteByOrderId(id);
        orderDao.deleteById(id);
    }

    /**
     * 修改订单状态
     *
     * @param orderID
     * @param status
     */
    public void updateOrderStatus(Integer orderID, Integer status) {
        Order order = orderDao.getOne(orderID);
        order.setStatus(status);
        //状态修改时修改相应时间数据
        if (status == Constants.OrderStatus.PAYED) {
            order.setPayTime(new Date());
        } else if (status == Constants.OrderStatus.SHIPPED) {
            order.setShipTime(new Date());
        } else if (status == Constants.OrderStatus.ENDED) {
            order.setConfirmTime(new Date());
        }
        orderDao.save(order);
    }

    /**
     * 验证订单归属人
     *
     * @param orderId
     * @param userId
     * @return
     */
    public boolean checkOwned(Integer orderId, Integer userId) {
        return orderDao.getOne(orderId).getUser().getId().equals(userId);
    }

    public void pay(Integer orderId) {
        Order order = orderDao.getOne(orderId);
        order.setStatus(Constants.OrderStatus.PAYED);
        order.setPayTime(new Date());
        orderDao.save(order);
    }
}
