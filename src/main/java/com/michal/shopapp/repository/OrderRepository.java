package com.michal.shopapp.repository;

import com.michal.shopapp.components.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
