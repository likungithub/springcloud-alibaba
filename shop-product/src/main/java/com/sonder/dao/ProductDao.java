package com.sonder.dao;

import com.sonder.domain.Product;
import com.sonder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer> {
}
