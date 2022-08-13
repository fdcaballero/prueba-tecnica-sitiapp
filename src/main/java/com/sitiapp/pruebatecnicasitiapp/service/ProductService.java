package com.sitiapp.pruebatecnicasitiapp.service;

import com.sitiapp.pruebatecnicasitiapp.entity.Product;

import java.util.List;

public interface ProductService {

    Product findById(Integer id);

    Product findByCode(String code);

    Product save(Product product);

    List<Product> findAll();

    boolean delete(Integer id);

    Product update(Product product);

}
