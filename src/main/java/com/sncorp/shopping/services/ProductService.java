package com.sncorp.shopping.services;

import com.sncorp.shopping.dao.ProductRepository;
import com.sncorp.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProduct(Integer id) {
        return repository.findById(id);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAllProducts() {
        repository.deleteAll();
    }
}
