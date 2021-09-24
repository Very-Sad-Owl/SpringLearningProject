package com.epam.practice.teashop_spring.repos;

import com.epam.practice.teashop_spring.domain.Order;
import com.epam.practice.teashop_spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByUser(User user);
}