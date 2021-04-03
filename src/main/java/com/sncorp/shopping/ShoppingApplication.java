package com.sncorp.shopping;

import com.sncorp.shopping.dao.ProductRepository;
import com.sncorp.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ShoppingApplication {

    private final ProductRepository repository;

    @Autowired
    public ShoppingApplication(ProductRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            long count = repository.count();
            if (count == 0) {
                repository.saveAll(List.of(
                        new Product("baseball", 9.99),
                        new Product("football", 14.95),
                        new Product("basketball", 11.99)
                ));
            }
            repository.findAll().forEach(System.out::println);
        };
    }

}
