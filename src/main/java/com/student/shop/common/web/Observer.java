package com.student.shop.common.web;

// 定义观察者接口 Define the observer interface
interface Observer {
    void update(Orders order);

    void update();
}
