package com.student.shop.controller;

import com.google.common.collect.Maps;
import com.student.shop.model.OrderItem;
import com.student.shop.model.User;
import com.student.shop.service.OrderService;
import com.student.shop.service.UserAddressService;
import com.student.shop.service.UserService;
import com.student.shop.util.CartItem;
import com.student.shop.util.CartUtil;
import com.student.shop.util.UserUtil;
import com.student.shop.common.Constants;
import com.student.shop.common.Page;
import com.student.shop.common.web.JsonResult;
import com.student.shop.model.Order;
import com.student.shop.model.UserAddress;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author Yike Du
 * @date 2023-3-8
 */
@Controller
@RequestMapping("/user/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserAddressService userAddressService;

    /**
     * 订单列表
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        org.springframework.util.Assert.notNull(user,"未登录用户，非法操作");
        Page<Order> page = new Page<>(request);
        orderService.findOrders(page, user.getId());
        model.addAttribute("page", page);
        return "order/orderList";
    }

    /**
     * 订单查看
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String orderView(@PathVariable Integer id, Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        org.springframework.util.Assert.notNull(user,"未登录用户，非法操作");
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/orderView";
    }

    /**
     * 确认收货
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/confirm/{id}")
    @ResponseBody
    public JsonResult orderConfirm(@PathVariable Integer id, Model model, HttpSession session, HttpServletRequest request) {
        User user = UserUtil.getUserFromSession(session);
        org.springframework.util.Assert.notNull(user,"未登录用户，非法操作");
        Order order = orderService.findById(id);

        JsonResult result = new JsonResult();
        if(Objects.equals(order.getUser().getId(), user.getId())){
            orderService.updateOrderStatus(id, Constants.OrderStatus.ENDED);
            result.setToSuccess();
        }else{
            result.setToFail();
        }
        return result;
    }


    /**
     * 订单确认
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String purchase(Model model, HttpSession session) {
        User user = userService.findOne(UserUtil.getUserFromSession(session).getId());
        List<UserAddress> userAddressList = user.getAddresses();
        model.addAttribute("addressList", userAddressList);

        HashMap<Integer, CartItem> cartItems = (HashMap<Integer, CartItem>) session.getAttribute(CartUtil.CART);
        if(cartItems==null){
            cartItems = Maps.newHashMap();
        }
        model.addAttribute("cartItems",cartItems);

        return "order/orderPurchase";
    }

    /**
     * 下单
     *
     * @param address
     * @param session
     * @return
     */
    @RequestMapping(value = "/ordering", method = RequestMethod.POST)
    public String ordering(UserAddress address, HttpSession session) {
        Order order = new Order();
        order.setCreateTime(new Date());
        address.setUser(UserUtil.getUserFromSession(session));
        order.setOrderNumber(new DateTime().toString("yyyyMMddHHmmSS"));
        order.setStatus(Constants.OrderStatus.WAIT_PAY);
        List<OrderItem> oiList = CartUtil.getOrderItemFromCart(session);
        BigDecimal totalSum = new BigDecimal("0");
        for (OrderItem oi : oiList) {
            totalSum = totalSum.add(new BigDecimal(oi.getProduct().getPoint() * oi.getQuantity()));
            oi.setOrder(order);
        }
        order.setTotalPrice(totalSum.doubleValue());
        order.setFinalPrice(totalSum.doubleValue());
        order.setOrderItems(oiList);
        order.setUser(UserUtil.getUserFromSession(session));
        //地址保存
        order.setAddress(address.getAddress());
        order.setZipcode(address.getZipcode());
        order.setConsignee(address.getConsignee());
        order.setPhone(address.getPhone());
        orderService.addOrder(order, oiList, address);
        CartUtil.cleanCart(session);
        return "order/orderingSuccess";
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewOrder(@PathVariable Integer id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/orderDetail";
    }*/

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult pay(@PathVariable(value = "id") Integer orderId, HttpSession session) {
        JsonResult result = new JsonResult();
        //验证订单是否归当前人员所有
        if (orderService.checkOwned(orderId, UserUtil.getUserFromSession(session).getId())) {
            orderService.pay(orderId);
            result.setToSuccess();
        }else{
            result.setToFail();
        }
        return result;
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult cancel(@PathVariable(value = "id") Integer orderId) {
        //TODO 验证是否拥有此订单
        orderService.updateOrderStatus(orderId, Constants.OrderStatus.DELETED);

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }

    /**
     * 订单收货确认
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult confirm(@PathVariable(value = "id") Integer orderId) {
        orderService.updateOrderStatus(orderId, Constants.OrderStatus.ENDED);

        JsonResult result = new JsonResult();
        result.setToSuccess();
        return result;
    }

}
