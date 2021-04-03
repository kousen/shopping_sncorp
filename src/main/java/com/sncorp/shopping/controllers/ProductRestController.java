package com.sncorp.shopping.controllers;

import com.sncorp.shopping.entities.Product;
import com.sncorp.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class ProductRestController {
    private final ProductService service;

    @Value("${defaultPrice}")
    private Double defaultPrice;

    @Autowired
    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("Default price is " + defaultPrice);
        return service.getProducts();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        return service.getProduct(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product p = service.addProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).body(p);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        Optional<Product> existingProduct = service.getProduct(id);
        if (existingProduct.isPresent()) {
            service.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProducts() {
        service.deleteAllProducts();
    }
}
