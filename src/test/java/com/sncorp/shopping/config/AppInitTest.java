package com.sncorp.shopping.config;

import com.sncorp.shopping.dao.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AppInitTest {
    @Autowired
    private ProductRepository repository;

    @Test @DisplayName("There should be three or more products in the database")
    void productInDB() {
        assertTrue(repository.count() >= 3);
    }
}