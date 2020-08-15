package com.sonder.dao;

import com.sonder.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {
}
