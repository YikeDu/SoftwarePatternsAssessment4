package com.student.shop.common.web;

import java.util.ArrayList;
import java.util.List;

public class NotificationObserver implements Observer {
    private OrderSubject subject;
    private Orders orders;
    private List<String> notifications; // 保存通知信息的缓存

    public NotificationObserver(OrderSubject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
        this.notifications = new ArrayList<>();
    }

    @Override
    public void update(Orders order) {

    }

    @Override
    public void update() {
        String status = orders.getStatus();
        String message = "Your order has been updated to " + status;
        notifications.add(message); // 将通知信息保存到缓存中
    }

    public List<String> getNotifications() {
        List<String> currentNotifications = new ArrayList<>(notifications);
        notifications.clear();
        return currentNotifications;
    }
}

