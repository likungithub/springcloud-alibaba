package com.sonder.service.impl;

import com.sonder.dao.OrderDao;
import com.sonder.domain.Order;
import com.sonder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }
}
