package com.sonder.service.impl;

import com.sonder.dao.ProductDao;
import com.sonder.domain.Product;
import com.sonder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Integer pid) {
        return productDao.findById(pid).get();
    }
}
