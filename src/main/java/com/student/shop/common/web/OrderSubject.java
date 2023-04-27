package com.student.shop.common.web;

import java.util.ArrayList;
import java.util.List;

public class OrderSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private Orders order;

    public OrderSubject(Orders order) {
        this.order = order;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Orders order) {

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setOrderStatus(String status) {
        order.setStatus(status);
        notifyObservers();
    }

    public Orders getOrder() {
        return null;
    }
}
