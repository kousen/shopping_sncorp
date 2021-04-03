package com.sncorp.shopping.dao;

import com.sncorp.shopping.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceGreaterThanEqual(double amount);
}
