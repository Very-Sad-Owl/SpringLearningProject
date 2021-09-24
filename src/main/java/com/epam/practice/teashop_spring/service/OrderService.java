package com.epam.practice.teashop_spring.service;

import com.epam.practice.teashop_spring.domain.User;
import com.epam.practice.teashop_spring.service.impl.OrderServiceImpl;
import com.epam.practice.teashop_spring.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order save(Order order);

    List<Order> findOrderByUser(User user);
}
