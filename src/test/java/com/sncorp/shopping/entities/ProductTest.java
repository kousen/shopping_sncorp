package com.sncorp.shopping.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Autowired
    private Validator validator;

    @Test
    void nameCanNotBeBlank() {
        Product product = new Product("", 10.0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        violations.forEach(System.out::println);
        assertEquals(1, violations.size());

        Optional<ConstraintViolation<Product>> violation = violations.stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals("A name is required", violation.get().getMessage());
    }

    @Test
    void priceMustBeGEZero() {
        Product product = new Product("name", -1.0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        System.out.println(violations);
    }

    @Test
    void priceGE10() {
        Product product = new Product("name", 11.0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        System.out.println(violations);
    }
}