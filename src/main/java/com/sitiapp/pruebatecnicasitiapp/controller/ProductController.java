package com.sitiapp.pruebatecnicasitiapp.controller;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;
import com.sitiapp.pruebatecnicasitiapp.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


//    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping
    public List<Product> getAll() {
        return this.productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.save(product));
    }

    @GetMapping("code/{code}")
    private ResponseEntity<Product> findByCode(@PathVariable String code) {
        Product product = this.productService.findByCode(code);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findBy(@PathVariable Integer id) {
        Product product = this.productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {

        Product product1 = this.productService.findById(id);
        if (product1 == null){
            return ResponseEntity.notFound().build();
        }
        product1.setCode(product.getCode());
        product1.setName(product.getName());
        product1.setState(product.getState());
        product1.setValue(product.getValue());
        return ResponseEntity.ok(this.productService.update(product1));

    }
}
