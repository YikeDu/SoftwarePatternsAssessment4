package com.student.shop.common.web;

// 定义被观察者接口 Define the observed interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Orders order);

    void notifyObservers();
}